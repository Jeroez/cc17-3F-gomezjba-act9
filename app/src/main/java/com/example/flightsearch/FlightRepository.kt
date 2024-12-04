package com.example.flightsearch

class FlightRepository {
    private val airportDao = FlightDatabase.getInstance().airportDao()
    private val favoriteDao = FlightDatabase.getInstance().favoriteDao()

    suspend fun searchAirports(query: String): List<Airport> {
        return airportDao.searchAirports(query)
    }

    suspend fun getFlights(departureCode: String): List<Flight> {
        return airportDao.getFlights(departureCode)
    }

    suspend fun saveFavorite(departure: String, destination: String) {
        favoriteDao.insertFavorite(Favorite(departure, destination))
    }
}
