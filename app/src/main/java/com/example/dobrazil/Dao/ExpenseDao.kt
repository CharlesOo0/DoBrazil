package com.example.dobrazil.Dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.dobrazil.Entity.ExpenseEntity

/**
 * @brief Dao class for the Expense table
 */
@Dao
interface ExpenseDao {

    // Insert an expense in the table
    @Insert
    fun insert(expense: ExpenseEntity)

    // Update an expense in the table
    @Update
    fun update(expense: ExpenseEntity)

    // Delete an expense in the table
    @Delete
    fun delete(expense: ExpenseEntity)

    // Get all expenses in the table
    @Query("SELECT * FROM ExpenseEntity")
    fun getAll(): List<ExpenseEntity>

    // Get an expense by its id
    @Query("SELECT * FROM ExpenseEntity WHERE idExpense = :id")
    fun getById(id: Int): ExpenseEntity
}