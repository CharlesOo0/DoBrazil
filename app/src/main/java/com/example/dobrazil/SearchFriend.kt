package com.example.dobrazil

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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.dobrazil.ui.theme.DoBrazilTheme

/**
 * @brief SearchFriend composable that modelise the SearchFriend screen
 * @param mode Int that represent the mode of the screen Add Friend or Invite People
 * */
@Composable
fun SearchFriend(mode : Int = 0, navController: NavController? = null) {
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

        // Search bar
        SearchBar(
            value = search.value, // Connect the search state to the SearchBar
            onValueChange = { search.value = it }, // Update the search state when the value changes
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
            // TODO Query the search value to get the contacts and print them
            Contact(mode = mode)
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
        SearchFriend()
    }
}