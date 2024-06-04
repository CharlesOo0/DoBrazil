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

    // Get a profil by its username
    @Query("SELECT * FROM ProfilEntity WHERE username = :username")
    fun getByUsername(username: String): ProfilEntity

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

    // Search not friend profil
    @Query("SELECT * FROM ProfilEntity WHERE username LIKE '%' || :search || '%' AND idProfil NOT IN (SELECT idFollow FROM FavoriteRel WHERE idFollower = :idProfil) AND idProfil != :idProfil")
    fun searchNotFriendProfil(search: String, idProfil: Int): List<ProfilEntity>

    // Search not invited profil do not take the idProfil
    @Query("SELECT * FROM ProfilEntity WHERE username LIKE '%' || :search || '%' AND idProfil NOT IN (SELECT idProfil FROM EventInvitedCrossRef WHERE eventId = :idEvent) AND idProfil != :idProfil")
    fun searchNotInvitedProfil(search: String, idProfil: Int, idEvent: Int): List<ProfilEntity>

    // Get friends profil
    @Query("SELECT * FROM ProfilEntity WHERE idProfil IN (SELECT idFollow FROM FavoriteRel WHERE idFollower = :idProfil)")
    fun getFriendsProfil(idProfil: Int): List<ProfilEntity>

    // Get peoples invited to an event
    @Query("SELECT * FROM ProfilEntity WHERE idProfil IN (SELECT idProfil FROM EventInvitedCrossRef WHERE eventId = :idEvent)")
    fun getInvitedProfil(idEvent: Int): List<ProfilEntity>

}