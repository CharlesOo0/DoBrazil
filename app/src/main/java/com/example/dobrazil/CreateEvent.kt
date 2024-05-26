package com.example.dobrazil

import android.app.DatePickerDialog
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Title
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dobrazil.ui.theme.DoBrazilTheme
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.window.Dialog
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

/**
 * @brief Composable that allow to modelize the create event screen
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateEvent() {
    Column (
        modifier = Modifier
            .background(Color(IvoryColor))
            .fillMaxSize(),
    ){
        Row ( // Top bar
            modifier = Modifier
                .background(Color(GreenVariantColor))
                .fillMaxWidth(),
        ){
            Icon (
                Icons.Default.ArrowBack,
                contentDescription = "Back",
                modifier = Modifier
                    .clickable(onClick = { /*TODO*/ })
                    .padding(8.dp)
            )

            Text(text = "Create Event", color = Color.White, modifier = Modifier.padding(8.dp))

        }

        BottomBorder(width = 3.dp, color = Color(IvoryBorderColor))

        Column { // Create Event Form
            val title = remember { mutableStateOf("") }
            val description = remember { mutableStateOf("") }
            val location = remember { mutableStateOf("") }
            val dateStart = rememberDatePickerState()
            val dateEnd = rememberDatePickerState()

            CustomInputField(textState = title, label = "Title", icon = { Icon(Icons.Default.Title, contentDescription = "Title icon") })
            CustomInputField(textState = description, label = "Description", icon = { Icon(Icons.Default.Description, contentDescription = "Description icon") })
            CustomInputField(textState = location, label = "Location", icon = { Icon(Icons.Default.LocationOn, contentDescription = "Location icon") })

            Row {
                DatePicker(state = dateStart)
                DatePicker(state = dateEnd)
            }

        }

        Row {

        }
    }
}

/**
 * @brief Composable that allow to custom input field
 * @param textState : MutableState<String> the state of the input field
 * @param label : String the label of the input field
 * @param icon : ImageVector the icon of the input field
 */
@Composable
fun CustomInputField(textState: MutableState<String>, label: String,  icon: (@Composable () -> Unit)? = null) {
    TextField(
        leadingIcon = icon,
        label = { Text(label) },
        value = textState.value,
        onValueChange = { textState.value = it },
        modifier = Modifier.fillMaxWidth()
    )
}


/**
 * @brief Composable that allow to preview the login screen
 */
@Preview(showBackground = true)
@Composable
fun CreateEventPreview() {
    DoBrazilTheme {
        CreateEvent()
    }
}

