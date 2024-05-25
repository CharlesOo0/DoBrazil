package com.example.dobrazil

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.foundation.layout.size


/**
 * @brief Composable that allow to the profil page
 */
@Composable
fun Profil() {
    val scrollState = rememberScrollState()

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
                        .clip(CircleShape)
                        .border(4.dp, Color(GreenVariantStrongColor), CircleShape) // Add a border to the image
                        .size(140.dp)
                )
            }

            Column (
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
            ){
                Text("Nom de l'utilisateur") // Username of the user

                Spacer(modifier = Modifier.padding(10.dp))

                Text("Email de l'utilisateur") // Firstname of the user
            }

        }

        BottomBorder(width = 6.dp, color = Color(IvoryBorderColor))

        /*------------------------------- Categorie container -------------------------------*/
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(GreenVariantColor))
        ){
            Text("Catégorie")
        }

        BottomBorder(width = 6.dp, color = Color(IvoryBorderColor))

        /*------------------------------- List of the category -------------------------------*/
        Column (
            modifier = Modifier
                .fillMaxSize()
                .background(Color(IvoryColor))
                .verticalScroll(scrollState),
        ){
            Text("Les trucs de la catégorie")

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