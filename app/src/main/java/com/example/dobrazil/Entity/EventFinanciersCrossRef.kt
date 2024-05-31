package com.example.dobrazil.Entity

import androidx.room.Entity

/**
 * @brief Entity class for the EventFinanciersCrossRef table
 * @param eventId: Int, primary key
 * @param profilId: Int, primary key
 */
@Entity(primaryKeys = ["eventId", "profilId"])
data class EventFinanciersCrossRef(
    val eventId: Int,
    val profilId: Int
)
