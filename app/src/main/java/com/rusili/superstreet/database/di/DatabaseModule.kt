package com.rusili.superstreet.database.di

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rusili.superstreet.database.AppDatabase
import com.rusili.superstreet.database.favorites.FavoriteDao
import com.rusili.superstreet.di.AppModule
import dagger.Module
import dagger.Provides

private const val DATABASE_NAME = "SuperstreetDb"

@Module(includes = [AppModule::class])
class DatabaseModule {

    @Provides
    fun provideFavoriteDao(database: AppDatabase): FavoriteDao =
        database.favoriteDao()

    @Provides
    protected fun provideRoomDatabase(app: Application): RoomDatabase =
        Room.databaseBuilder(
            app,
            AppDatabase::class.java,
            DATABASE_NAME
        ).build()
}
