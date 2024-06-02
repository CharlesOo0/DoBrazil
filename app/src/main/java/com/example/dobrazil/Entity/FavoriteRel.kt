package com.example.dobrazil.Entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

/**
 * @brief Entity class for the FavoriteRel table
 * @param idFavoriteRel: Int, primary key
 * @param idProfil: Int, foreign key to the Profil table
 * @param idEvent: Int, foreign key to the Event table
 */
@Entity
data class FavoriteRel(
    @PrimaryKey(autoGenerate = true)
    val idFavoriteRel: Int?,
    val idProfil: Int,
    val idEvent: Int
)