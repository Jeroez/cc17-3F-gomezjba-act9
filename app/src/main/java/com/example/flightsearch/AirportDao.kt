package com.example.flightsearch

import androidx.room.Dao
import androidx.room.Query

@Dao
interface AirportDao {
    @Query("SELECT * FROM airports WHERE name LIKE :query OR iataCode LIKE :query")
    suspend fun searchAirports(query: String): List<Airport>

    @Query("SELECT * FROM flights WHERE departureCode = :departureCode")
    suspend fun getFlights(departureCode: String): List<Flight>
}
