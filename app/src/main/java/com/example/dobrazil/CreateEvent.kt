package com.example.dobrazil

import android.app.DatePickerDialog
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Title
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dobrazil.ui.theme.DoBrazilTheme
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.appwithroomuv.R
import com.example.dobrazil.data.LocalStorage
import com.example.dobrazil.viewModel.eventViewModel
import androidx.lifecycle.viewModelScope
import com.example.dobrazil.Entity.EventEntity
import com.example.dobrazil.Entity.EventInvitedCrossRef
import com.example.dobrazil.Entity.FavoriteRel
import com.example.dobrazil.Entity.ProfilEntity
import com.example.dobrazil.viewModel.eventInvitedViewModel
import com.example.dobrazil.viewModel.favoriteViewModel
import com.example.dobrazil.viewModel.profilViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * @brief Function that verify the information for creating Event
 * @param title : String the title of the event
 * @param description : String the description of the event
 * @param location : String the location of the event
 * @param dateStart : String the start date of the event
 * @param dateEnd : String the end date of the event
 * @param private : Boolean the privacy of the event
 * @param inviteFavorite : Boolean the invitation of favorite person
 * @param navController : NavController the navigation controller
 * @param error : MutableState<String> the error message
 * @param eventViewModel : eventViewModel the event view model
 * @param profilViewModel : profilViewModel the profil view model
 * @param favoriteViewModel : favoriteViewModel the favorite view model
 * @param eventInvitedViewModel : eventInvitedViewModel the event invited view model
 * @param localStorage : LocalStorage the local storage
 */
fun verifyCreateEvent(
    title: String,
    description: String,
    location: String,
    dateStart: String,
    dateEnd: String,
    private: Boolean,
    inviteFavorite: Boolean,
    navController: NavController ?= null,
    error: MutableState<String>,
    eventViewModel: eventViewModel,
    profilViewModel: profilViewModel,
    favoriteViewModel: favoriteViewModel,
    eventInvitedViewModel: eventInvitedViewModel,
    localStorage: LocalStorage
) {
    if (title.isEmpty() || description.isEmpty() || location.isEmpty() || dateStart.isEmpty() || dateEnd.isEmpty()) { // If one of the field is empty
        error.value = "Please fill all the fields" // Error message
        return
    }

    eventViewModel.viewModelScope.launch(Dispatchers.Main) { // Launch a coroutine
        val profil : ProfilEntity? = profilViewModel.getByUsername(localStorage.username) // Get the profil
        var idHost = 0

        if (profil?.idProfil != null) { // If the profil is null
            idHost = profil.idProfil // Get the id of the profil
        }

        val event : EventEntity = EventEntity(null, dateStart, dateEnd, idHost, location, title, description, private, inviteFavorite) // Create the event
        eventViewModel.insert(event) // Create the event and get the inserted event id

        if (inviteFavorite) { // If the event owner want to invite favorite person
            favoriteViewModel.viewModelScope.launch(Dispatchers.Main) {
                if (localStorage.idUser != null) {
                    // Get the favorite list
                    val favoriteList : List<FavoriteRel> = favoriteViewModel.getByFollower(localStorage.idUser!!) // Get the favorite list

                    for (favorite in favoriteList) { // For each favorite
                        val eventInvitedCrossRef = EventInvitedCrossRef(event.idEvent!!, favorite.idFollow)
                        eventInvitedViewModel.insert(eventInvitedCrossRef) // Invite the favorite person
                    }
                }
            }
        }
    }
}

/**
 * @brief Composable that allow to modelize the create event screen
 */
@Composable
fun CreateEvent(
    navController: NavController? = null,
    eventViewModel: eventViewModel,
    profilViewModel: profilViewModel,
    favoriteViewModel: favoriteViewModel,
    eventInvitedViewModel: eventInvitedViewModel,
    localStorage: LocalStorage
) {
    Column ( // Column that contains the screen
        modifier = Modifier
            .background(Color(IvoryColor)) // Background color
            .fillMaxSize(), // Full size
    ){
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            Image(//background
                painter = painterResource(R.drawable.fond_lac_do_brazil),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()// Add a border to the image
            )

            Column{
                Row( // Top bar
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .background(Color(IvoryColor).copy(alpha = Opacity))
                        .fillMaxWidth()
                        .height(70.dp)
                ) {
                    Icon( // Back button
                        Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        modifier = Modifier
                            .size(45.dp)
                            .clickable(onClick = { navController?.popBackStack() }) // Make the icon clickable
                            .padding(8.dp)
                    )

                    Text(
                        text = "Budget",
                        fontSize = 20.sp,
                        color = Color(GreenVariantStrongColor),
                        modifier = Modifier
                            .padding(8.dp)
                    ) // Title

                }


                BottomBorder(width = 3.dp, color = Color(IvoryBorderColor).copy(alpha = Opacity)) // Border between top bar and form
                Spacer(modifier = Modifier.padding(8.dp)) // Space between the input fields

                Column ( // Column that contains the form
                    modifier = Modifier
                        .padding(8.dp)
                        .verticalScroll(rememberScrollState()) // Make the column scrollable
                ){ // Create Event Form
                    val title = remember { mutableStateOf("") } // Title of the event
                    val description = remember { mutableStateOf("") } // Description of the event
                    val location = remember { mutableStateOf("") } // Location of the event
                    val dateStart = remember { mutableStateOf("") } // Start date of the event
                    val dateEnd = remember { mutableStateOf("") } // End date of the event
                    val private = remember { mutableStateOf(false) } // Private event
                    val inviteFavoritePerson = remember { mutableStateOf(false) } // Invite favorite person

                    val error = remember { mutableStateOf("") } // Error message

                    if (error.value != "") { // If there is an error
                        Text(text = error.value, color = Color.Red) // Display the error
                        Spacer(modifier = Modifier.height(16.dp)) // Add a space of 16dp
                    }

                    // Input field for the title
                    CustomInputField(textState = title, label = "Title", icon = { Icon(Icons.Default.Title, contentDescription = "Title icon") })

                    // Input field for the description
                    CustomInputField(textState = description, label = "Description", icon = { Icon(Icons.Default.Description, contentDescription = "Description icon") })

                    // Input field for the location
                    CustomInputField(textState = location, label = "Location", icon = { Icon(Icons.Default.LocationOn, contentDescription = "Location icon") })

                    Spacer(modifier = Modifier.padding(8.dp)) // Space between the input fields

                    Column(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color(IvoryColor).copy(alpha = Opacity))
                            .padding(8.dp)
                    ){

                        Row (//chose the dates
                            modifier = Modifier
                                .padding(8.dp)
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ){
                            CustomDatePicker(dateStart, "Start Date", "From") // Date picker for the start date
                            CustomDatePicker(dateEnd, "End Date", "To") // Date picker for the end date
                        }

                        Spacer(modifier = Modifier.padding(8.dp)) // Space between the input fields

                        Row(//chose to set the event as private or not
                            modifier = Modifier.fillMaxWidth(), // Full width
                            horizontalArrangement = Arrangement.Absolute.Left, // Center horizontally
                            verticalAlignment = Alignment.CenterVertically // Center vertically

                        ){
                            Icon(Icons.Default.Lock, contentDescription = "Privacy Icon", modifier = Modifier.padding(4.dp)) // Icon for the privacy
                            Text(text = "Private Event", modifier = Modifier.padding(4.dp)) // Label
                            BooleanInputField(private) // Boolean input field for the privacy
                        }

                        Row(//chose to invite Favorite persons or not
                            modifier = Modifier.fillMaxWidth(), // Full width
                            horizontalArrangement = Arrangement.Absolute.Left, // Center horizontally
                            verticalAlignment = Alignment.CenterVertically // Center vertically

                        ){
                            Icon(Icons.Default.Star, contentDescription = "Star Icon", modifier = Modifier.padding(4.dp)) // Icon for the privacy
                            Text(text = "Invite Favorite Person", modifier = Modifier.padding(4.dp)) // Label
                            BooleanInputField(inviteFavoritePerson) // Boolean input field for the privacy
                        }

                        Spacer(modifier = Modifier.padding(8.dp)) // Space between the input fields

                        // Button to go to next step
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            contentAlignment = Alignment.CenterEnd
                        ){
                            Button( // Button
                                onClick = { verifyCreateEvent(title.value, description.value, location.value, dateStart.value, dateEnd.value, private.value, inviteFavoritePerson.value, navController, error, eventViewModel, profilViewModel, favoriteViewModel, eventInvitedViewModel, localStorage) }, // Verify the information
                                modifier = Modifier
                                    .padding(8.dp) // Padding
                            ){
                                Text(
                                    text = "Next",
                                    color = Color(BeigeVariant)
                                ) // Text of the button

                            }
                        }

                    }

                }

            }
        }
    }
}



/**
 * @brief Composable that allow to  make a boolean input field
 * @param switchState : MutableState<Boolean> the state of the input field
 */
@Composable
fun BooleanInputField(switchState: MutableState<Boolean>) {
    Switch( // Switch input field
        checked = switchState.value, // Checked value
        onCheckedChange = { switchState.value = it }, // Change the value
        modifier = Modifier.padding(16.dp) // Padding
    )
}

/**
 * @brief Composable that allow to make a custom datepicker
 * @param dateState : MutableState<String> the state of the input field
 * @param text : String the text of the input field
 * @param label : String the label of the input field
 */
@Composable
fun CustomDatePicker(dateState: MutableState<String> = mutableStateOf(""), text: String = "Date", label: String = "Date") {
    val context = LocalContext.current // Get the context
    var dialogVisible by remember { mutableStateOf(false) } // Dialog visibility
    var selectedDate by remember { mutableStateOf(Calendar.getInstance()) } // Selected date

    if (dialogVisible) { // If the dialog is visible
        AlertDialog(  // Create the dialog
            onDismissRequest = { dialogVisible = false }, // Dismiss the dialog
            title = { Text(text = text) }, // Title
            text = { // Text
                DatePickerDialog(  // Create the date picker dialog
                    context,
                    { _, year, month, dayOfMonth ->
                        selectedDate.set(year, month, dayOfMonth) // Set the selected date
                        val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()) // Date format
                        dateState.value = sdf.format(selectedDate.time) // Set the date but for the page
                        dialogVisible = false // Dismiss the dialog
                    },
                    selectedDate.get(Calendar.YEAR),
                    selectedDate.get(Calendar.MONTH),
                    selectedDate.get(Calendar.DAY_OF_MONTH)
                ).show() // Show the dialog
            },
            confirmButton = { } // No confirm button
        )
    }

    Row( // Row that contains the input field
        verticalAlignment = Alignment.CenterVertically, // Center vertically
        modifier = Modifier
            .clickable { dialogVisible = true } // Make the row clickable
    ){
        IconButton( // Icon button
            onClick = { dialogVisible = true }, // Show the dialog when clicked
        ) {
            Icon(
                imageVector = Icons.Default.DateRange, // Icon its a calendar
                contentDescription = "Calendar Icon"
            )
        }

        Text(text = label + " " + dateState.value) // Label that show the date picked
    }

}

/**
 * @brief Composable that allow to custom input field
 * @param textState : MutableState<String> the state of the input field
 * @param label : String the label of the input field
 * @param icon : ImageVector the icon of the input field
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomInputField(textState: MutableState<String>, label: String,  icon: (@Composable () -> Unit)? = null) {
    TextField( // Custom input field
        leadingIcon = icon, // Icon
        label = { Text(label) }, // Label
        value = textState.value,
        onValueChange = { textState.value = it },
        modifier = Modifier.fillMaxWidth() // Full width
    )
}


/**
 * @brief Composable that allow to preview the login screen
 */
@Preview(showBackground = true)
@Composable
fun CreateEventPreview() {
    DoBrazilTheme {
        CreateEvent(eventViewModel = hiltViewModel(), profilViewModel = hiltViewModel(), favoriteViewModel = hiltViewModel(), eventInvitedViewModel = hiltViewModel(), localStorage = LocalStorage(""))
    }
}

