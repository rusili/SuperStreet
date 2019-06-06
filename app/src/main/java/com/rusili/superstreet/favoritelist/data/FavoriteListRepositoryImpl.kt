package com.rusili.superstreet.favoritelist.data

import com.rusili.superstreet.database.favorites.FavoriteDao
import com.rusili.superstreet.database.favorites.FavoriteManager
import com.rusili.superstreet.database.favorites.model.FavoriteEntity
import com.rusili.superstreet.favoritelist.domain.FavoriteListRepository
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class FavoriteListRepositoryImpl @Inject constructor(private val manager: FavoriteManager) :
    FavoriteManager by manager
