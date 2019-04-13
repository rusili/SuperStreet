package com.rusili.superstreet.favoritelist.di

import com.rusili.superstreet.favoritelist.data.FavoriteListRepositoryImpl
import com.rusili.superstreet.favoritelist.domain.FavoriteListRepository
import com.rusili.superstreet.favoritelist.domain.FavoriteListUsecaseImpl
import com.rusili.superstreet.favoritelist.ui.FavoriteListUsecase
import dagger.Binds
import dagger.Module

@Module
abstract class FavoriteListModule() {

    @Binds
    abstract fun provideFavoriteListRepository(repositoryImpl: FavoriteListRepositoryImpl): FavoriteListRepository

    @Binds
    abstract fun provideFavoriteListUsecase(repositoryImpl: FavoriteListUsecaseImpl): FavoriteListUsecase
}
