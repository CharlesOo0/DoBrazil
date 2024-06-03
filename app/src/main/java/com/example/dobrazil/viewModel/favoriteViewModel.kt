package com.example.dobrazil.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dobrazil.Entity.FavoriteRel
import com.example.dobrazil.EntityRepositories.FavoriteRelRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * @brief ViewModel for FavoriteRel
 */
@HiltViewModel
class favoriteViewModel @Inject public constructor(
    private val repository: FavoriteRelRepository
) : ViewModel(){

    /**
     * @brief Get all FavoriteRel
     */
    fun getAll(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAll()
        }
    }

    /**
     * @brief Get FavoriteRel by id
     */
    fun getById(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getById(id)
        }
    }

    /**
     * @brief Get FavoriteRel by follower
     */
    suspend fun getByFollower(id: Int): List<FavoriteRel> {
        return withContext(Dispatchers.IO) {
            repository.getByFollower(id)
        }
    }

    /**
     * @brief Get FavoriteRel by follow
     */
    fun getByFollow(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getByFollow(id)
        }
    }

    /**
     * @brief Insert FavoriteRel
     */
    fun insert(event: FavoriteRel){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(event)
        }
    }

    /**
     * @brief Delete FavoriteRel
     */
    fun delete(event: FavoriteRel){
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(event)
        }
    }
}