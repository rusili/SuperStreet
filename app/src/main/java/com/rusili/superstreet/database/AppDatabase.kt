package com.rusili.superstreet.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rusili.superstreet.database.favorites.FavoriteDao
import com.rusili.superstreet.database.favorites.model.FavoriteEntity

@Database(entities = [FavoriteEntity::class], version = 2)
abstract class AppDatabase : RoomDatabase() {

    abstract fun favoriteDao(): FavoriteDao
}
