package com.example.dobrazil.Dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import com.example.dobrazil.Entity.EventFinanciersCrossRef

/**
 * @brief Dao class for the EventFinanciersCrossRef table
 */
@Dao
interface EventFinanciersCrossRefDao {

    // Insert an event-financier cross reference in the table
    @Insert
    fun insert(eventFinanciersCrossRef: EventFinanciersCrossRef)

    // Delete an event-financier cross reference in the table
    @Delete
    fun delete(eventFinanciersCrossRef: EventFinanciersCrossRef)
}