package com.example.dobrazil.ModelView

import androidx.lifecycle.ViewModel
import com.example.dobrazil.Entity.ExpenseEntity
import com.example.dobrazil.EntityRepositories.ExpenseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * ViewModel for the ExpenseEntity
 */
@HiltViewModel
class ExpenseView @Inject constructor(
    private val expenseRepository: ExpenseRepository
) : ViewModel() {
    // Get all expenses
    fun getAll() = expenseRepository.getAll()

    // Get all expenses by status
    fun getById(id : Int) = expenseRepository.getById(id)

    // Insert an expense
    suspend fun insert(expense: ExpenseEntity) = expenseRepository.insert(expense)

    // Delete an expense
    suspend fun delete(expense: ExpenseEntity) = expenseRepository.delete(expense)

    // Update an expense
    suspend fun update(expense: ExpenseEntity) = expenseRepository.update(expense)
}