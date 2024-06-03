package com.example.dobrazil.Dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
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
    @Query("SELECT * FROM EventInvitedCrossRef")
    fun getAll() : List<EventInvitedCrossRef>

    // Get an event-invited cross reference by id
    @Query("SELECT * FROM EventInvitedCrossRef WHERE eventId = :id")
    fun getById(id : Int): EventInvitedCrossRef

    // Get an event-invited cross reference by its event and profile
    @Query("SELECT * FROM EventInvitedCrossRef WHERE eventId = :eventId AND profilId = :profilId")
    fun getByEventAndProfile(eventId: Int, profilId: Int): EventInvitedCrossRef?

    // Get a profil id by its username
    @Query("SELECT idProfil FROM ProfilEntity WHERE username = :username")
    fun getIdByUsername(username: String): Int

    // Insert an event-invited cross reference using usernames of event and profile
    @Transaction
    fun insertWithUsernames(eventId: Int, profileUsername: String) {
        val profilId = getIdByUsername(profileUsername)
        val existingRelation = getByEventAndProfile(eventId, profilId)
        if (existingRelation == null) {
            insert(EventInvitedCrossRef(eventId, profilId))
        }
    }

    // Delete an event-invited cross reference using usernames of event and profile
    @Transaction
    fun deleteWithUsernames(eventId: Int, profileUsername: String) {
        val profilId = getIdByUsername(profileUsername)
        val existingRelation = getByEventAndProfile(eventId, profilId)
        if (existingRelation != null) {
            delete(existingRelation)
        }
    }
}