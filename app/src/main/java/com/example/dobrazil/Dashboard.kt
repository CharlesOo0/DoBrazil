package com.example.dobrazil

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.dobrazil.Entity.ProfilEntity
import com.example.dobrazil.data.LocalStorage
import com.example.dobrazil.ui.theme.DoBrazilTheme
import com.example.dobrazil.viewModel.eventFinanciersViewModel
import com.example.dobrazil.viewModel.eventInvitedViewModel
import com.example.dobrazil.viewModel.eventViewModel
import com.example.dobrazil.viewModel.expenseViewModel
import com.example.dobrazil.viewModel.favoriteViewModel
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
        favoriteViewModel: favoriteViewModel = hiltViewModel(),
        localStorage: LocalStorage = LocalStorage("")
    ) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "LoginScreen") {
        composable("LoginScreen") { // Fini
            LoginScreen(
                navController = navController,
                profilViewModel = profiViewModel,
                localStorage
            )
        }
        composable("HomeScreen") { // Fini
            Home(
                navController = navController,
                eventViewModel = eventViewModel,
                profilViewModel = profiViewModel,
                eventInvitedViewModel = eventInvitedViewModel,
                eventFinanciersViewModel = eventFinanciersViewModel,
                localStorage = localStorage
            )
        }
        composable("CreateEventScreen") {// Fini
            CreateEvent(
                navController = navController,
                eventViewModel = eventViewModel,
                profilViewModel = profiViewModel,
                favoriteViewModel,
                eventInvitedViewModel,
                localStorage
            )
        }
        composable("ChoseInvitedScreen/{eventTitle}", // Fini
            arguments = listOf(navArgument("eventTitle") { type = NavType.StringType })
        ) {backstackEntry ->
            ChoseInvited(
                navController = navController,
                profilViewModel = profiViewModel,
                favoriteViewModel = favoriteViewModel,
                eventInvitedViewModel = eventInvitedViewModel,
                eventViewModel = eventViewModel,
                localStorage = localStorage,
                eventTitle = backstackEntry.arguments?.getString("eventTitle") ?: ""
            )
        }
        composable("ManageEventScreen/{eventTitle}", // Fini
            arguments = listOf(navArgument("eventTitle") { type = NavType.StringType })
        ) {backstackEntry ->
            ManageEvent(
                navController = navController,
                eventViewModel = eventViewModel,
                eventInvitedViewModel = eventInvitedViewModel,
                favoriteViewModel = favoriteViewModel,
                profilViewModel = profiViewModel,
                expenseViewModel = expenseViewModel,
                eventFinanciersViewModel = eventFinanciersViewModel,
                localStorage = localStorage,
                eventTitle = backstackEntry.arguments?.getString("eventTitle") ?: ""
            )
        }
        composable("BudgetScreen") {
            Budget(
                navController = navController
            )
        }
        composable("SearchFriendScreen") { // Fini
            SearchFriend(
                navController = navController,
                profilViewModel = profiViewModel,
                favoriteViewModel = favoriteViewModel,
                eventInvitedViewModel = eventInvitedViewModel,
                eventViewModel = eventViewModel,
                localStorage = localStorage
            )
        }
        composable("ProfilScreen") { // Fini
            Profil(
                navController = navController,
                profilViewModel = profiViewModel,
                eventViewModel = eventViewModel,
                eventInvitedViewModel = eventInvitedViewModel,
                favoriteViewModel = favoriteViewModel,
                eventFinanciersViewModel = eventFinanciersViewModel,
                localStorage = localStorage
            )
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