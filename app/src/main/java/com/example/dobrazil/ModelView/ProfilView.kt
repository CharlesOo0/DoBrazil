package com.example.dobrazil.ModelView

import androidx.lifecycle.ViewModel
import com.example.dobrazil.Entity.ProfilEntity
import com.example.dobrazil.EntityRepositories.ProfilRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * ViewModel for the Profil
 */
@HiltViewModel
class ProfilView @Inject constructor(
    private val repository: ProfilRepository
) : ViewModel() {

    // Get all profils
    fun getAll() = repository.getAll()

    // Get all profils by status
    fun getById(id : Int) = repository.getById(id)

    // Insert a profil
    suspend fun insert(profil: ProfilEntity) = repository.insert(profil)

    // Delete a profil
    suspend fun delete(profil: ProfilEntity) = repository.delete(profil)

    // Update a profil
    suspend fun update(profil: ProfilEntity) = repository.update(profil)
}