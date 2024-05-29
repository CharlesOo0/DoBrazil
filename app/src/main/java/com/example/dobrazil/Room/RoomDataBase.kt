package com.example.dobrazil.Room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.dobrazil.Dao.*
import com.example.dobrazil.Entity.*

/**
 * @brief Database class for the application
 * @param entities: Array of Entity classes
 * @param version: Int, version of the database
 */
@Database(entities = [ProfilEntity::class, EventEntity::class, ExpenseEntity::class, EventInvitedCrossRef::class, EventFinanciersCrossRef::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun profilDao(): ProfilDao // Dao for the Profil table
    abstract fun eventDao(): EventDao // Dao for the Event table
    abstract fun expenseDao(): ExpenseDao // Dao for the Expense table
    abstract fun eventInvitedCrossRefDao(): EventInvitedCrossRefDao // Dao for the EventInvitedCrossRef table
    abstract fun eventFinanciersCrossRefDao(): EventFinanciersCrossRefDao // Dao for the EventFinanciersCrossRef table

    // Companion object to get the database
    companion object {
        // Singleton prevents multiple instances of database opening at the same time.
        @Volatile
        private var INSTANCE: AppDatabase? = null

        /**
         * @brief Get the database
         * @param context: Context, context of the application
         * @return AppDatabase, the database
         */
        fun getDatabase(context: Context): AppDatabase {
            val tempInstance = INSTANCE // Copy of the database
            if (tempInstance != null) { // If the database already exists
                return tempInstance // Return the database
            }

            // Else, create the database
            synchronized(this) { // Synchronize the database
                val instance = Room.databaseBuilder( // Create the database
                    context.applicationContext, // Context of the application
                    AppDatabase::class.java, // Class of the database
                    "app_database" // Name of the database
                ).build() // Build the database
                INSTANCE = instance // Set the database
                return instance // Return the database
            }
        }
    }
}