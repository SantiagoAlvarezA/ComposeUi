package com.composeui.ui.navigation

sealed class Screen(val route: String) {
    object SplashScreen : Screen("splash")
    object MainScreen : Screen("main")
    object TaskScreen : Screen("task")
    object MapScreen : Screen("map")

    fun withArgs(vararg args: String): String =
        buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
}
