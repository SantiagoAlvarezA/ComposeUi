package com.composeui.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.composeui.ui.screens.main.MainScreen
import com.composeui.ui.screens.product.ProductScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.MainScreen.route) {
        composable(route = Screen.MainScreen.route) {
            MainScreen {
                navController.navigate(Screen.ProductScreen.withArgs("${it.id}"))
            }
        }
        composable(
            route = "${Screen.ProductScreen.route}/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) {
            val id = it.arguments?.getInt("id")
            requireNotNull(id)
            ProductScreen(id) {
                navController.popBackStack()
            }
        }


    }
}