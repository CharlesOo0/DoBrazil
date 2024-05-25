package com.example.dobrazil

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

/**
 * @brief Composable that allow to the profil page
 */
@Composable
fun Profil() {
    val scrollState = rememberScrollState()
    val page = remember { mutableStateOf(0) }

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
                        .size(140.dp) // Size of the image
                )
            }

            Column (
                horizontalAlignment = Alignment.CenterHorizontally, // Align the column to the center
                modifier = Modifier
                    .fillMaxWidth() // Fill the entire width of the screen
                    .padding(8.dp),
            ){

                Icon (
                    Icons.Default.Home,
                    contentDescription = "Home icon",
                    modifier = Modifier
                        .size(40.dp)
                        .clickable { /* TODO allow to go to the menu*/ }
                )

                Spacer(modifier = Modifier.padding(10.dp))

                Text("Nom de l'utilisateur") // Username of the user
                /* TODO Remplacer par le nom de l'utilisateur */

                Spacer(modifier = Modifier.padding(10.dp))

                Text("Email de l'utilisateur") // Email of the user
                /* TODO Remplacer par l'email de l'utilisateur */
            }


        }

        BottomBorder(width = 6.dp, color = Color(IvoryBorderColor))

        /*------------------------------- Categorie container -------------------------------*/
        Row (
            modifier = Modifier
                .fillMaxWidth() // Fill the entire width of the screen
                .background(Color(GreenVariantColor)), // Background color of the row
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

        /*------------------------------- List of the category -------------------------------*/
        Column (
            modifier = Modifier
                .fillMaxSize() // Fill the entire size of the screen remaining
                .background(Color(IvoryColor)) // Background color of the column
                .verticalScroll(scrollState) // Add a scroll to the column to see all the element
                .padding(8.dp),
        ){
            if (page.value == 0) { // If the user want to see the event that are made by him / coming soon
                for (i in 0..10) {
                    People()
                    Spacer(modifier = Modifier.padding(8.dp))
                }
            } else if (page.value == 1) { // If the user want to see the event that are made by him / currently
                Event()
            } else { // If the user want to see the favorite people
                People()
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
 * @brief Composable that modelise an event
 * @param eventId : Int that represent the id of the event
 */
@Composable
fun Event(eventId: Int = 0) {
    Row ( // Row that contains the element of the event
        modifier = Modifier
            .border(4.dp, Color(GreenVariantStrongColor)) // Add a border to the row
            .background(Color(GreenVariantColor)) // Background color of the row
            .fillMaxWidth() // Fill the entire width of the screen
            .padding(8.dp)  // Add a padding to the row
            .height(200.dp) // Height of the row
    ){
        Column ( // Column that contains the element the picture of the event
            modifier = Modifier
                .fillMaxWidth(0.35f)
                .fillMaxHeight()
                .padding(4.dp)
        ){
            Image(
                painter = painterResource(R.drawable.event_default_pfp), // Image of the event
                /* TODO Remplacer par l'image de l'événement */
                contentDescription = "",
                contentScale = ContentScale.FillBounds, // Fill the entire space of the image
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp)) // Clip the image to a rounded corner
                    .border(2.dp, Color(GreenVariantStrongColor), RoundedCornerShape(8.dp)) // Add a border to the image
            )

        }

        Column ( // Column that contains the element descripting the event
            verticalArrangement = Arrangement.SpaceEvenly, // Space the element inside evenly
            modifier = Modifier
                .fillMaxSize() // Fill the entire size of the column
                .fillMaxHeight()
        ){
            Row( // Row that contains the name, location and number of person of the event
                horizontalArrangement = Arrangement.SpaceEvenly, // Space the element inside evenly
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.15f)
            ){
                Text("XXX") // Name of the event
                /* TODO Remplacer par le nom de l'événement */
                Text("Lieu") // Location of the event
                /* TODO Remplacer par le lieu de l'événement */
                Text("X pers.") // Number of person that are going to the event
                /* TODO Remplacer par le nombre de personne qui vont à l'événement */
                Box {
                    DropDownMenu()
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
            }
        }
    }

}

/**
 * @brief Composable that moodelise people that you put in favorite
 * @param peopleId : Int that represent the id of the people
 */
@Composable
fun People(peopleId: Int = 0) {
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
                    .border(2.dp, Color(GreenVariantStrongColor), RoundedCornerShape(8.dp)) // Add a border to the image
                    .size(70.dp)
        )

        Text("Nom de la personne") // Name of the people
        /* TODO Remplacer par le nom de la personne */

        Box {
            DropDownMenu()
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
 * @brief Composable that allow to preview the profil page
 */
@Preview(showBackground = true)
@Composable
fun ProfilPreview() {
    DoBrazilTheme {
        Profil()
    }
}