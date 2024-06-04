package com.example.dobrazil.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dobrazil.Entity.ExpenseEntity
import com.example.dobrazil.EntityRepositories.ExpenseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * @brief ViewModel for Expense
 */
@HiltViewModel
class expenseViewModel @Inject public constructor(
    private val repository: ExpenseRepository
) : ViewModel(){

    /**
     * @brief Get all Expense
     */
    fun getAll(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAll()
        }
    }

    /**
     * @brief Get Expense by id
     */
    fun getById(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getById(id)
        }
    }

    /**
     * @brief Insert Expense
     */
    fun insert(event: ExpenseEntity){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(event)
        }
    }

    /**
     * @brief Delete Expense
     */
    fun delete(event: ExpenseEntity){
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(event)
        }
    }

    /**
     * @brief Update Expense
     */
    fun update(event: ExpenseEntity){
        viewModelScope.launch(Dispatchers.IO) {
            repository.update(event)
        }
    }

    /**
     * @brief Delete all Expense of a specific event and profil
     */
    fun deleteAllExpenseTargetEventProfil(idProfil: Int, idEvent: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllExpenseTargetEventProfil(idProfil, idEvent)
        }
    }

    /**
     * @brief Get all Expense of a specific event
     */
    suspend fun getAllExpenseTargetEvent(idEvent: Int): List<ExpenseEntity> {
        return withContext(Dispatchers.IO) {
            repository.getAllExpenseTargetEvent(idEvent)
        }
    }
}