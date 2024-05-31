package com.example.dobrazil

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.dobrazil.Entity.ProfilEntity
import com.example.dobrazil.ui.theme.DoBrazilTheme

/**
 * @brief Dashboard that manage how the screen Interact with each others.
 */
@Composable
fun Dashboard(
    viewModel: MainViewModel = hiltViewModel()
) {
    val navController = rememberNavController()

//    val profil : ProfilEntity = ProfilEntity(null, "Charles", "DZJDZD")
//
//    viewModel.insert(profil)

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