package com.example.flightsearch

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "airports")
data class Airport(
    @PrimaryKey val iataCode: String,
    val name: String
)
