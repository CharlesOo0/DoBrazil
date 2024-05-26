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
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
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
import com.example.dobrazil.ui.theme.DoBrazilTheme

/**
 * @brief SearchFriend composable that modelise the SearchFriend screen
 */
@Composable
fun SearchFriend() {
    Column ( // Column that contains the screen
        modifier = Modifier
            .background(Color(IvoryColor)) // Background color
            .fillMaxSize(), // Full size
    ) {
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
                text = "Search Friends",
                color = Color.White,
                modifier = Modifier.padding(8.dp)
            ) // Title

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
            Contact(add = true)
        }
    }
}

/**
 * @brief Preview that allow to see the SearchFriend screen
 */
@Preview(showBackground = true)
@Composable
fun SearchFriendPreview() {
    DoBrazilTheme {
        SearchFriend()
    }
}