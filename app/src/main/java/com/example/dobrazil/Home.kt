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
import androidx.compose.foundation.layout.size
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController

/**
 * @brief Composable that allow to modelise the Home page
 */
@Composable
fun Home(navController: NavController? = null) {
    val scrollState = rememberScrollState()


        Box(
            modifier = Modifier
                .fillMaxSize() // Fill the entire size of the screen
                .background(Color(GreenVariantStrongColor)) // Background color of the Box
                .padding(8.dp) // Padding around the Box (acts as margin)
        ) {
            Image(
                painter = painterResource(R.drawable.lac_do_brazil), // Image of the event
                contentDescription = null, // Background picture of the image
                contentScale = ContentScale.Crop, // Crop the image to fit the container
                modifier = Modifier.fillMaxSize()// Add a border to the image
            )

            Column {

                Row( // Row that contain the logo of the application
                    verticalAlignment = Alignment.CenterVertically, // Align the element vertically
                    horizontalArrangement = Arrangement.SpaceEvenly, // Space the element evenly
                    modifier = Modifier
                        .fillMaxWidth() // Fill the entire width of the screen
                        .background(Color(GreenVariantColor)) // Background color of the row
                ) {
                    Image(
                        // Logo of DoBrazil
                        painter = painterResource(id = R.drawable.logo_do_brazil),
                        contentDescription = "Logo Do Brazil",
                        modifier = Modifier
                            .fillMaxWidth() // Fill the entire width of the screen
                            .padding(16.dp) // Add a padding to the image
                    )
                }

                Column( // Column that contains the events
                    modifier = Modifier
                        .fillMaxWidth() // Fill the entire width of the screen
                        .fillMaxHeight(0.90f) // Fill 90% of the height of the screen
                        .verticalScroll(scrollState) // Add a scroll to the column to see all the element
                        .padding(10.dp)
                ) {
                    for (i in 0..10) { // For each event
                        Event(forProfil = false) // Show the event
                        Spacer(modifier = Modifier.size(10.dp)) // Add a space between each event
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
                            .size(50.dp)
                            .clickable { /* TODO allow to create an event*/ }
                    )

                    Icon( // Icon to search for people
                        Icons.Default.Star,
                        contentDescription = "Search people",
                        modifier = Modifier
                            .size(50.dp)
                            .clickable { /* TODO allow to search for people */ }
                    )

                    Icon( // Icon to go to the profil page
                        Icons.Default.Person,
                        contentDescription = "Profil page",
                        modifier = Modifier
                            .size(50.dp)
                            .clickable { /* TODO allow to go to the profil*/ }
                    )
                }
            }
        }
}

/**
 * @brief Composable that modelise an event
 * @param eventId : Int that represent the id of the event
 * @param forProfil : Boolean that represent if the event is for the profil page
 */
@Composable
fun Event(eventId: Int = 0, forProfil: Boolean = true) {
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
                Text("Titre") // Name of the event
                /* TODO Remplacer par le nom de l'événement */
                Text("Lieu") // Location of the event
                /* TODO Remplacer par le lieu de l'événement */
                Text("X pers.") // Number of person that are going to the event
                /* TODO Remplacer par le nombre de personne qui vont à l'événement */

                if (forProfil) { // If the event is for the profil page
                    Box {
                        DropDownMenu()
                    }
                } else {
                    Icon (
                        Icons.Default.Add,
                        contentDescription = "Participer",
                        modifier = Modifier
                            .size(25.dp)
                            .clickable { /* TODO allow to participate to an event*/ }
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
                Text("Description") // Description of the event
                /* TODO Remplacer par la description de l'événement */
            }

            Row ( // Row that contains the date of the event
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            ){
                Text("Date de début") // Date of the beginning of the event
                /* TODO Remplacer par la date de début de l'événement */
                Text("Date de fin") // Date of the end of the event
                /* TODO Remplacer par la date de fin de l'événement */

                if (forProfil) { // If the event is for the profil page
                    Text("O/F") // Statut de l'evenement
                    /* TODO Remplacer par le statut de l'evenement */
                }
            }
        }
    }

}

/**
 * @brief Composable that modelise a DropDownMenu
 * @param isCreator : Boolean that represent if the user is the creator of the event
 * @param isEvent : Boolean that represent if the dropDownMenu is for an event
 */
@Composable
fun DropDownMenu(isCreator : Boolean = false, isEvent : Boolean = true) {
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
                        onClick = { /* TODO */ }
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
        Home()
    }
}