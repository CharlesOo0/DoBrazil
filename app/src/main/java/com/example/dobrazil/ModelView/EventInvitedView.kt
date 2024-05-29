package com.example.dobrazil.ModelView

import androidx.lifecycle.ViewModel
import com.example.dobrazil.Entity.EventInvitedCrossRef
import com.example.dobrazil.EntityRepositories.EventInvitedRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * ViewModel for the EventInvitedCrossRef
 */
@HiltViewModel
class EventInvitedView @Inject constructor(
    private val eventRepository: EventInvitedRepository
) : ViewModel() {

    // Get all events
    fun getAll() = eventRepository.getAll()

    // Get all events by status
    fun getById(id : Int) = eventRepository.getById(id)

    // Insert an event
    suspend fun insert(event: EventInvitedCrossRef) = eventRepository.insert(event)

    // Delete an event
    suspend fun delete(event: EventInvitedCrossRef) = eventRepository.delete(event)

    // Update an event
    suspend fun update(event: EventInvitedCrossRef) = eventRepository.update(event)
}