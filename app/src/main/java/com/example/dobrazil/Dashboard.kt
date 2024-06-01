package com.example.dobrazil

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.dobrazil.Entity.ProfilEntity
import com.example.dobrazil.data.LocalStorage
import com.example.dobrazil.ui.theme.DoBrazilTheme
import com.example.dobrazil.viewModel.eventFinanciersViewModel
import com.example.dobrazil.viewModel.eventInvitedViewModel
import com.example.dobrazil.viewModel.eventViewModel
import com.example.dobrazil.viewModel.expenseViewModel
import com.example.dobrazil.viewModel.profilViewModel

/**
 * @brief Dashboard that manage how the screen Interact with each others.
 */
@Composable
fun Dashboard(
        profiViewModel: profilViewModel = hiltViewModel(),
        eventViewModel: eventViewModel = hiltViewModel(),
        expenseViewModel: expenseViewModel = hiltViewModel(),
        eventFinanciersViewModel: eventFinanciersViewModel = hiltViewModel(),
        eventInvitedViewModel: eventInvitedViewModel = hiltViewModel(),
        localStorage: LocalStorage = LocalStorage(null)
    ) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "LoginScreen") {
        composable("LoginScreen") {
            LoginScreen(navController = navController, profilViewModel = profiViewModel, localStorage)
        }
        composable("HomeScreen") {
            Home(navController = navController)
        }
        composable("CreateEventScreen") {
            CreateEvent(navController = navController)
        }
        composable("ChoseInvitedScreen") {
            ChoseInvited(navController = navController)
        }
        composable("ManageEventScreen") {
            ManageEvent(navController = navController)
        }
        composable("BudgetScreen") {
            Budget(navController = navController)
        }
        composable("SearchFriendScreen") {
            SearchFriend(navController = navController)
        }
        composable("ProfilScreen") {
            Profil(navController = navController)
        }
    }

}

/**
 * @brief Dashboard preview
 */
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DoBrazilTheme {
        Dashboard()
    }
}