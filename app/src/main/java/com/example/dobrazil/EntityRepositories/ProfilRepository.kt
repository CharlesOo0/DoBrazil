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

    // Get a profil by its username
    fun getByUsername(username: String) = dao.getByUsername(username)

    // Check connexion
    fun checkConnexion(username: String, password: String) = dao.checkConnexion(username, password)

    // Check if username is taken
    fun checkUsername(username: String) = dao.checkUsername(username)

    // Check if email is taken
    fun checkEmail(email: String) = dao.checkEmail(email)

    // Register a profil
    fun register(email: String, username: String, password: String) = dao.register(email, username, password)
}