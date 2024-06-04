package com.example.dobrazil

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dobrazil.ui.theme.DoBrazilTheme
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.appwithroomuv.R
import com.example.dobrazil.Entity.ProfilEntity
import com.example.dobrazil.data.LocalStorage
import com.example.dobrazil.viewModel.profilViewModel
import androidx.lifecycle.viewModelScope
import com.example.dobrazil.Entity.EventEntity
import com.example.dobrazil.viewModel.eventFinanciersViewModel
import com.example.dobrazil.viewModel.eventInvitedViewModel
import com.example.dobrazil.viewModel.eventViewModel
import com.example.dobrazil.viewModel.favoriteViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * @brief Composable that allow to modelise the profil page
 */
@Composable
fun Profil(
    navController: NavController? = null,
    profilViewModel: profilViewModel,
    eventViewModel: eventViewModel,
    eventInvitedViewModel: eventInvitedViewModel,
    eventFinanciersViewModel: eventFinanciersViewModel,
    favoriteViewModel: favoriteViewModel,
    localStorage: LocalStorage
) {
    val page = remember { mutableStateOf(0) }
    val userProfil : MutableState<ProfilEntity> = remember { mutableStateOf(ProfilEntity(0, null, "", "", "") ) }
    val coroutineScope = rememberCoroutineScope()

    // Get the user profil
    if (localStorage.username != "") {
        LaunchedEffect(Dispatchers.Main) { // Launch the coroutine in the main thread
            val user = coroutineScope.async { // Launch the coroutine in the coroutineScope
                profilViewModel.getByUsername(localStorage.username) // Get the user profil
            }
            try { // Try to get the user profil
                userProfil.value = user.await()!! // Get the user profil
            } catch (e: Exception) { // Catch the exception
                Log.d("Profil", "Error : " + e.message)
            }
        }
    }

    Column( // Column that fill the entire screen
        modifier = Modifier
            .fillMaxSize(),
    ) {

        /*------------------------------- Profil container -------------------------------*/
        Row ( // Row that contains the element of the user profil
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(IvoryColor))
        ){
            Box ( // Surround the image with a box to add a padding
                modifier = Modifier
                    .padding(8.dp)
            ){
                Image( // Image of the user profil
                    painter = painterResource(id = R.drawable.default_pfp_do_brazil), /* TODO Faire en sorte de mettre l'image de l'utilisateur */
                    contentDescription = "Profil picture",
                    contentScale = ContentScale.Crop, // Crop the image to fit the circle
                    modifier = Modifier
                        .clip(CircleShape) // Clip the image to a circle
                        .border(
                            4.dp,
                            Color(GreenVariantStrongColor),
                            CircleShape
                        ) // Add a border to the image
                        .size(100.dp) // Size of the image
                )
            }
            Column (//user infos
                horizontalAlignment = Alignment.CenterHorizontally, // Align the column to the center
                modifier = Modifier
                    .fillMaxWidth() // Fill the entire width of the screen
                    .padding(8.dp),
            ){
                if (userProfil.value.username != "" && userProfil.value.email != "") {
                    Text(userProfil.value.username + "") // Username of the user

                    Text(userProfil.value.email + "") // Email of the user
                }else {
                    Text("Username") // Username of the user

                    Text("Email") // Email of the user
                }
            }
        }



        Box(
            modifier = Modifier
                .fillMaxSize() // Fill the entire size of the screen
                .background(Color(GreenVariantStrongColor)) // Background color of the Box
        ) {
            Image(
                painter = painterResource(R.drawable.lac_do_brazil), // Image for background
                contentDescription = null, // Background picture of the image
                contentScale = ContentScale.Crop, // Crop the image to fit the container
                modifier = Modifier.fillMaxSize()// Add a border to the image
            )
            Column{//contains the categories container, the events and the bottom bar


                Column(//column that contains the categories container and the events
                    modifier = Modifier
                        .fillMaxWidth() // Fill the entire width of the screen
                        .fillMaxHeight(0.90f) // Fill 90% of the height of the screen
                        .padding(10.dp)
                ){
                    //BottomBorder(width = 6.dp, color = Color(IvoryBorderColor))
                    /*------------------------------- Categories container -------------------------------*/
                    Row (
                        modifier = Modifier
                            .fillMaxWidth() // Fill the entire width of the screen
                            .background(Color(GreenVariantColor).copy(alpha = Opacity)), // Background color of the row
                        horizontalArrangement = Arrangement.SpaceEvenly // Space the element evenly
                    ){
                        // Button to show the event that are soon
                        CategorieButton(onClick = {page.value = 0}, R.drawable.categorie_soon, Modifier.weight(1f))

                        // Button to show the event that are currently
                        CategorieButton(onClick = {page.value = 1}, R.drawable.categorie_party, Modifier.weight(1f))

                        // Button to show the your favorite people
                        CategorieButton(onClick = {page.value = 2}, R.drawable.categorie_favorite, Modifier.weight(1f))

                        // they are all weighted to take the same space
                    }
                    BottomBorder(width = 6.dp, color = Color(IvoryBorderColor))

                    /*------------------------------- BACKEND -------------------------------*/
                    var listEventHostMutable : List<EventEntity> by remember { mutableStateOf(listOf()) } // List of event
                    var listEventInvitedMutable : List<EventEntity> by remember { mutableStateOf(listOf()) } // List of event
                    var listFavoritePeopleMutable : List<ProfilEntity> by remember { mutableStateOf(listOf()) } // List of favorite people

                    LaunchedEffect(Dispatchers.Main) { // Launch the coroutine in the main thread
                        // Get the event joined by the user
                        val eventHost = async {eventViewModel.getEventsByHost(localStorage.idUser!!)}.await()

                        // Get the event made by the user
                        val eventInvited = async {eventViewModel.getEventsWhereUserIsInvited(localStorage.idUser!!)}.await()

                        // Get the favorite people of the user
                        val favoritePeople = async {profilViewModel.getFriendsProfil(localStorage.idUser!!)}.await()

                        withContext(Dispatchers.Main) { // Change the context to the main thread
                            listEventHostMutable = eventHost
                            listEventInvitedMutable = eventInvited
                            listFavoritePeopleMutable = favoritePeople
                        }
                    }


                    /*------------------------------- List of the category -------------------------------*/
                    LazyColumn {
                        when (page.value) {
                            0 -> { // If the user wants to see the event that he joined
                                items(listEventInvitedMutable) { event ->
                                    Event(
                                        eventId = event.idEvent!!,
                                        profilViewModel = profilViewModel,
                                        eventViewModel = eventViewModel,
                                        eventInvitedViewModel = eventInvitedViewModel,
                                        financiersViewModel = eventFinanciersViewModel,
                                        localStorage = localStorage,
                                        navController = navController
                                    )
                                    Spacer(modifier = Modifier.padding(8.dp))
                                }
                            }
                            1 -> { // If the user wants to see the event that are made by him
                                items(listEventHostMutable) { event ->
                                    Event(
                                        eventId = event.idEvent!!,
                                        profilViewModel = profilViewModel,
                                        eventViewModel = eventViewModel,
                                        eventInvitedViewModel = eventInvitedViewModel,
                                        financiersViewModel = eventFinanciersViewModel,
                                        localStorage = localStorage,
                                        navController = navController
                                    )
                                    Spacer(modifier = Modifier.padding(8.dp))
                                }
                            }
                            2 -> { // If the user wants to see the favorite people
                                items(listFavoritePeopleMutable) { people ->
                                    People(
                                        peopleEntity = people,
                                        favoriteViewModel = favoriteViewModel,
                                        localStorage = localStorage
                                    )
                                    Spacer(modifier = Modifier.padding(8.dp))
                                }
                            }
                        }
                    }
                }


                Row( // Row that contains the bottom bar
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier
                        .fillMaxWidth() // Fill the entire width of the screen
                        .fillMaxHeight() // Fill the entire height remaining of the screen
                        .background(Color(GreenVariantColor))
                ){
                    Icon (
                        Icons.Default.Home,
                        contentDescription = "Home icon",
                        modifier = Modifier
                            .size(40.dp)
                            .clickable { navController?.navigate("HomeScreen") }
                    )
                }
            }
        }



    }
}

/**
 * @brief Composable that modelise categorie button
 * @param categorieIcon : Int that represent the icon of the categorie
 * @param onClick : Function that represent the action when the button is clicked
 * @param modifier : Modifier of the button
 */
@Composable
fun CategorieButton(onClick: () -> Unit, categorieIcon: Int, modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(categorieIcon), // Icon of the categorie
        contentDescription = "", // Description of the image
        modifier = modifier // Modifier of the image
            .clickable { onClick() } // Add a clickable action to the image
            .border(4.dp, Color(GreenVariantStrongColor)) // Add a border to the image
    )
}


/**
 * @brief Composable that moodelise people that you put in favorite
 * @param peopleEntity : ProfilEntity that represent the people
 * @param eventViewModel : eventViewModel that represent the eventViewModel
 * @param eventInvitedViewModel : eventInvitedViewModel that represent the eventInvitedViewModel
 * @param favoriteViewModel : favoriteViewModel that represent the favoriteViewModel
 * @param localStorage : LocalStorage that represent the localStorage
 */
@Composable
fun People(
    peopleEntity: ProfilEntity,
    favoriteViewModel: favoriteViewModel,
    localStorage: LocalStorage
) {
    Row ( // Row that contains the element of the people
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .border(4.dp, Color(GreenVariantStrongColor)) // Add a border to the row
            .background(Color(GreenVariantColor)) // Background color of the row
            .fillMaxWidth() // Fill the entire width of the screen
            .padding(8.dp)  // Add a padding to the row
    ){
        Image(
                painter = painterResource(R.drawable.default_pfp_do_brazil), // Image of the people
                /* TODO Remplacer par l'image de la personne */
                contentDescription = "",
                contentScale = ContentScale.FillBounds, // Fill the entire space of the image
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp)) // Clip the image to a rounded corner
                    .border(
                        2.dp,
                        Color(GreenVariantStrongColor),
                        RoundedCornerShape(8.dp)
                    ) // Add a border to the image
                    .size(70.dp)
        )

        Text(peopleEntity.username) // Name of the people

        Box {
            Icon( // Delete button
                Icons.Default.Delete,
                contentDescription = "Delete",
                modifier = Modifier
                    .clickable(onClick = {
                        favoriteViewModel.viewModelScope.launch(Dispatchers.Main) {
                            favoriteViewModel.deleteWithUsernames(localStorage.username, peopleEntity.username) // Insert the contact in the favorite list
                        }
                    }) // Make the icon clickable
            )
        }

    }
}


/**
 * @brief Composable that allow to preview the profil page
 */
@Preview(showBackground = true)
@Composable
fun ProfilPreview() {
    DoBrazilTheme {
        Profil(profilViewModel = hiltViewModel(), eventViewModel = hiltViewModel(), eventInvitedViewModel = hiltViewModel(), eventFinanciersViewModel = hiltViewModel(), favoriteViewModel = hiltViewModel(), localStorage = LocalStorage(""))
    }
}