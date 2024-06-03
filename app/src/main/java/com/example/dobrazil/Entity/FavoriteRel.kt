package com.example.dobrazil.Entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

/**
 * @brief Entity class for the FavoriteRel table
 * @param idFavoriteRel: Int, primary key
 * @param idFollower: Int, foreign key
 * @param idFollow: Int, foreign key
 */
@Entity
data class FavoriteRel(
    @PrimaryKey(autoGenerate = true)
    val idFavoriteRel: Int?,
    val idFollower: Int,
    val idFollow: Int
)