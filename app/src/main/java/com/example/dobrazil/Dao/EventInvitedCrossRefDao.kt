package com.example.dobrazil.Dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import com.example.dobrazil.Entity.EventInvitedCrossRef

/**
 * @brief Dao class for the EventInvitedCrossRef table
 */
@Dao
interface EventInvitedCrossRefDao {

    // Insert an event-invited cross reference in the table
    @Insert
    fun insert(eventInvitedCrossRef: EventInvitedCrossRef)

    // Delete an event-invited cross reference in the table
    @Delete
    fun delete(eventInvitedCrossRef: EventInvitedCrossRef)

    // Update an event-invited cross reference in the table
    @Insert
    fun update(eventInvitedCrossRef: EventInvitedCrossRef)

    // Get all event-invited cross references in the table
    fun getAll()

    // Get an event-invited cross reference by id
    fun getById(id : Int)
}