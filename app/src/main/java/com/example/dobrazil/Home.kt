package com.example.dobrazil

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dobrazil.ui.theme.DoBrazilTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircleOutline
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.ui.Alignment
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.appwithroomuv.R
import com.example.dobrazil.Entity.EventEntity
import com.example.dobrazil.data.LocalStorage
import com.example.dobrazil.viewModel.eventInvitedViewModel
import com.example.dobrazil.viewModel.eventViewModel
import com.example.dobrazil.viewModel.profilViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * @brief Composable that allow to modelise the Home page
 * @param navController : NavController that allow to navigate between the screen
 * @param eventViewModel : eventViewModel that allow to interact with the event
 * @param profilViewModel : profilViewModel that allow to interact with the profil
 * @param eventInvitedViewModel : eventInvitedViewModel that allow to interact with the eventInvited
 * @param localStorage : LocalStorage that allow to interact with the local storage
 */
@Composable
fun Home(
    navController: NavController? = null,
    eventViewModel: eventViewModel,
    profilViewModel: profilViewModel,
    eventInvitedViewModel: eventInvitedViewModel,
    localStorage: LocalStorage
){
    val scrollState = rememberScrollState()

    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(Color(GreenVariantColor))
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(GreenVariantColor))
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo_do_brazil),
                contentDescription = "Logo Do Brazil",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
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

            Column {

                var listEventMutable : List<EventEntity> by remember { mutableStateOf(listOf()) } // List of event
                LaunchedEffect(Dispatchers.Main) { // Launch the effect
                    val listEvent = eventViewModel.getEventsWhereNotInvitedOrCreator(localStorage.idUser!!)// Get the list of event

                    withContext(Dispatchers.Main) { // Change the context to the main thread
                        listEventMutable = listEvent // Update the list of event
                    }
                }

                LazyColumn( // Column that contains the events
                    modifier = Modifier
                        .fillMaxWidth() // Fill the entire width of the screen
                        .fillMaxHeight(0.90f) // Fill 90% of the height of the screen
                        .padding(10.dp)
                ) {
                    items(listEventMutable.size) { index -> // For each event in the list
                        Event( // Show the event
                            eventViewModel = eventViewModel,
                            profilViewModel = profilViewModel,
                            eventInvitedViewModel = eventInvitedViewModel,
                            localStorage = localStorage,
                            eventId = listEventMutable.get(index).idEvent!!,
                            forProfil = false,
                            navController = navController
                        )
                        Spacer(modifier = Modifier.size(10.dp)) // Add a space between the events
                    }
                }

                // Border between the events and the bottom bar
                BottomBorder(width = 4.dp, color = Color(IvoryBorderColor))

                Row( // Row that contains the bottom bar
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier
                        .fillMaxWidth() // Fill the entire width of the screen
                        .fillMaxHeight() // Fill the entire height remaining of the screen
                        .background(Color(GreenVariantColor))
                ) {
                    Icon( // Icon to create an event
                        Icons.Default.AddCircleOutline,
                        contentDescription = "Create an event",
                        modifier = Modifier
                            .size(40.dp)
                            .clickable { navController?.navigate("CreateEventScreen") }
                    )

                    Icon( // Icon to search for people
                        Icons.Default.Star,
                        contentDescription = "Search people",
                        modifier = Modifier
                            .size(40.dp)
                            .clickable { navController?.navigate("SearchFriendScreen") }
                    )

                    Icon( // Icon to go to the profil page
                        Icons.Default.Person,
                        contentDescription = "Profil page",
                        modifier = Modifier
                            .size(40.dp)
                            .clickable { navController?.navigate("ProfilScreen") }
                    )
                }
            }
        }
    }
}

/**
 * @brief Composable that modelise an event
 * @param eventId : Int that represent the id of the event
 * @param forProfil : Boolean that represent if the event is for the profil page
 * @param navController : NavController that allow to navigate between the screen
 * @param eventViewModel : eventViewModel that allow to interact with the event
 * @param profilViewModel : profilViewModel that allow to interact with the profil
 * @param eventInvitedViewModel : eventInvitedViewModel that allow to interact with the eventInvited
 * @param localStorage : LocalStorage that allow to interact with the local storage
 */
@Composable
fun Event(
    eventViewModel: eventViewModel,
    profilViewModel: profilViewModel,
    eventInvitedViewModel: eventInvitedViewModel,
    localStorage: LocalStorage,
    eventId: Int = 0,
    forProfil: Boolean = true,
    navController: NavController? = null
) {
    var eventMutable : EventEntity by remember { mutableStateOf(EventEntity(null, "", "", 0, "", "", "", false, false)) }

    LaunchedEffect(Dispatchers.IO){
        val eventGet = eventViewModel.getById(eventId)

        withContext(Dispatchers.Main){
            eventMutable = eventGet
        }
    }

    Row ( // Row that contains the element of the event
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp)) // Clip the container to a rounded corner
            .background(Color(IvoryColor).copy(alpha = 0.9f)) // Background color of the row
            .fillMaxWidth() // Fill the entire width of the screen
            .padding(18.dp)  // Add a padding to the row
            .height(200.dp) // Height of the row
    ){
        Column ( // Column that contains the element the picture of the event
            modifier = Modifier
                .fillMaxWidth(0.35f)
                .fillMaxHeight()
                .padding(4.dp)
        ){
            Image(
                painter = painterResource(R.drawable.lac_do_brazil), // Image of the event
                /* TODO Remplacer par l'image de l'événement */
                contentDescription = "",
                contentScale = ContentScale.FillBounds, // Fill the entire space of the image
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp)) // Clip the image to a rounded corner
                    .border(2.dp, Color(GreenVariantStrongColor), RoundedCornerShape(8.dp)) // Add a border to the image
            )

        }
        Spacer(modifier = Modifier.size(10.dp))

        Column ( // Column that contains the element descripting the event
            verticalArrangement = Arrangement.SpaceEvenly, // Space the element inside evenly
            modifier = Modifier
                .fillMaxSize() // Fill the entire size of the column
                .fillMaxHeight()
        ){
            Row( // Row that contains the name, location and number of person of the event
                horizontalArrangement = Arrangement.SpaceBetween, // Space the element inside evenly
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.15f)
            ){
                Text(eventMutable.title) // Name of the event

                Text(eventMutable.location) // Location of the event


                val numberPersMutable : MutableState<Int> = remember { mutableStateOf(0) }
                LaunchedEffect(Dispatchers.IO) {
                    val numberPerson = eventInvitedViewModel.getNumberOfPersonGoing(eventId)

                    withContext(Dispatchers.Main){
                        numberPersMutable.value = numberPerson
                    }
                }
                Text(numberPersMutable.value.toString() + " Pers") // Number of person going to the event

                if (forProfil && eventMutable.idHost == localStorage.idUser) { // If the event is for the profil page
                    Box {
                        DropDownMenu(navController = navController, eventViewModel = eventViewModel, event = eventMutable, isCreator = true)
                    }
                } else { // If the event is not for the profil page
                    Icon ( // Icon to participate to the event
                        Icons.Default.Add,
                        contentDescription = "Participer",
                        modifier = Modifier
                            .size(20.dp)
                            .clickable { // When the user click on the icon
                                eventInvitedViewModel.insertWithUsernames(eventId, localStorage.username)
                            }
                    )
                }
            }

            Row ( // Row that contains the description of the event
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.85f)
                    .border(1.dp, Color(GreenVariantStrongColor))
            ){
                Text(eventMutable.description) // Description of the event
            }

            Row ( // Row that contains the date of the event
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            ){
                Text(eventMutable.startDate) // Date of the beginning of the event

                Text(eventMutable.endDate) // Date of the end of the event


                if (forProfil) { // If the event is for the profil page
                    if (eventMutable.isPrivate) { //
                        Text("Private")
                    } else {
                        Text("Public")
                    }
                }
            }
        }
    }

}

/**
 * @brief Composable that modelise a DropDownMenu
 * @param isCreator : Boolean that represent if the user is the creator of the event
 * @param isEvent : Boolean that represent if the dropDownMenu is for an event
 * @param navController : NavController that allow to navigate between the screen
 */
@Composable
fun DropDownMenu(
    isCreator : Boolean = false,
    isEvent : Boolean = true,
    navController: NavController? = null,
    eventViewModel: eventViewModel,
    event: EventEntity
){
    var expanded by remember { mutableStateOf(false) }

    Box { // Box that contains the DropDownMenu
        IconButton(onClick = { expanded = !expanded }) { // Button that show the DropDownMenu
            Icon( // Icon of the button
                imageVector = Icons.Default.MoreVert,
                contentDescription = "More"
            )
        }

        DropdownMenu( // DropDownMenu that is shown when the button is clicked
            expanded = expanded,
            onDismissRequest = { expanded = false } // Dismiss the DropDownMenu when the user click outside of it
        ) {
            if (isEvent) { // If the DropDownMenu is for an event
                if (isCreator) {  // If the user is the creator of the event
                    DropdownMenuItem( // Show the option to manage the event
                        text = { Text("Gerer") },
                        onClick = { navController?.navigate("ManageEvent") } /* TODO Make it navigate to the correct Event */
                    )
                }
                DropdownMenuItem( // Show the option to delete/quit the event
                    text = { Text("Supprimer/Quitter") },
                    onClick = { /* TODO */ }
                )
            } else {
                DropdownMenuItem( // Show the option to delete the people from the favorite
                    text = { Text("Supprimer") },
                    onClick = { /* TODO */ }
                )
            }

        }
    }
}

/**
 * @brief Composable that allow to preview the Home page
 */
@Preview(showBackground = true)
@Composable
fun HomePreview() {
    DoBrazilTheme {
        Home(profilViewModel = hiltViewModel(), eventViewModel = hiltViewModel(), eventInvitedViewModel = hiltViewModel(), localStorage = LocalStorage())
    }
}

