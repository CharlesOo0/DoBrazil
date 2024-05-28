package com.example.dobrazil

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.dobrazil.ui.theme.DoBrazilTheme

/**
 * @brief ChoseInvited composable that modelise the ChoseInvited screen
 */
@Composable
fun ChoseInvited(navController: NavController? = null) {
    Column ( // Column that contains the screen
        modifier = Modifier
            .background(Color(IvoryColor)) // Background color
            .fillMaxSize(), // Full size
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            // Top bar
            modifier = Modifier
                .background(Color(GreenVariantColor))
                .fillMaxWidth(),
        ) {
            Icon( // Back button
                Icons.Default.ArrowBack,
                contentDescription = "Back",
                modifier = Modifier
                    .clickable(onClick = { navController?.popBackStack() }) // Make the icon clickable
                    .padding(8.dp)
            )

            Text(
                text = "Chose invited people",
                color = Color.White,
                modifier = Modifier.padding(8.dp)
            ) // Title

            Icon( // Front button
                Icons.Default.ArrowForward,
                contentDescription = "Front",
                modifier = Modifier
                    .clickable(onClick = { navController?.navigate("ManageEventScreen") }) // Make the icon clickable
                    /* TODO Make it go to the correct Event */
                    .padding(8.dp)
            )

        }

        BottomBorder(
            width = 3.dp,
            color = Color(IvoryBorderColor)
        ) // Border between top bar and form

        val search = remember { mutableStateOf("") } // Search value

        // Search bar
        SearchBar(
            value = search.value, // Connect the search state to the SearchBar
            onValueChange = { search.value = it }, // Update the search state when the value changes
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            hint = "Search for contacts"
        )

        val scrollState = rememberScrollState() // Scroll state

        // List of contacts
        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            // TODO Query the search value to get the contacts and print them
            Contact(mode = 1)
        }
    }
}

/**
 * @brief SearchBar composable that modelise the search bar
 * @param value: String that contains the value of the search bar
 * @param onValueChange: (String) -> Unit that contains the function to change the value of the search bar
 * @param modifier: Modifier that contains the style of the search bar
 * @param hint: String that contains the hint of the search bar
 * @param onSearchExecute: () -> Unit that contains the function to execute when the search is done

 */
@Composable
fun SearchBar(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    hint: String = "Search",
    onSearchExecute: () -> Unit = {}
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        placeholder = { Text(text = hint) },
        // Keyboard options
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(onSearch = { onSearchExecute() }),
        // Leading icon
        leadingIcon = { Icon(Icons.Filled.Search, contentDescription = "Search Icon") },
        singleLine = true
    )
}

/**
 * @brief Composable that modelise a contact
 * @param name: String that contains the name of the contact
 * @param avatar: String that contains the avatar of the contact
 * @param mode: Int if its 0 its the invite mode, if its 1 its the add mode, if its 2 its the delete mode
 */
@Composable
fun Contact(name: String = "", avatar: String = "", mode: Int = 0) {
    var invited = remember { mutableStateOf(false) } // State of the invitation

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

        if (mode == 0) { // If we are in add mode
            Icon( // Add button
                Icons.Default.Add,
                contentDescription = "Add",
                modifier = Modifier
                    .clickable(onClick = { /*TODO*/ }) // Make the icon clickable
            )
        } else if (mode == 1) { // If we are in invite mode
            Box {
                BooleanInputField(switchState = invited) // Switch to invite the people
            }
        } else {
            Icon( // Delete button
                Icons.Default.Delete,
                contentDescription = "Delete",
                modifier = Modifier
                    .clickable(onClick = { /*TODO*/ }) // Make the icon clickable
            )

        }


    }
}

/**
 * @brief Preview of ChoseInvited
 */
@Preview(showBackground = true)
@Composable
fun ChoseInvitedPreview() {
    DoBrazilTheme {
        ChoseInvited()
    }
}