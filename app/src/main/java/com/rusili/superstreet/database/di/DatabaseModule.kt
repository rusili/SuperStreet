package com.rusili.superstreet.database.di

import android.content.Context
import androidx.room.Room
import com.rusili.superstreet.database.AppDatabase
import com.rusili.superstreet.database.favorites.FavoriteDao
import com.rusili.superstreet.database.favorites.FavoriteManager
import com.rusili.superstreet.database.favorites.FavoriteManagerImpl
import com.rusili.superstreet.database.favorites.model.FavoriteModelMapper
import com.rusili.superstreet.di.AppModule
import dagger.Binds
import dagger.Module
import dagger.Provides

private const val DATABASE_NAME = "SuperstreetDb"

@Module(includes = [AppModule::class])
abstract class DatabaseModule {

    @Module
    companion object {
        @JvmStatic
        @Provides
        fun provideFavoriteModelMapper(): FavoriteModelMapper =
            FavoriteModelMapper()

        @JvmStatic
        @Provides
        protected fun provideFavoriteDao(database: AppDatabase): FavoriteDao =
            database.favoriteDao()

        @JvmStatic
        @Provides
        protected fun provideRoomDatabase(context: Context): AppDatabase =
            Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                DATABASE_NAME
            ).build()
    }

    @Binds
    abstract fun provideFavoriteManager(manager: FavoriteManagerImpl): FavoriteManager
}
