package com.example.dobrazil.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dobrazil.Entity.EventEntity
import com.example.dobrazil.EntityRepositories.EventRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * @brief ViewModel for Event
 */
@HiltViewModel
class eventViewModel @Inject public constructor(
    private val repository: EventRepository
) : ViewModel(){

    /**
     * @brief Get all Event
     */
    fun getAll(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAll()
        }
    }

    /**
     * @brief Get Event by id
     */
    suspend fun getById(id: Int): EventEntity {
        return withContext(Dispatchers.IO) {
            repository.getById(id)
        }
    }

    /**
     * @brief Insert Event
     */
    fun insert(event: EventEntity){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(event)
        }
    }

    /**
     * @brief Delete Event
     */
    fun delete(event: EventEntity){
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(event)
        }
    }

    /**
     * @brief Update Event
     */
    fun update(event: EventEntity){
        viewModelScope.launch(Dispatchers.IO) {
            repository.update(event)
        }
    }

    /**
     * @brief Get Event by title
     */
    suspend fun getByTitle(title: String): EventEntity {
        return withContext(Dispatchers.IO) {
            repository.getByTitle(title)
        }
    }

    /**
     * @brief Check title already exist
     */
    suspend fun checkTitle(title: String): Boolean {
        return withContext(Dispatchers.IO) {
            repository.checkTitle(title) != null
        }
    }

    /**
     * @brief Get events where not invited or creator
     */
    suspend fun getEventsWhereNotInvitedOrCreator(profilId: Int): List<EventEntity> {
        return withContext(Dispatchers.IO) {
            repository.getEventsWhereNotInvitedOrCreator(profilId)
        }
    }
}