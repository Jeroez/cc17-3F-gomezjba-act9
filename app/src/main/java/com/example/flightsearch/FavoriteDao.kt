package com.example.flightsearch

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface FavoriteDao {
    @Insert
    suspend fun insertFavorite(favorite: Favorite)
}
