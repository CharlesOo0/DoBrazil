package com.example.dobrazil.Entity

import androidx.room.Entity

/**
 * @brief Entity class for the EventInvitedCrossRef table
 * @param eventId: Int, primary key
 * @param profilId: Int, primary key
 */
@Entity(primaryKeys = ["eventId", "profilId"])
data class EventInvitedCrossRef(
    val eventId: Int,
    val profilId: Int
)