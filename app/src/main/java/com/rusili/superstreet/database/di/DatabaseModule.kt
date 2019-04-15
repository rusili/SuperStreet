package com.rusili.superstreet.database.di

import android.content.Context
import androidx.room.Room
import com.rusili.superstreet.database.AppDatabase
import com.rusili.superstreet.database.favorites.FavoriteDao
import com.rusili.superstreet.database.favorites.FavoriteModelMapper
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
    fun provideFavoriteModelMapper(): FavoriteModelMapper =
        FavoriteModelMapper()

    @Provides
    protected fun provideRoomDatabase(context: Context): AppDatabase =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            DATABASE_NAME
        ).build()
}
