package com.example.dobrazil.EntityRepositories

import com.example.dobrazil.Dao.EventFinanciersCrossRefDao
import com.example.dobrazil.Entity.EventFinanciersCrossRef
import javax.inject.Inject

/**
 * Repository for the EventFinanciersCrossRef
 */
class EventFinanciersRepository @Inject constructor(
    private val dao: EventFinanciersCrossRefDao
) {
    // Insert an event
    suspend fun insert(event: EventFinanciersCrossRef) = dao.insert(event)

    // Delete an event
    suspend fun delete(event: EventFinanciersCrossRef) = dao.delete(event)

    // Update an event
    suspend fun update(event: EventFinanciersCrossRef) = dao.update(event)

    // Get all events
    fun getAll() = dao.getAll()

    // Get all events by status
    fun getById(id : Int) = dao.getById(id)
}