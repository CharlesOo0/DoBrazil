package com.example.dobrazil.Entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @brief Entity class for the Profil table
 * @param idProfil: Int, primary key
 * @param username: String, username of the profil
 * @param password: String, password of the profil
 * @param email: String, email of the profil
 * @param avatarLink: String, link to the avatar of the profil
 */
@Entity
data class ProfilEntity(
    @PrimaryKey(autoGenerate = true)
    val idProfil : Int?,
    val avatarLink: String? = "",
    val email: String,
    val username: String,
    val password: String
)