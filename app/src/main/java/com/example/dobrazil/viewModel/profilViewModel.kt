package com.example.dobrazil.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dobrazil.Entity.ProfilEntity
import com.example.dobrazil.EntityRepositories.ProfilRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * @brief ViewModel for Profil
 */
@HiltViewModel
class profilViewModel @Inject public constructor(
    private val repository: ProfilRepository
) : ViewModel(){

    /**
     * @brief Get all Profil
     */
    fun getAll(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAll()
        }
    }

    /**
     * @brief Get Profil by id
     */
    fun getById(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getById(id)
        }
    }

    /**
     * @brief Insert Profil
     */
    fun insert(event: ProfilEntity){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(event)
        }
    }

    /**
     * @brief Delete Profil
     */
    fun delete(event: ProfilEntity){
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(event)
        }
    }

    /**
     * @brief Update Profil
     */
    fun update(event: ProfilEntity){
        viewModelScope.launch(Dispatchers.IO) {
            repository.update(event)
        }
    }

    /**
     * @brief Check connexion
     */
    suspend fun login(username: String, password: String): Boolean {
        return withContext(Dispatchers.IO) {
            repository.checkConnexion(username, password) != null
        }
    }

    /**
     * @brief Check if username is taken
     */
    suspend fun checkUsername(username: String): Boolean {
        return withContext(Dispatchers.IO) {
            repository.checkUsername(username) != null
        }
    }

    /**
     * @brief Check if email is taken
     */
    suspend fun checkEmail(email: String): Boolean {
        return withContext(Dispatchers.IO) {
            repository.checkEmail(email) != null
        }
    }

    /**
     * @brief Register a profil
     */
    fun register(email: String, username: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.register(email, username, password)
        }
    }

}