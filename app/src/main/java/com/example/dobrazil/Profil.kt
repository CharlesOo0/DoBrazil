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
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavController

/**
 * @brief Composable that allow to modelise the profil page
 */
@Composable
fun Profil(navController: NavController? = null) {
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
                        .border(4.dp,Color(GreenVariantStrongColor),CircleShape) // Add a border to the image
                        .size(100.dp) // Size of the image
                )
            }
            Column (//user infos
                horizontalAlignment = Alignment.CenterHorizontally, // Align the column to the center
                modifier = Modifier
                    .fillMaxWidth() // Fill the entire width of the screen
                    .padding(8.dp),
            ){
                //Spacer(modifier = Modifier.padding(10.dp))
                Text("Nom de l'utilisateur") // Username of the user
                /* TODO Remplacer par le nom de l'utilisateur */
                //Spacer(modifier = Modifier.padding(10.dp))
                Text("Email de l'utilisateur") // Email of the user
                /* TODO Remplacer par l'email de l'utilisateur */
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
                        .verticalScroll(scrollState) // Add a scroll to the column to see all the element
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

                        // Button to show the your favorite people
                        CategorieButton(onClick = {page.value = 3}, R.drawable.categorie_invitation, Modifier.weight(1f))

                        // they are all weighted to take the same space
                    }
                    BottomBorder(width = 6.dp, color = Color(IvoryBorderColor))


                    /*------------------------------- List of the category -------------------------------*/
                    Column (
                        modifier = Modifier
                            .fillMaxSize() // Fill the entire size of the screen remaining
                            .background(Color(IvoryColor).copy(alpha = Opacity)) // Background color of the column
                            .verticalScroll(scrollState) // Add a scroll to the column to see all the element
                            .padding(8.dp),
                    ){
                        if (page.value == 0) { // If the user want to see the event that are made by him / coming soon
                            for (i in 0..10) {
                                Event()
                                Spacer(modifier = Modifier.padding(8.dp))
                            }
                        } else if (page.value == 1) { // If the user want to see the event that are made by him / currently
                            Event()
                        } else if (page.value == 2 ) { // If the user want to see the favorite people
                            for (i in 0..10) {
                                People()
                                Spacer(modifier = Modifier.padding(8.dp))
                            }
                        } else {
                            Invitation()
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
 * @brief Composable that modelise an invitation to an Event
 * @param eventId : Int that represent the id of the event
 */
@Composable
fun Invitation(eventId: Int = 0) {
    Column ( // Row that contains the element of the event
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .border(4.dp, Color(GreenVariantStrongColor)) // Add a border to the row
            .background(Color(GreenVariantColor)) // Background color of the row
            .fillMaxWidth() // Fill the entire width of the screen
            .padding(8.dp)  // Add a padding to the row
    ){
        Text("You are invited to an event") // Text that show that the user is invited to an event

        Spacer(modifier = Modifier.padding(8.dp))

        Event(eventId, false) // Show the event that the user is invited to

        Spacer(modifier = Modifier.padding(8.dp))

        Row {
            Button(onClick = { /* TODO */ }) { // Button to accept the invitation
                Text("Accept") // Text of the button
            }

            Spacer(modifier = Modifier.padding(8.dp))

            Button(onClick = { /* TODO */ }) { // Button to refuse the invitation
                Text("Deny") // Text of the button
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
                    .border(
                        2.dp,
                        Color(GreenVariantStrongColor),
                        RoundedCornerShape(8.dp)
                    ) // Add a border to the image
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
 * @brief Composable that allow to preview the profil page
 */
@Preview(showBackground = true)
@Composable
fun ProfilPreview() {
    DoBrazilTheme {
        Profil()
    }
}