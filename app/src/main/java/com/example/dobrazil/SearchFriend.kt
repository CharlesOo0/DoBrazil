package com.example.dobrazil

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.dobrazil.Entity.ProfilEntity
import com.example.dobrazil.data.LocalStorage
import com.example.dobrazil.ui.theme.DoBrazilTheme
import com.example.dobrazil.viewModel.eventInvitedViewModel
import com.example.dobrazil.viewModel.eventViewModel
import com.example.dobrazil.viewModel.favoriteViewModel
import com.example.dobrazil.viewModel.profilViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

/**
 * @brief SearchFriend composable that modelise the SearchFriend screen
 * @param mode Int that represent the mode of the screen Add Friend or Invite People
 * @param profilViewModel profilViewModel that contains the profilViewModel
 * @param favoriteViewModel favoriteViewModel that contains the favoriteViewModel
 * @param navController NavController that contains the NavController
 * */
@Composable
fun SearchFriend(
    mode : Int = 0,
    profilViewModel: profilViewModel,
    favoriteViewModel: favoriteViewModel,
    eventInvitedViewModel: eventInvitedViewModel,
    eventViewModel: eventViewModel,
    localStorage: LocalStorage,
    navController: NavController? = null) {
    Column ( // Column that contains the screen
        modifier = Modifier
            .background(Color(IvoryColor)) // Background color
            .fillMaxSize(), // Full size
    ) {
        Row(
            // Top bar
            modifier = Modifier
                .background(Color(GreenVariantColor))
                .fillMaxWidth(),
        ) {
            Icon( // Back button
                Icons.Default.ArrowBack,
                contentDescription = "Back",
                modifier = Modifier
                    .clickable(onClick = { navController?.popBackStack() }) // Make the icon clickable
                    .padding(8.dp)
            )

            Text(
                text = "Search Friends",
                color = Color.White,
                modifier = Modifier.padding(8.dp)
            ) // Title

        }

        BottomBorder(
            width = 3.dp,
            color = Color(IvoryBorderColor)
        ) // Border between top bar and form

        val search = remember { mutableStateOf("") } // Search value
        var listSearch : MutableState<List<ProfilEntity>> = remember { mutableStateOf(listOf()) } // List of profil


        // Search bar
        SearchBar(
            value = search.value, // Connect the search state to the SearchBar
            onValueChange = {
                search.value = it
            }, // Update the search state when the value changes
            onSearchExecute = {
                profilViewModel.viewModelScope.launch(Dispatchers.Main) { // Launch a coroutine

                    if (search.value != "") { // If the search value is not empty
                        listSearch.value = async { profilViewModel.searchNotFriendProfil(search.value, localStorage.idUser!!) }.await()
                    }

                }
            },
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            hint = "Search for contacts"
        )

        val scrollState = rememberScrollState() // Scroll state

        // List of contacts
        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            for (profil in listSearch.value) { // For each profil in the list
                Log.d("Profil", profil.username)
                Contact(localStorage.username, profil.username, profil.avatarLink, 0, favoriteViewModel = favoriteViewModel, eventInvitedViewModel = eventInvitedViewModel, eventViewModel = eventViewModel) // Display the contact
            }
        }
    }
}

/**
 * @brief Preview that allow to see the SearchFriend screen
 */
@Preview(showBackground = true)
@Composable
fun SearchFriendPreview() {
    DoBrazilTheme {
        SearchFriend(profilViewModel = hiltViewModel(), favoriteViewModel = hiltViewModel(), eventInvitedViewModel = hiltViewModel(), eventViewModel = hiltViewModel(), localStorage = LocalStorage())
    }
}