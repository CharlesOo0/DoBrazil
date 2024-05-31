package com.example.dobrazil.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dobrazil.Entity.EventInvitedCrossRef
import com.example.dobrazil.EntityRepositories.EventInvitedRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @brief ViewModel for EventInvited
 */
@HiltViewModel
class eventInvitedViewModel @Inject public constructor(
    private val repository: EventInvitedRepository
) : ViewModel(){

    /**
     * @brief Get all EventInvited
     */
    fun getAll(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAll()
        }
    }

    /**
     * @brief Get EventInvited by id
     */
    fun getById(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getById(id)
        }
    }

    /**
     * @brief Insert EventInvited
     */
    fun insert(event: EventInvitedCrossRef){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(event)
        }
    }

    /**
     * @brief Delete EventInvited
     */
    fun delete(event: EventInvitedCrossRef){
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(event)
        }
    }

    /**
     * @brief Update EventInvited
     */
    fun update(event: EventInvitedCrossRef){
        viewModelScope.launch(Dispatchers.IO) {
            repository.update(event)
        }
    }
}