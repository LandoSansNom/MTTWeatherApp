package com.example.mttweatherapp.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Thermostat
import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavDrawerItem(
    var route: String,
    var icon: ImageVector,
    var title: String
) {

    object NextSevenDays : NavDrawerItem("nextSevenDays", Icons.Rounded.Thermostat, "Forecast")
}