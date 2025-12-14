package com.example.appemisoras.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appemisoras.data.Station
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class StationsViewModel : ViewModel() {

    private val db = Firebase.firestore

    private val _stations = MutableStateFlow<List<Station>>(emptyList())
    val stations: StateFlow<List<Station>> = _stations

    private val _selectedStation = MutableStateFlow<Station?>(null)
    val selectedStation: StateFlow<Station?> = _selectedStation

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    init {
        fetchStations()
    }

    private fun fetchStations() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                val snapshot = db.collection("stations").orderBy("order").get().await()
                _stations.value = snapshot.toObjects(Station::class.java)
            } catch (e: Exception) {
                _error.value = "Error fetching stations: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun selectStation(station: Station) {
        _selectedStation.value = station
    }
}
