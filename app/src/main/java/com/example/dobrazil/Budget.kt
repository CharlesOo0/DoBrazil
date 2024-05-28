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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dobrazil.ui.theme.DoBrazilTheme

/**
 * @brief Budget composable that modelise the Budget screen
 */
@Composable
fun Budget() {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(Color(IvoryColor)),
    ) {
        var page = remember { mutableStateOf(0) }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            Image(
                painter = painterResource(R.drawable.fond_lac_do_brazil),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()// Add a border to the image
            )
            Column {
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
                            .clickable(onClick = { /*TODO*/ }) // Make the icon clickable
                            .padding(8.dp)
                    )

                    Text(
                        text = "Budget",
                        color = Color.White,
                        modifier = Modifier.padding(8.dp)
                    ) // Title

                }

                BottomBorder(width = 3.dp, color = Color(IvoryBorderColor))

                /*------------------------------- Categorie container -------------------------------*/
                Row (
                    modifier = Modifier
                        .fillMaxWidth() // Fill the entire width of the screen
                        .background(Color(GreenVariantColor)), // Background color of the row
                    horizontalArrangement = Arrangement.SpaceEvenly // Space the element evenly

                ){
                    // Button to show the event that are soon
                    CategorieButton(onClick = {page.value = 0}, R.drawable.categorie_history, Modifier.weight(1f))

                    // Button to show the event that are currently
                    CategorieButton(onClick = {page.value = 1}, R.drawable.categorie_balance, Modifier.weight(1f))

                }
            }
        }

        /*------------------------------- Content -------------------------------*/

        if (page.value == 0) { // Show the history of finance
            /* TODO query to get finance history */
            Finance()
        } else { // Show the balance between people
            /* TODO query to get balance between people */
            Balance()
        }

    }
}

/**
 * @brief Composable for a finance
 * @param financeId : Int, the id of the finance
 */
@Composable
fun Finance(financeId: Int = 0) {
    Row (
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxWidth() // Fill the entire width of the screen
            .padding(8.dp)
            .border(3.dp, Color(GreenVariantStrongColor), RoundedCornerShape(8.dp)) // Border
            .clip(RoundedCornerShape(8.dp)) // Rounded corner
            .background(Color(GreenVariantColor)), // Background color

    ) {

        Text(
            text = "Titre",
            color = Color.Black,
            modifier = Modifier.padding(8.dp)
        )

        Text(
            text = "XX Montant",
            color = Color.Black,
            modifier = Modifier.padding(8.dp)
        )

        Text(
            text = "XX Date",
            color = Color.Black,
            modifier = Modifier.padding(8.dp)
        )
    }

}

/**
 * @brief Composable balance
 * @param personneId : Int, the id of the balance
 */
@Composable
fun Balance(personneId: Int = 0) {
    Row (
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxWidth() // Fill the entire width of the screen
            .padding(8.dp)
            .border(3.dp, Color(GreenVariantStrongColor), RoundedCornerShape(8.dp)) // Border
            .clip(RoundedCornerShape(8.dp)) // Rounded corner
            .background(Color(GreenVariantColor)), // Background color

    ) {

        var balance = remember { mutableStateOf(0) }

        if (balance.value > 0) { // If the balance is negative
            Row (
                horizontalArrangement = Arrangement.SpaceBetween, // Space the element evenly
                modifier = Modifier
                    .fillMaxWidth() // Fill the entire width of the screen
                    .padding(8.dp)
                    .border(3.dp, Color(IvoryBorderColor), RoundedCornerShape(8.dp)) // Border
                    .clip(RoundedCornerShape(8.dp)) // Rounded corner
                    .background(Color(IvoryColor)), // Background color
            ){

                Text( // Text of the balance
                    text = "Person -" + balance.value.toString() + "€",
                    color = Color.Red,
                    modifier = Modifier.padding(8.dp)
                )

                Spacer(modifier = Modifier.padding(1.dp)) // Space between the text

                Spacer(modifier = Modifier.padding(1.dp)) // Space between the text
            }

        } else { // If the balance is positive
            Row {
                Spacer(modifier = Modifier.padding(1.dp)) // Space between the text

                Spacer(modifier = Modifier.padding(1.dp)) // Space between the text

                Text( // Text of the balance
                    text = "Personne +" + balance.value.toString() + "€",
                    color = Color.Green,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }

}

/**
 * @brief Preview of the Budget screen
 */
@Preview(showBackground = true)
@Composable
fun BudgetPreview() {
    DoBrazilTheme {
        Budget()
    }
}