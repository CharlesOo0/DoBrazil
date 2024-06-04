package com.example.dobrazil.Dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.dobrazil.Entity.EventEntity

/**
 * @brief Dao class for the Event table
 */
@Dao
interface EventDao {

    // Insert an event in the table
    @Insert
    fun insert(event: EventEntity)

    // Update an event in the table
    @Update
    fun update(event: EventEntity)

    // Delete an event in the table
    @Delete
    fun delete(event: EventEntity)

    // Get all events in the table
    @Query("SELECT * FROM EventEntity")
    fun getAll(): List<EventEntity>

    // Get an event by its id
    @Query("SELECT * FROM EventEntity WHERE idEvent = :id")
    fun getById(id: Int): EventEntity

    // Get by title
    @Query("SELECT * FROM EventEntity WHERE title = :title")
    fun getByTitle(title: String): EventEntity

    // Check title already exist
    @Query("SELECT * FROM EventEntity WHERE title = :title")
    fun checkTitle(title: String): EventEntity?

    // Get events where not invited or creator
    @Query("""
    SELECT * FROM EventEntity 
    WHERE idEvent NOT IN (
        SELECT eventId FROM EventInvitedCrossRef WHERE profilId = :profilId
    ) AND idHost != :profilId
""")
    fun getEventsWhereNotInvitedOrCreator(profilId: Int): List<EventEntity>

    // Get events made by a user
    @Query("SELECT * FROM EventEntity WHERE idHost = :profilId")
    fun getEventsByHost(profilId: Int): List<EventEntity>

    // Get events where user is invited
    @Query("""
    SELECT * FROM EventEntity
    WHERE idEvent IN (
        SELECT eventId FROM EventInvitedCrossRef WHERE profilId = :profilId
    )
    AND idHost != :profilId
""")
    fun getEventsWhereUserIsInvited(profilId: Int): List<EventEntity>
}