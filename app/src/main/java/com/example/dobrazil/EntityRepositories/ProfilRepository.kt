package com.example.dobrazil.EntityRepositories

import com.example.dobrazil.Dao.ProfilDao
import com.example.dobrazil.Entity.ProfilEntity
import javax.inject.Inject

/**
 * Repository for the ProfilEntity
 */
class ProfilRepository @Inject constructor(
    private val dao: ProfilDao
) {
    // Insert a profil
    suspend fun insert(profil: ProfilEntity) = dao.insert(profil)

    // Delete a profil
    suspend fun delete(profil: ProfilEntity) = dao.delete(profil)

    // Update a profil
    suspend fun update(profil: ProfilEntity) = dao.update(profil)

    // Get all profils
    fun getAll() = dao.getAll()

    // Get all profils by status
    fun getById(id : Int) = dao.getById(id)
}