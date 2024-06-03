package com.example.dobrazil.EntityRepositories

import com.example.dobrazil.Dao.EventDao
import com.example.dobrazil.Entity.EventEntity
import javax.inject.Inject

/**
 * Repository for the EventEntity
 */
class EventRepository @Inject constructor(
    private val dao: EventDao
) {
    // Insert an event
    suspend fun insert(event: EventEntity) = dao.insert(event)

    // Delete an event
    suspend fun delete(event: EventEntity) = dao.delete(event)

    // Update an event
    suspend fun update(event: EventEntity) = dao.update(event)

    // Get all events
    fun getAll() = dao.getAll()

    // Get all events by status
    fun getById(id : Int) = dao.getById(id)

    // Get by title
    fun getByTitle(title: String) = dao.getByTitle(title)

    // Check title already exist
    fun checkTitle(title: String) = dao.checkTitle(title)
}