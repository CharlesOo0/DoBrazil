package com.example.dobrazil.EntityRepositories

import com.example.dobrazil.Dao.EventInvitedCrossRefDao
import com.example.dobrazil.Entity.EventInvitedCrossRef
import javax.inject.Inject

/**
 * Repository for the EventInvitedCrossRef
 */
class EventInvitedRepository @Inject constructor(
    private val dao: EventInvitedCrossRefDao
) {
    // Insert an event
    suspend fun insert(event: EventInvitedCrossRef) = dao.insert(event)

    // Delete an event
    suspend fun delete(event: EventInvitedCrossRef) = dao.delete(event)

    // Update an event
    suspend fun update(event: EventInvitedCrossRef) = dao.update(event)

    // Get all events
    fun getAll() = dao.getAll()

    // Get all events by status
    fun getById(id : Int) = dao.getById(id)
}