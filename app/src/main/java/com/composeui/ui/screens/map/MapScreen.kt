package com.composeui.ui.screens.map

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.Circle
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun MapScreen(viewModel: MapViewModel = hiltViewModel()) {
    val state = viewModel.state

    val singapore = LatLng(1.35, 103.87)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(singapore, 10f)
    }

    var userLocation by remember {
        mutableStateOf( LatLng(1.36, 103.88))
    }

    LaunchedEffect(key1 = true) {
        while (true) {
            delay(4000)
            userLocation = LatLng(userLocation.latitude + 0.001, userLocation.longitude + 0.001)
            Log.d("test", userLocation.toString())
        }
    }


    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState
    ) {

        Circle(center = singapore, fillColor = MaterialTheme.colorScheme.primary, radius = 120.0, )
        Marker(
            state = MarkerState(position = singapore),
            title = "Singapore",
            snippet = "Marker in Singapore"
        )

        Circle(center = userLocation, fillColor = MaterialTheme.colorScheme.secondary, radius = 20.0, )
        Marker(
            state = MarkerState(position = userLocation),
            title = "User location",
            snippet = "Marker user location latest"
        )
    }

}