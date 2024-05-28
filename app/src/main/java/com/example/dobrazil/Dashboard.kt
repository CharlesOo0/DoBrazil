package com.example.dobrazil

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

/**
 * @brief Dashboard that manage how the screen Interact with each others.
 */
@Composable
fun Dashboard() {
    val navController = rememberNavController()
    
    NavHost(navController = navController, startDestination = "LoginScreen") {
        composable("LoginScreen") {
            LoginScreen(navController = navController)
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