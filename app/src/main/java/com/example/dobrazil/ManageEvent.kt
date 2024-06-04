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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dobrazil.ui.theme.DoBrazilTheme
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.layout.ContentScale
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.appwithroomuv.R
import com.example.dobrazil.Entity.ProfilEntity
import com.example.dobrazil.data.LocalStorage
import com.example.dobrazil.viewModel.eventFinanciersViewModel
import com.example.dobrazil.viewModel.eventInvitedViewModel
import com.example.dobrazil.viewModel.eventViewModel
import com.example.dobrazil.viewModel.expenseViewModel
import com.example.dobrazil.viewModel.favoriteViewModel
import com.example.dobrazil.viewModel.profilViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * @brief ManageEvent composable that modelise the ManageEvent screen
 */
@Composable
fun ManageEvent(
    navController: NavController? = null,
    eventViewModel: eventViewModel,
    eventInvitedViewModel: eventInvitedViewModel,
    eventFinanciersViewModel: eventFinanciersViewModel,
    favoriteViewModel: favoriteViewModel,
    profilViewModel: profilViewModel,
    expenseViewModel: expenseViewModel,
    localStorage: LocalStorage,
    eventTitle : String = ""
) {
    Column ( // Column that contains the screen
        modifier = Modifier
            .fillMaxSize()
            .background(Color(IvoryColor))
    ){

        var page = remember { mutableStateOf(0) }
        /*------------------------------- Categorie container -------------------------------*/
        Row (
            modifier = Modifier
                .fillMaxWidth() // Fill the entire width of the screen
                .background(Color(GreenVariantColor)), // Background color of the row
            horizontalArrangement = Arrangement.SpaceEvenly // Space the element evenly

        ){
            // Button to show the event that are soon
            CategorieButton(onClick = {page.value = 0}, R.drawable.categorie_information, Modifier.weight(1f))

            // Button to show the event that are currently
            CategorieButton(onClick = {page.value = 1}, R.drawable.categorie_add, Modifier.weight(1f))

            // Button to show the your favorite people
            CategorieButton(onClick = {page.value = 2}, R.drawable.categorie_delete, Modifier.weight(1f))

            // Button to show the your favorite people
            CategorieButton(onClick = {navController?.navigate("BudgetScreen")}, R.drawable.categorie_budget, Modifier.weight(1f))
            /* TODO Make it navigate to the correct budget screen */
            // they are all weighted to take the same space
        }

        BottomBorder(width = 3.dp, color = Color(BeigeVariant)) // Bottom border of the row

        /*------------------------------- Top bar -------------------------------*/
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
                    .clickable(onClick = { navController?.navigate("HomeScreen") }) // Make the icon clickable
                    .padding(8.dp)
            )

            var textTopBar : String = ""

            if (page.value == 0) { // If we want to see the event information
                textTopBar = "Event Information"
            } else if (page.value == 1) { // If we want to add people to the event
                textTopBar = "Add people to the event"
            } else if (page.value == 2) { // If we want to delete people from the event
                textTopBar = "Delete people from the event"
            } else if (page.value == 3) { // If we want to see the budget of the event
                textTopBar = "Budget"
            }

            Text(
                text = textTopBar,
                color = Color.White,
                modifier = Modifier.padding(8.dp)
            ) // Title

        }

        BottomBorder(width = 4.dp, color = Color(GreenVariantStrongColor)) // Border between top bar and form

        /*------------------------------- BACKEND -------------------------------*/

        val eventIdMutable = remember { mutableStateOf(0) } // Id of the event

        LaunchedEffect(Dispatchers.Main) { // Get the event
            val event = eventViewModel.getByTitle(eventTitle) // Get the event by its title
            withContext(Dispatchers.Main) { // Switch to the main thread
                eventIdMutable.value = event.idEvent!! // Set the id of the event
            }
        }

        /*------------------------------- Content -------------------------------*/

        if (page.value == 0) { // If we want to see the event information
            EventInformation(
                eventViewModel = eventViewModel,
                eventInvitedViewModel = eventInvitedViewModel,
                profilViewModel = profilViewModel,
                favoriteViewModel = favoriteViewModel,
                localStorage = localStorage,
                eventTitle = eventTitle
            )
        } else if (page.value == 1) { // If we want to add people to the event
            AddPeople(
                eventInvitedViewModel = eventInvitedViewModel,
                eventFinanciersViewModel = eventFinanciersViewModel,
                profilViewModel = profilViewModel,
                expenseViewModel = expenseViewModel,
                localStorage = localStorage,
                eventId = eventIdMutable.value
            )
        } else { // If we want to delete people from the event
            DeletePeople(
                eventInvitedViewModel = eventInvitedViewModel,
                eventFinanciersViewModel = eventFinanciersViewModel,
                profilViewModel = profilViewModel,
                expenseViewModel = expenseViewModel,
                eventId = eventIdMutable.value
            )
        }
    }
}

/**
 * @brief Composable that allow to display EventInformation
 */
@Composable
fun EventInformation(
    eventViewModel: eventViewModel,
    eventInvitedViewModel: eventInvitedViewModel,
    profilViewModel: profilViewModel,
    favoriteViewModel: favoriteViewModel,
    localStorage: LocalStorage,
    eventTitle : String
) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .fillMaxWidth()
    ){
        var eventMutable = remember { mutableStateOf(nullEvent) } // Event that will be displayed
        var nbPersMutable = remember { mutableStateOf(0) } // Number of people that are invited
        var peopleInvitedMutable = remember { mutableStateOf(listOf<ProfilEntity>()) } // People that are invited

        LaunchedEffect(Dispatchers.Main) { // Get the event
            val event = eventViewModel.getByTitle(eventTitle) // Get the event by its title
            val nbPers = eventInvitedViewModel.getNumberOfPersonGoing(event.idEvent!!) // Get the number of people that are invited
            val peopleInvited = profilViewModel.getInvitedProfil(event.idEvent) // Get the people that are invited

            withContext(Dispatchers.Main) { // Switch to the main thread
                eventMutable.value = event // Set the event
                nbPersMutable.value = nbPers // Set the number of people
                peopleInvitedMutable.value = peopleInvited // Set the people that are invited
            }
        }
        Row (
            modifier = Modifier
                .fillMaxWidth()
        ){
            Column (
                modifier = Modifier
                    .height(170.dp),
                verticalArrangement = Arrangement.Center,
            ){ // Column that contains the picture of the event
                Image (
                    painter = painterResource(R.drawable.event_default_pfp),
                    // TODO query to get the image of the event
                    contentDescription = "Event image",
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(0.25f)
                        .border(2.dp, Color.Black, RoundedCornerShape(8.dp))
                        .clip(RoundedCornerShape(8.dp))
                )
            }

            Column( // Column that contains the information of the event
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxWidth()
            ) {
                Row ( // Row that contains the title, location and number of people
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth()
                ){
                    Text(eventMutable.value.title) // Title of the event
                    Text(eventMutable.value.location) // Location of the event
                    Text( nbPersMutable.value.toString() + "Pers")

                }

                Spacer(modifier = Modifier.size(4.dp))

                Row ( // Row that contains the date start and date end
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth()
                ){
                    Text(eventMutable.value.startDate)
                    Text(eventMutable.value.endDate)
                }

                Box( // Box that contains the description
                    modifier = Modifier
                        .fillMaxWidth() // Fill the entire width of the screen
                        .border(1.dp, Color.Black, RoundedCornerShape(3.dp)) // Border of the box
                        .clip(RoundedCornerShape(3.dp)) // Rounded corner of the box
                        .height(100.dp) // Height of the box
                        .padding(2.dp)
                ) {
                    Text(eventMutable.value.description)
                }
            }
        }
        
        Spacer(modifier = Modifier.size(12.dp))

        LazyColumn( // Column that contains the invited people
            horizontalAlignment = Alignment.CenterHorizontally, // Center the element horizontally
            modifier = Modifier
                .fillMaxWidth() // Fill the entire width of the screen
                .padding(8.dp)
        ){
            items(peopleInvitedMutable.value.size) { index ->
                val person = peopleInvitedMutable.value[index]
                People(
                    peopleEntity = person,
                    favoriteViewModel = favoriteViewModel,
                    localStorage = localStorage
                )
            }
        }
    }
}

/**
 * @brief Composable that allow to add people to the event
 */
@Composable
fun AddPeople(
    eventInvitedViewModel: eventInvitedViewModel,
    eventFinanciersViewModel: eventFinanciersViewModel,
    profilViewModel: profilViewModel,
    expenseViewModel: expenseViewModel,
    localStorage: LocalStorage,
    eventId: Int
) {
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
                    listSearch.value = async { profilViewModel.searchNotInvitedProfil(search.value, localStorage.idUser!!, eventId) }.await()
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
    LazyColumn(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxSize()
    ) {
        items(listSearch.value.size) { index -> // For each profil in the list
            val profil = listSearch.value[index]
            ContactManageEvent(
                name = profil.username,
                mode = 0,
                eventInvitedViewModel = eventInvitedViewModel,
                eventFinanciersViewModel = eventFinanciersViewModel,
                profilViewModel = profilViewModel,
                expenseViewModel = expenseViewModel,
                eventId = eventId
            )
            Spacer(modifier = Modifier.size(4.dp))
        }
    }
}

/**
 * @brief Composable that allow to delete people from the event
 * @param eventInvitedViewModel: eventInvitedViewModel that contains the eventInvitedViewModel
 * @param eventFinanciersViewModel: eventFinanciersViewModel that contains the eventFinanciersViewModel
 * @param profilViewModel: profilViewModel that contains the profilViewModel
 * @param expenseViewModel: expenseViewModel that contains the expenseViewModel
 * @param eventId: Int that contains the id of the event
 */
@Composable
fun DeletePeople(
    eventInvitedViewModel: eventInvitedViewModel,
    eventFinanciersViewModel: eventFinanciersViewModel,
    profilViewModel: profilViewModel,
    expenseViewModel: expenseViewModel,
    eventId: Int
) {
    val peopleMutable = remember { mutableStateOf(listOf<ProfilEntity>()) } // People that are invited

    LaunchedEffect(Dispatchers.Main) { // Get the people
        val people = profilViewModel.getInvitedProfil(eventId) // Get the people that are invited
        withContext(Dispatchers.Main) {
            peopleMutable.value = people // Set the people
        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxHeight() // Fill the entire height of the screen
            .fillMaxWidth() // Fill the entire width of the screen
            .padding(8.dp)
    ) {
        items(peopleMutable.value.size) { index ->
            val person = peopleMutable.value[index]
            ContactManageEvent(
                name = person.username,
                mode = 1,
                eventInvitedViewModel = eventInvitedViewModel,
                eventFinanciersViewModel = eventFinanciersViewModel,
                profilViewModel = profilViewModel,
                expenseViewModel = expenseViewModel,
                eventId = eventId
            )
            Spacer(modifier = Modifier.size(4.dp))
        }
    }
}

/**
 * @brief Composable that modelise a contact
 * @param name: String that contains the name of the contact
 * @param mode: Int that contains the mode of the contact
 * @param eventInvitedViewModel: eventInvitedViewModel that contains the eventInvitedViewModel
 * @param eventFinanciersViewModel: eventFinanciersViewModel that contains the eventFinanciersViewModel
 * @param profilViewModel: profilViewModel that contains the profilViewModel
 * @param expenseViewModel: expenseViewModel that contains the expenseViewModel
 * @param eventId: Int that contains the id of the event
 */
@Composable
fun ContactManageEvent(
    name: String = "",
    mode: Int = 0,
    eventInvitedViewModel: eventInvitedViewModel,
    eventFinanciersViewModel: eventFinanciersViewModel,
    profilViewModel: profilViewModel,
    expenseViewModel: expenseViewModel,
    eventId: Int
) {
    var invited = remember { mutableStateOf(false) } // State of the invitation
    var idTargetProfil = remember { mutableStateOf(0) } // Id of the target profil

    LaunchedEffect(Dispatchers.Main) { // Get the profil
        val profil =  profilViewModel.getByUsername(name) // Get the profil by its username
        withContext(Dispatchers.Main) { // Switch to the main thread
            idTargetProfil.value = profil?.idProfil!! // Set the id of the target profil
        }
    }


    Row ( // Row that contains the element of the people
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .background(Color(IvoryColor).copy(alpha = Opacity)) // Background color of the row
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

        Text(name) // Name of the people

        if (mode == 0) { // If we are in add mode
            Icon( // Add button
                Icons.Default.Add,
                contentDescription = "Add",
                modifier = Modifier
                    .clickable(onClick = {
                        eventInvitedViewModel.insertWithUsernames(eventId, name)
                    }) // Make the icon clickable
            )
        } else {
            Icon( // Delete button
                Icons.Default.Delete,
                contentDescription = "Delete",
                modifier = Modifier
                    .clickable(onClick = {
                        eventInvitedViewModel.deleteWithUsernames(eventId, name)
                        eventFinanciersViewModel.deleteWithUsernames(eventId, name)
                        expenseViewModel.deleteAllExpenseTargetEventProfil(idTargetProfil.value, eventId)
                    }) // Make the icon clickable
            )

        }


    }
}

/**
 * @brief Preview of the ManageEvent screen
 */
@Preview(showBackground = true)
@Composable
fun ManageEventPreview() {
    DoBrazilTheme {
        ManageEvent(eventViewModel = hiltViewModel(), eventInvitedViewModel = hiltViewModel(), favoriteViewModel = hiltViewModel(), profilViewModel = hiltViewModel(), expenseViewModel = hiltViewModel(), eventFinanciersViewModel = hiltViewModel(), localStorage = LocalStorage())
    }
}