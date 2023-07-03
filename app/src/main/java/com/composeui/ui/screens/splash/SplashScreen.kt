package com.composeui.ui.screens.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(toMainScreen: () -> Unit) {
    LaunchedEffect(key1 = true) {
        delay(1000)
        toMainScreen()
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        /*Image(
            painter = painterResource(id = R.drawable.mercadolibre), contentDescription = "Splash"
        )*/
        Spacer(modifier = Modifier.height(18.dp))
        Text(text = "by Santiago Alvarez", fontSize = 24.sp)
    }
}
