package com.example.dobrazil

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.Alignment
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.appwithroomuv.R

/**
 * @brief ManageEvent composable that modelise the ManageEvent screen
 */
@Composable
fun ManageEvent(
    navController: NavController? = null,
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


        /*------------------------------- Content -------------------------------*/

        if (page.value == 0) { // If we want to see the event information
            EventInformation()
        } else if (page.value == 1) { // If we want to add people to the event
            AddPeople()
        } else { // If we want to delete people from the event
            DeletePeople()
        }
    }
}

/**
 * @brief Composable that allow to display EventInformation
 */
@Composable
fun EventInformation() {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .fillMaxWidth()
    ){
        var scroll = rememberScrollState()

        /* TODO query to get the event information */
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
                    Text("Title")
                    Text("Location")
                    Text("X Peop")
                    /* TODO query to get info */
                }

                Spacer(modifier = Modifier.size(4.dp))

                Row ( // Row that contains the date start and date end
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth()
                ){
                    Text("Date start")
                    Text("Date end")
                    /* TODO query to get info */
                }

                Box( // Box that contains the description
                    modifier = Modifier
                        .fillMaxWidth() // Fill the entire width of the screen
                        .border(1.dp, Color.Black, RoundedCornerShape(3.dp)) // Border of the box
                        .clip(RoundedCornerShape(3.dp)) // Rounded corner of the box
                        .height(100.dp) // Height of the box
                        .padding(2.dp)
                ) {
                    Text("Description")
                    /* TODO query to get info */
                }
            }
        }
        
        Spacer(modifier = Modifier.size(12.dp))

        Column( // Column that contains the invited people
            horizontalAlignment = Alignment.CenterHorizontally, // Center the element horizontally
            modifier = Modifier
                .fillMaxWidth() // Fill the entire width of the screen
                .verticalScroll(scroll) // Allow the column to be scrollable
                .padding(8.dp)
        ){
            People()
            /* TODO query to get info */
        }

    }
}

/**
 * @brief Composable that allow to display AddPeople
 */
@Composable
fun AddPeople() {
    /* TODO query to get every people from the event */
    Column(
        modifier = Modifier
            .fillMaxHeight() // Fill the entire height of the screen
            .fillMaxWidth() // Fill the entire width of the screen
            .padding(8.dp)
    ) {
        for (i in 0..10) {
            Contact(mode = 1, favoriteViewModel = hiltViewModel(), eventInvitedViewModel = hiltViewModel(), eventViewModel = hiltViewModel())
            Spacer(modifier = Modifier.size(4.dp))
        }
    }
}

/**
 * @brief Composable that allow to display DeletePeople
 */
@Composable
fun DeletePeople() {
    /* TODO query to get every people from the event */
    Column(
        modifier = Modifier
            .fillMaxHeight() // Fill the entire height of the screen
            .fillMaxWidth() // Fill the entire width of the screen
            .padding(8.dp)
    ) {
        for (i in 0..10) {
            Contact(mode = 2, favoriteViewModel = hiltViewModel(), eventInvitedViewModel = hiltViewModel(), eventViewModel = hiltViewModel())
            Spacer(modifier = Modifier.size(4.dp))
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
        ManageEvent()
    }
}