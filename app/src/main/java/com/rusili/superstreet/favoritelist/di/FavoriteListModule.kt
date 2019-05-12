package com.rusili.superstreet.favoritelist.di

import com.rusili.superstreet.favoritelist.data.FavoriteListRepositoryImpl
import com.rusili.superstreet.favoritelist.domain.FavoriteListRepository
import com.rusili.superstreet.favoritelist.domain.FavoriteListUsecaseImpl
import com.rusili.superstreet.favoritelist.ui.FavoriteListUsecase
import com.rusili.superstreet.favoritelist.ui.FavoriteListViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class FavoriteListModule() {

    @Module
    companion object {
        @JvmStatic
        @Provides
        protected fun provideFavoriteListViewModelFactory(usecase: FavoriteListUsecase) =
            FavoriteListViewModelFactory(usecase)
    }

    @Binds
    abstract fun provideFavoriteListRepository(repositoryImpl: FavoriteListRepositoryImpl): FavoriteListRepository

    @Binds
    abstract fun provideFavoriteListUsecase(repositoryImpl: FavoriteListUsecaseImpl): FavoriteListUsecase
}
