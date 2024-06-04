package com.example.dobrazil

import android.util.Log
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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
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
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.appwithroomuv.R
import com.example.dobrazil.Entity.ExpenseEntity
import com.example.dobrazil.Entity.ProfilEntity
import com.example.dobrazil.data.LocalStorage
import com.example.dobrazil.ui.theme.DoBrazilTheme
import com.example.dobrazil.viewModel.eventFinanciersViewModel
import com.example.dobrazil.viewModel.eventInvitedViewModel
import com.example.dobrazil.viewModel.eventViewModel
import com.example.dobrazil.viewModel.expenseViewModel
import com.example.dobrazil.viewModel.favoriteViewModel
import com.example.dobrazil.viewModel.profilViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * @brief Budget composable that modelise the Budget screen
 */
@Composable
fun Budget(
    eventInvitedViewModel: eventInvitedViewModel,
    eventFinanciersViewModel: eventFinanciersViewModel,
    eventViewModel: eventViewModel,
    profilViewModel: profilViewModel,
    expenseViewModel: expenseViewModel,
    navController: NavController? = null,
    localStorage: LocalStorage,
    eventTitle: String = ""
) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(Color(IvoryColor)),
    ) {
        var page = remember { mutableStateOf(0) }
        var openDialog = remember { mutableStateOf(false) }

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

            Column {
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

                    Icon(
                        Icons.Default.Add,
                        contentDescription = "Add",
                        modifier = Modifier
                            .size(45.dp)
                            .clickable(onClick = {
                                openDialog.value = !openDialog.value
                            }) // Make the icon clickable
                            .padding(8.dp)
                    )

                }

                BottomBorder(width = 3.dp, color = Color(IvoryBorderColor).copy(alpha = Opacity))// Border between top bar and form

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

                /*------------------------------- Content -------------------------------*/
                var expensesMutable = remember { mutableStateOf(listOf<ExpenseEntity>()) }
                var profilesMutable = remember { mutableStateOf(listOf<ProfilEntity>()) }


                LaunchedEffect(Dispatchers.Main) {
                    val event = async {eventViewModel.getByTitle(eventTitle)}.await()
                    val expenses = async {expenseViewModel.getAllExpenseTargetEvent(event.idEvent!!)}.await()
                    val profiles = async {profilViewModel.getAll()}.await()

                    withContext(Dispatchers.Main) {
                        expensesMutable.value = expenses
                        profilesMutable.value = profiles
                    }
                }

                if (page.value == 0) { // Show the history of finance
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    ) { // Lazy column for the list of finance
                        items(expensesMutable.value) { expense ->
                            Finance(expense)
                            Spacer(modifier = Modifier.padding(8.dp))
                        }
                    }
                } else { // Show the balance between people
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)

                    ){
                        items(calculateBalances(expensesMutable.value, profilesMutable.value).toList()) { (key, value) ->
                            Balance(
                                name = key,
                                balance = value
                            )
                            Spacer(modifier = Modifier.padding(8.dp))
                        }
                    }
                }

                CreateExpense( // Create an expense
                    openDialog = openDialog,
                    profilViewModel = profilViewModel,
                    eventViewModel = eventViewModel,
                    expenseViewModel = expenseViewModel,
                    eventTitle = eventTitle
                )
            }

        }

    }
}

/**
 * @brief Calculate the balance between people
 * @param expenses : List<ExpenseEntity>, the list of expenses
 * @param profilViewModel : profilViewModel, the view model of the profil
 */
fun calculateBalances(expenses: List<ExpenseEntity>, profiles: List<ProfilEntity>): Map<String, Float> {
    val balances = mutableMapOf<String, Float>()

    // Initialize balances
    profiles.forEach { profile ->
        balances[profile.username] = 0f
    }

    expenses.forEach { expense ->
        val payer = profiles.find { it.idProfil == expense.idPayer }
        val financer = profiles.find { it.idProfil == expense.idFinancer }

        payer?.let {
            balances[it.username] = balances[it.username]!! + expense.amount
        }

        financer?.let {
            balances[it.username] = balances[it.username]!! - expense.amount
        }
    }

    return balances
}

/**
 * @brief Composable that allow to create an expense
 */
@Composable
fun CreateExpense(
    openDialog: MutableState<Boolean>,
    eventViewModel: eventViewModel,
    profilViewModel: profilViewModel,
    expenseViewModel: expenseViewModel,
    eventTitle: String
) {
    var expenseTitle = remember { mutableStateOf("") }
    var expenseAmount = remember { mutableStateOf("") }
    var expenseDate = remember { mutableStateOf("") }
    var indexPayer = remember { mutableStateOf(0) }
    var indexFinancer = remember { mutableStateOf(0) }

    var listInvitedMutable = remember { mutableStateOf(listOf<ProfilEntity>()) }
    var idEventMutable = remember { mutableStateOf(0) }

    LaunchedEffect(Dispatchers.Main) { // Launch the effect
        val idEvent = async {eventViewModel.getByTitle(eventTitle)}.await().idEvent!! // Get the id of the event
        val listInvited = async {profilViewModel.getInvitedProfil(idEvent)}.await() // Get the list of invited people

        withContext(Dispatchers.Main) { // Change the context
            listInvitedMutable.value = listInvited // Set the value of the list of invited people
            idEventMutable.value = idEvent // Set the value of the id of the event
        }
    }

    if (listInvitedMutable.value.isEmpty()) { // If the list of invited people is empty
        return
    }

    if (openDialog.value) { // If the dialog is open
        AlertDialog( // Create an alert dialog
            onDismissRequest = { // When the dialog is dismissed
                openDialog.value = false
            },
            title = { // Title of the dialog
                Text(text = "Create an expense")
            },
            text = { // Text of the dialog
                Column (
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(8.dp)
                ){
                    CustomInputField( // Custom input field for the title
                        label = "Title",
                        textState = expenseTitle
                    )

                    CustomInputField( // Custom input field for the amount
                        label = "Amount",
                        textState = expenseAmount,
                        mode = 1
                    )

                    CustomDatePicker( // Custom date picker
                        label = "Date",
                        dateState = expenseDate
                    )

                    Row {
                        Text("Payer", modifier = Modifier.padding(8.dp))
                        SelectChoice( // Select choice for the person that payed
                            items = listInvitedMutable.value,
                            selectedIndex = indexPayer
                        )
                    }

                    Row {
                        Text("Financer", modifier = Modifier.padding(8.dp))
                        SelectChoice( // Select choice for the person that need to finance it
                            items = listInvitedMutable.value,
                            selectedIndex = indexFinancer
                        )
                    }
                }
            },
            confirmButton = { // Confirm button
                Button(
                    onClick = { // When the button is clicked
                        // Verify that no field are null
                        if (expenseTitle.value == "" || expenseAmount.value == "" || expenseDate.value == "") {
                            return@Button
                        } else { // If all the fields are filled
                            val expenseEntity: ExpenseEntity =
                                ExpenseEntity( // Create an expense entity
                                    idExpense = null,
                                    title = expenseTitle.value,
                                    amount = expenseAmount.value.toFloat(),
                                    date = expenseDate.value,
                                    idPayer = listInvitedMutable.value[indexPayer.value].idProfil!!,
                                    idFinancer = listInvitedMutable.value[indexFinancer.value].idProfil!!,
                                    idEvent = idEventMutable.value
                                )
                            expenseViewModel.insert(expenseEntity) // Insert the expense
                            openDialog.value = false // Close the dialog
                        }
                    }
                ) {
                    Text("Confirm")
                }
            },
            dismissButton = { // Dismiss button
                Button(
                    onClick = { // When the button is clicked
                        openDialog.value = false // Close the dialog
                    }
                ) {
                    Text("Dismiss")
                }
            }
        )
    }
}

/**
 * @brief Composable for a select choice
 */
@Composable
fun SelectChoice(
    items: List<ProfilEntity>,
    selectedIndex: MutableState<Int>
) {
    var expanded = remember { mutableStateOf(false) }

    Box(modifier = Modifier.wrapContentSize()) {
        Text(items[selectedIndex.value].username, modifier = Modifier.clickable(onClick = { expanded.value = true }))
        DropdownMenu(expanded = expanded.value, onDismissRequest = { expanded.value = false }) {
            items.forEachIndexed { index, s ->
                DropdownMenuItem(
                    text = { Text(s.username) },
                    onClick = {
                    selectedIndex.value = index
                    expanded.value = false
                })
            }
        }
    }
}

/**
 * @brief Composable for a finance
 * @param financeId : Int, the id of the finance
 */
@Composable
fun Finance(
    expense : ExpenseEntity
) {
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
            text = expense.title,
            color = Color.Black,
            modifier = Modifier.padding(8.dp)
        )

        Text(
            text = expense.amount.toString() + "€",
            color = Color.Black,
            modifier = Modifier.padding(8.dp)
        )

        Text(
            text = expense.date,
            color = Color.Black,
            modifier = Modifier.padding(8.dp)
        )
    }

}

/**
 * @brief Composable balance
 * @param name : String, the name of the person
 * @param balance : Float, the balance of the person
 */
@Composable
fun Balance(
    name: String,
    balance: Float
) {
    Row (
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxWidth() // Fill the entire width of the screen
            .padding(8.dp)
            .border(3.dp, Color(GreenVariantStrongColor), RoundedCornerShape(8.dp)) // Border
            .clip(RoundedCornerShape(8.dp)) // Rounded corner
            .background(Color(GreenVariantColor)), // Background color

    ) {

        if (balance < 0) { // If the balance is negative
            Row (
                horizontalArrangement = Arrangement.SpaceEvenly, // Space the element evenly
                modifier = Modifier
                    .fillMaxWidth() // Fill the entire width of the screen
                    .padding(8.dp)
            ){
                Text( // Text of the balance
                    text = balance.toString() + "€",
                    color = Color.Red,
                    modifier = Modifier.padding(8.dp)
                )

                Text( // Text of the name
                    text = name,
                    color = Color.Red,
                    modifier = Modifier.padding(8.dp)
                )

                Spacer(modifier = Modifier.padding(1.dp)) // Space between the text
            }

        } else { // If the balance is positive
            Row (
                horizontalArrangement = Arrangement.SpaceEvenly, // Space the element evenly
                modifier = Modifier
                    .fillMaxWidth() // Fill the entire width of the screen
                    .padding(8.dp)
            ){
                Spacer(modifier = Modifier.padding(1.dp)) // Space between the text

                Text( // Text of the name
                    text = name,
                    color = Color.Green,
                    modifier = Modifier.padding(8.dp)
                )

                Text( // Text of the balance
                    text = balance.toString() + "€",
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
        Budget(profilViewModel = hiltViewModel(), eventInvitedViewModel = hiltViewModel(), eventFinanciersViewModel = hiltViewModel(), expenseViewModel = hiltViewModel(), eventViewModel = hiltViewModel(), localStorage = LocalStorage())
    }
}