package com.example.dobrazil.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.room.Delete
import com.example.dobrazil.Entity.ProfilEntity

/**
 * @brief Dao class for the Profil table
 */
@Dao
interface ProfilDao {

    // Insert a profil in the table
    @Insert
    fun insert(profil: ProfilEntity)

    // Update a profil in the table
    @Update
    fun update(profil: ProfilEntity)

    // Delete a profil in the table
    @Delete
    fun delete(profil: ProfilEntity)

    // Get all profils in the table
    @Query("SELECT * FROM ProfilEntity")
    fun getAll(): List<ProfilEntity>

    // Get a profil by its id
    @Query("SELECT * FROM ProfilEntity WHERE idProfil = :id")
    fun getById(id: Int): ProfilEntity

    // Check the connexion
    @Query("SELECT * FROM ProfilEntity WHERE username = :email AND password = :password")
    fun checkConnexion(email: String, password: String): ProfilEntity?

    // Check if username is taken
    @Query("SELECT * FROM ProfilEntity WHERE username = :username")
    fun checkUsername(username: String): ProfilEntity?

    // Check if email is taken
    @Query("SELECT * FROM ProfilEntity WHERE email = :email")
    fun checkEmail(email: String): ProfilEntity?

    // Register a profil
    @Query("INSERT INTO ProfilEntity (email, username, password) VALUES (:email, :username, :password)")
    fun register(email: String, username: String, password: String)

}