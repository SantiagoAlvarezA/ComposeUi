package com.composeui.ui.navigation

sealed class Screen( val route: String) {
    object MainScreen : Screen("main")
    object ProductScreen : Screen("product")

    fun withArgs(vararg args: String): String =
        buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
}
