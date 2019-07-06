package com.rusili.superstreet.database.favorites.model

import androidx.room.Entity
import androidx.room.PrimaryKey

const val FAVORITE_TABLE_NAME = "favorites_table"

@Entity(tableName = FAVORITE_TABLE_NAME)
data class FavoriteEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long?,
    val magazineValue: String,
    val typeValue: String,
    val headerValue: String,
    val headerUrl: String,
    val headerImageTitle: String,
    val headerImageUrl: String,
    val headerDesc: String,
    val authorValue: String,
    val authorHref: String,
    val date: Long
)
