package com.example.dobrazil.Room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.dobrazil.Dao.*
import com.example.dobrazil.Entity.*

/**
 * @brief Database class for the application
 * @param entities: Array of Entity classes
 * @param version: Int, version of the database
 */
@Database(entities = [
    ProfilEntity::class,
    EventEntity::class,
    ExpenseEntity::class,
    EventInvitedCrossRef::class,
    EventFinanciersCrossRef::class,
    FavoriteRel::class
                     ], version = 9)
abstract class AppDatabase : RoomDatabase() {
    abstract fun profilDao(): ProfilDao // Dao for the Profil table
    abstract fun eventDao(): EventDao // Dao for the Event table
    abstract fun expenseDao(): ExpenseDao // Dao for the Expense table
    abstract fun eventInvitedCrossRefDao(): EventInvitedCrossRefDao // Dao for the EventInvitedCrossRef table
    abstract fun eventFinanciersCrossRefDao(): EventFinanciersCrossRefDao // Dao for the EventFinanciersCrossRef table

    abstract fun favoriteRelDao(): FavoriteDao // Dao for the FavoriteRel table
}