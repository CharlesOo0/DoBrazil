package com.example.dobrazil.Entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.Date

// Setup the Expense table foreign keys
@Entity(foreignKeys = [
    ForeignKey(entity = ProfilEntity::class, parentColumns = ["idProfil"], childColumns = ["idPayer"]),
    ForeignKey(entity = ProfilEntity::class, parentColumns = ["idProfil"], childColumns = ["idFinancer"])
])

/**
 * @brief Entity class for the Expense table
 * @param idExpense: Int, primary key
 * @param title: String, title of the expense
 * @param amount: Float, amount of the expense
 * @param date: Date, date of the expense
 * @param idPayer: Int, foreign key to the Profil table
 * @param idFinancer: Int, foreign key to the Profil table
 */
data class ExpenseEntity(
    @PrimaryKey val idExpense: Int,
    val title: String,
    val amount: Float,
    val date: Date,
    val idPayer: Int,
    val idFinancer: Int
)
