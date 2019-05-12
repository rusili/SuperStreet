package com.rusili.superstreet.database.favorites

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_entity")
data class FavoriteEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = 0,
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
