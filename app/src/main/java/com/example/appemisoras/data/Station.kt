package com.example.appemisoras.data

import androidx.annotation.StringRes

// Data class to represent a station
data class Station(
    val logoUrl: String,
    @param:StringRes val stationNameRes: Int,
    @param:StringRes val presentersRes: Int,
    val imageUrl: String,
    @param:StringRes val descriptionRes: Int
)
