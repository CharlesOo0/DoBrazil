package com.example.dobrazil.ModelView

import androidx.lifecycle.ViewModel
import com.example.dobrazil.Entity.EventEntity
import com.example.dobrazil.EntityRepositories.EventRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * ViewModel for the EventEntity
 */
@HiltViewModel
class EventView @Inject constructor(
    private val repository: EventRepository
) : ViewModel() {
    // Get all events
    fun getAll() = repository.getAll()

    // Get all events by status
    fun getById(id : Int) = repository.getById(id)

    // Insert an event
    suspend fun insert(event: EventEntity) = repository.insert(event)

    // Delete an event
    suspend fun delete(event: EventEntity) = repository.delete(event)

    // Update an event
    suspend fun update(event: EventEntity) = repository.update(event)
}