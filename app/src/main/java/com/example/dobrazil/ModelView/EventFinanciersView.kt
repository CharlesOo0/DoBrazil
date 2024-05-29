package com.example.dobrazil.ModelView

import com.example.dobrazil.Entity.EventFinanciersCrossRef
import com.example.dobrazil.EntityRepositories.EventFinanciersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * ViewModel for the EventFinanciersCrossRef
 */
@HiltViewModel
class EventFinanciersView @Inject constructor(
    private val repository: EventFinanciersRepository
) {
    // Insert an event
    suspend fun insert(event: EventFinanciersCrossRef) = repository.insert(event)

    // Delete an event
    suspend fun delete(event: EventFinanciersCrossRef) = repository.delete(event)

    // Update an event
    suspend fun update(event: EventFinanciersCrossRef) = repository.update(event)

    // Get all events
    fun getAll() = repository.getAll()

    // Get all events by status
    fun getById(id : Int) = repository.getById(id)
}