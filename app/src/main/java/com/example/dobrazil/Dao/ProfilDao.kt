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
}