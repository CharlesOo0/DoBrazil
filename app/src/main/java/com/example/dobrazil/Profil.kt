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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember


/**
 * @brief Composable that allow to the profil page
 */
@Composable
fun Profil() {
    val scrollState = rememberScrollState()
    val currentlySoon = remember { mutableStateOf(true) }

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
            CategorieButton(onClick = {currentlySoon.value = true}, R.drawable.categorie_soon, Modifier.weight(1f))

            // Button to show the event that are currently
            CategorieButton(onClick = {currentlySoon.value = false}, R.drawable.categorie_party, Modifier.weight(1f))

            // Both of them are weighted to take the same space
        }

        BottomBorder(width = 6.dp, color = Color(IvoryBorderColor))

        /*------------------------------- List of the category -------------------------------*/
        Column (
            modifier = Modifier
                .fillMaxSize()
                .background(Color(IvoryColor))
                .verticalScroll(scrollState),
        ){
            if (currentlySoon.value) {
                Text("Bientôt disponible")
            } else {
                Text("Liste des événements")
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
fun Event(eventId: Int) {

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