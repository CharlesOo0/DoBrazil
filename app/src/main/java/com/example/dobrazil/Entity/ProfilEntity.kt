package com.example.dobrazil.Entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @brief Entity class for the Profil table
 * @param idProfil: Int, primary key
 * @param username: String, username of the profil
 * @param password: String, password of the profil
 */
@Entity
data class ProfilEntity(
    @PrimaryKey val idProfil: Int,
    val username: String,
    val password: String
)