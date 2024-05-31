package com.example.dobrazil.Entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.Date

/**
 * @brief Entity class for the Expense table
 * @param idExpense: Int, primary key
 * @param title: String, title of the expense
 * @param amount: Float, amount of the expense
 * @param date: Date, date of the expense
 * @param idPayer: Int, foreign key to the Profil table
 * @param idFinancer: Int, foreign key to the Profil table
 */
@Entity
data class ExpenseEntity(
    @PrimaryKey (autoGenerate = true)
    val idExpense: Int?,
    val title: String,
    val amount: Float,
    val date: String,
    val idPayer: Int,
    val idFinancer: Int
)
