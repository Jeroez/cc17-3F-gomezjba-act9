package com.example.flightsearch

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FlightViewModel : ViewModel() {
    private val repository = FlightRepository()

    suspend fun getAirports(query: String): List<Airport> {
        return withContext(Dispatchers.IO) {
            repository.searchAirports(query)
        }
    }

    suspend fun getFlights(departureCode: String): List<Flight> {
        return withContext(Dispatchers.IO) {
            repository.getFlights(departureCode)
        }
    }

    suspend fun saveFavoriteFlight(departure: String, destination: String) {
        withContext(Dispatchers.IO) {
            repository.saveFavorite(departure, destination)
        }
    }
}
