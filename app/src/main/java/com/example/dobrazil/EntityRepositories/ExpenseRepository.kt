package com.example.dobrazil.EntityRepositories

import com.example.dobrazil.Dao.ExpenseDao
import com.example.dobrazil.Entity.ExpenseEntity
import javax.inject.Inject

/**
 * Repository for the ExpenseEntity
 */
class ExpenseRepository @Inject constructor(
    private val dao: ExpenseDao
) {
    // Insert an expense
    suspend fun insert(expense: ExpenseEntity) = dao.insert(expense)

    // Delete an expense
    suspend fun delete(expense: ExpenseEntity) = dao.delete(expense)

    // Update an expense
    suspend fun update(expense: ExpenseEntity) = dao.update(expense)

    // Get all expenses
    fun getAll() = dao.getAll()

    // Get all expenses by status
    fun getById(id : Int) = dao.getById(id)
}