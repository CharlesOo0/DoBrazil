package com.example.dobrazil.Dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
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

    // Update an event-financier cross reference in the table
    @Update
    fun update(eventFinanciersCrossRef: EventFinanciersCrossRef)

    // Get all event-financier cross references in the table
    @Query("SELECT * FROM EventFinanciersCrossRef")
    fun getAll(): List<EventFinanciersCrossRef>

    // Get an event-financier cross reference by id
    @Query("SELECT * FROM EventFinanciersCrossRef WHERE eventId = :id")
    fun getById(id : Int): EventFinanciersCrossRef
}