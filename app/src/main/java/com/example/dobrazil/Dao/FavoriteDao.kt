package com.example.dobrazil.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Delete
import com.example.dobrazil.Entity.FavoriteRel

/**
 * @brief Dao class for the FavoriteRel table
 */
@Dao
interface FavoriteDao {

    // Insert a favorite relation in the table
    @Insert
    fun insert(favoriteRel: FavoriteRel)

    // Delete a favorite relation in the table
    @Delete
    fun delete(favoriteRel: FavoriteRel)

    // Get all favorite relations in the table
    @Query("SELECT * FROM FavoriteRel")
    fun getAll(): List<FavoriteRel>

    // Get a favorite relation by its id
    @Query("SELECT * FROM FavoriteRel WHERE idFavoriteRel = :id")
    fun getById(id: Int): FavoriteRel

    // Get a favorite relation by its profil id
    @Query("SELECT * FROM FavoriteRel WHERE idProfil = :idProfil")
    fun getByProfilId(idProfil: Int): List<FavoriteRel>

    // Get a favorite relation by its event id
    @Query("SELECT * FROM FavoriteRel WHERE idEvent = :idEvent")
    fun getByEventId(idEvent: Int): List<FavoriteRel>

}