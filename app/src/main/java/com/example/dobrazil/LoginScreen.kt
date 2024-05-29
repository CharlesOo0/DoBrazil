package com.example.dobrazil

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dobrazil.ui.theme.DoBrazilTheme
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.Color
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.IconButton
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.navigation.NavController
import androidx.compose.runtime.mutableStateOf


/**
 * @brief Function onclick that represent a register
 * @param username : String : the username of the user
 * @param email : String : the email of the user
 * @param password : String : the password of the user
 * @param confirmPassword : String : the confirmation of the password of the user
 * @param navController : NavController? : the navigation controller
 * @param error : MutableState<String> : the error message
 */
fun register(username : String, email : String, password : String, confirmPassword : String, navController: NavController? = null, error : MutableState<String>) {
    // Check if the fields are empty
    if (!username.isNotEmpty() && !email.isNotEmpty() && !password.isNotEmpty() && !confirmPassword.isNotEmpty()) {
        error.value = "All fields must be filled"
        return
    }

    // Check if username is between 8 and 32 characters
    if (username.length < 8 || username.length > 32) {
        error.value = "The username must be between 8 and 32 characters"
        return
    }

    /* TODO check if username is already taken */

    // Check if the email is valid
    if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
        error.value = "The email is not valid"
        return
    }

    /* TODO check if email is already taken */

    // Check if the password is between 8 and 32 characters
    if (password.length < 8 || password.length > 32) {
        error.value = "The password must be between 8 and 32 characters"
        return
    }

    // Check if the password and the confirm password are the same
    if (password != confirmPassword) {
        error.value = "The password and the confirm password are not the same"
        return
    }

    navController?.navigate("HomeScreen")
}

/**
 * @brief Function onclick that represent a login
 * @param username : String : the username of the user
 * @param password : String : the password of the user
 * @param navController : NavController? : the navigation controller
 * @param error : MutableState<String> : the error message
 */
fun login(username : String, password : String, navController: NavController? = null, error : MutableState<String>) {
    // Check if the fields are empty
    if (!username.isNotEmpty() && !password.isNotEmpty()) {
        error.value = "All fields must be filled"
        return
    }

    /* TODO check if username and password are correct */

    navController?.navigate("HomeScreen")
}

/**
 * @brief Composable that allow to modelize the login / register screen
 */
@Composable
fun LoginScreen(navController: NavController? = null) {
    val isLogin = remember { mutableStateOf(true) }
    val error = remember { mutableStateOf("") }

    Column (
        modifier = Modifier // Fill the screen
            .fillMaxSize()
            .background(Color(GreenVariantColor))
    ){

        // Variables login
        val usernameLogin = remember { mutableStateOf("") }
        val passwordLogin = remember { mutableStateOf("") }

        // Variables register
        val username = remember { mutableStateOf("") }
        val email = remember { mutableStateOf("") }
        val password = remember { mutableStateOf("") }
        val confirmPassword = remember { mutableStateOf("") }

        /*------------------------------- TOP of the screen -------------------------------*/
        Row (
            modifier = Modifier // Fill the width and set up a background color
                .fillMaxWidth()
                .background(Color(GreenVariantColor)),
        ){ // Top part of the screen where the logo is displayed
            // Display the logo of the app
            Image(
                painter = painterResource(id = R.drawable.logo_do_brazil),
                contentDescription = "Logo Do Brazil",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
        }

        BottomBorder(width = 6.dp, color = Color(IvoryBorderColor)) // Display a bottom border

        /*------------------------------- MIDDLE of the screen -------------------------------*/

        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier // Fill the width and set up a background color
                .fillMaxWidth()
                .background(Color(IvoryColor)),
        ){ // Bottom part of the screen where the login / register form is displayed
            Spacer(modifier = Modifier.height(16.dp)) // Add a space of 16dp

            if (error.value != "") { // If there is an error
                Text(text = error.value, color = Color.Red) // Display the error
                Spacer(modifier = Modifier.height(16.dp)) // Add a space of 16dp
            }

            if (isLogin.value) { // If we are in login mode

                InputField( // Input field for the username
                    label = "Username",
                    onValueChange = { newValue -> usernameLogin.value = newValue },
                    isPassword = false,
                    icon = { Icon(Icons.Default.AccountCircle, contentDescription = "Email icon") }
                )

                InputField( // Input field for the password
                    label = "Password",
                    onValueChange = { newValue -> passwordLogin.value = newValue },
                    isPassword = true,
                    icon = { Icon(Icons.Default.Lock, contentDescription = "Email icon") }
                )

            }else {
                // Define the state for each input field
                InputField( // Input field for the username
                    label = "Username",
                    onValueChange = { newValue -> username.value = newValue },
                    isPassword = false,
                    icon = { Icon(Icons.Default.AccountCircle, contentDescription = "Email icon") }
                )

                InputField( // Input field for the email
                    label = "Email",
                    onValueChange = { newValue -> email.value = newValue },
                    isPassword = false,
                    icon = { Icon(Icons.Default.Email, contentDescription = "Email icon") }
                )

                InputField( // Input field for the password
                    label = "Password",
                    onValueChange = { newValue -> password.value = newValue },
                    isPassword = true,
                    icon = { Icon(Icons.Default.Lock, contentDescription = "Email icon") }
                )

                InputField( // Input field for the password confirmation
                    label = "Confirm password",
                    onValueChange = { newValue -> confirmPassword.value = newValue },
                    isPassword = true,
                    icon = { Icon(Icons.Default.Lock, contentDescription = "Email icon") }
                )
            }
            Spacer(modifier = Modifier.height(16.dp)) // Add a space of 16dp
        }

        BottomBorder(width = 6.dp, color = Color(IvoryBorderColor)) // Display a bottom border

        /*------------------------------- BOTTOM of the screen -------------------------------*/

        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(GreenVariantColor))
        ) { // Column where there is the sign in / sign up button

            Spacer(modifier = Modifier.height(16.dp)) // Add a space of 16dp between the text and the button

            if (isLogin.value) { // If we are in login mode
                CustomButton(onClick = { login(usernameLogin.value, passwordLogin.value, navController, error) }, text = "Sign in")
            } else {
                CustomButton(onClick = { register(username.value, email.value, password.value, confirmPassword.value, navController, error) }, text = "Sign up")
            }

            Spacer(modifier = Modifier.height(16.dp)) // Add a space of 16dp between the text and the button

            if (isLogin.value) { // If we are in login mode
                CustomButton(onClick = { isLogin.value = false }, text = "Sign up ?")

            } else {
                CustomButton(onClick = { isLogin.value = true }, text = "Sign in ?")
            }

            Spacer(modifier = Modifier.height(16.dp)) // Add a space of 16dp

            if (isLogin.value) { // If we are in login mode
                Image(
                    painter = painterResource(id = R.drawable.lac_do_brazil),
                    contentDescription = "Lac Do Brazil",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.FillBounds // This will stretch the image to fill all available space
                )
            }
        }
    }

}

/**
 * @brief Composable that permit to implement our input fields
 * @param label : String : the label of the input field
 * @param onValueChange : (String) -> Unit : the function that will be called when the value of the input field change
 * @param isPassword : Boolean : true if the input field is a password field, false otherwise
 * @param icon : Icons : the icon that will be displayed in the input field
 * @param width : Float : the width of the input field
 */
@Composable
fun InputField(label: String, onValueChange: (String) -> Unit, isPassword: Boolean, icon: (@Composable () -> Unit)? = null, width: Float = 0.7f) {
    val passwordVisibility = remember { mutableStateOf(false) }
    val inputValue = remember { mutableStateOf("") }

    OutlinedTextField (
        leadingIcon = icon, // Icon of the input field
        value = inputValue.value, // Value of the input field
        onValueChange = { newValue -> // Function that will be called when the value of the input field change
            inputValue.value = newValue
            onValueChange(newValue)
        },
        label = { Text(label) }, // Label of the input field
        colors = OutlinedTextFieldDefaults.colors(  // Colors of the input field
            // Color when the input field is unfocused
            unfocusedTextColor = Color(InputFieldGreen),
            unfocusedBorderColor = Color(InputFieldGreen),
            unfocusedLabelColor = Color(InputFieldGreen),
            unfocusedLeadingIconColor = Color.Black,

            // Color when the input field is focused
            focusedTextColor = Color(InputFieldBrown),
            focusedBorderColor = Color(InputFieldBrown),
            focusedLabelColor = Color(InputFieldBrown),
            focusedLeadingIconColor = Color.Yellow,
        ),
        shape = RoundedCornerShape(40.dp), // Shape of the input field
        modifier = Modifier
            .fillMaxWidth(width)
            .padding(8.dp),

        // Visual transformation of the input field if it is a password field
        // If the input field is a password field and the passwordVisibility is false, the text will be hidden
        visualTransformation = if (isPassword && !passwordVisibility.value) PasswordVisualTransformation() else VisualTransformation.None,

        // Trailing icon of the input field
        trailingIcon = {
            if (isPassword) { // If the input field is a password field
                IconButton(onClick = { passwordVisibility.value = !passwordVisibility.value }) { // Button to show / hide the password
                    Icon( // Icon to show / hide the password
                        imageVector = if (passwordVisibility.value) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                        contentDescription = if (passwordVisibility.value) "Hide password" else "Show password"
                    )
                }
            }
        }
    )
}


/**
 * @brief Composable that allow to make custom button
 * @param onClick : () -> Unit : the function that will be called when the button is clicked
 * @param text : String : the text of the button
 * @param contentColor : Color : the color of the text of the button
 * @param containerColor : Color : the color of the button
 * @param borderColor : Color : the color of the border of the button
 */
@Composable
fun CustomButton(onClick: () -> Unit, text: String, contentColor : Color = Color(InputFieldGreen), containerColor : Color = Color(IvoryColor), borderColor : Color = Color(InputFieldGreen)) {

    Button(
        onClick = onClick,
        border = BorderStroke(3.dp, borderColor), // Border of the button
        colors = buttonColors(
            containerColor = containerColor, // Color of the button
            contentColor = contentColor // Color of the text of the button
        ),
        shape = RoundedCornerShape(40.dp) // Shape of the button
    ) {
        Text(text = text) // Text of the button
    }
}

/**
 * @brief Composable that allow to make bottom border
 * @param width : Dp : the width of the border
 * @param color : Color : the color of the border
 */
@Composable
fun BottomBorder(width: Dp = 2.dp, color: Color = Color.Red) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(width) // This is the thickness of the border
            .background(color) // This is the color of the border
    ) {
        Spacer(Modifier.fillMaxSize())
    }
}

/**
 * @brief Composable that allow to preview the login screen
 */
@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    DoBrazilTheme {
        LoginScreen()
    }
}