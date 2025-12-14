package com.example.appemisoras.data

import com.google.firebase.firestore.PropertyName

// Data class to represent a station from Firestore
// It matches the document structure in the 'stations' collection
data class Station(
    val name: String = "",
    val description: String = "",
    val institution: String = "", // New field for the institution
    @get:PropertyName("logoURL") @set:PropertyName("logoURL") var logoURL: String = "",
    @get:PropertyName("bannerURL") @set:PropertyName("bannerURL") var bannerURL: String = "",
    @get:PropertyName("streamURL") @set:PropertyName("streamURL") var streamURL: String = "",
    val order: Int = 0
)
