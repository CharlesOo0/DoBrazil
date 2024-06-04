package com.example.dobrazil.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dobrazil.Entity.EventFinanciersCrossRef
import com.example.dobrazil.EntityRepositories.EventFinanciersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @brief ViewModel for EventFinanciers
 */
@HiltViewModel
class eventFinanciersViewModel @Inject public constructor(
    private val repository: EventFinanciersRepository
) : ViewModel(){

    /**
     * @brief Get all EventFinanciers
     */
    fun getAll(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAll()
        }
    }

    /**
     * @brief Get EventFinanciers by id
     */
    fun getById(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getById(id)
        }
    }

    /**
     * @brief Insert EventFinanciers
     */
    fun insert(event: EventFinanciersCrossRef){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(event)
        }
    }

    /**
     * @brief Delete EventFinanciers
     */
    fun delete(event: EventFinanciersCrossRef){
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(event)
        }
    }

    /**
     * @brief Update EventFinanciers
     */
    fun update(event: EventFinanciersCrossRef){
        viewModelScope.launch(Dispatchers.IO) {
            repository.update(event)
        }
    }

    /**
     * @brief Delete with event id and financier id
     */
    fun deleteWithEventIdAndFinancierId(eventId: Int, financierId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteWithEventIdAndFinancierId(eventId, financierId)
        }
    }

    /**
     * @brief Delete with event id
     */
    fun deleteWithEventId(eventId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteWithEventId(eventId)
        }
    }

    /**
     * @brief Insert with username of financer and event id
     */
    fun insertWithUsernames(eventId: Int, financierUsername: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertWithUsernames(eventId, financierUsername)
        }
    }

    /**
     * @brief Delete with username of financer and event id
     */
    fun deleteWithUsernames(eventId: Int, financierUsername: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteWithUsernames(eventId, financierUsername)
        }
    }
}