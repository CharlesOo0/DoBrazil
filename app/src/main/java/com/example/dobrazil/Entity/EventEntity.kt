package com.example.dobrazil.Entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.Date

/**
 * @brief Entity class for the Event table
 * @param idEvent: Int, primary key
 * @param startDate: Date, start date of the event
 * @param endDate: Date, end date of the event
 * @param idHost: Int, foreign key to the Profil table
 * @param location: String, location of the event
 * @param title: String, title of the event
 * @param description: String, description of the event
 */
@Entity(foreignKeys = [ForeignKey(entity = ProfilEntity::class, parentColumns = ["idProfil"], childColumns = ["idHost"])])
data class EventEntity(
    @PrimaryKey val idEvent: Int,
    val startDate: Date,
    val endDate: Date,
    val idHost: Int,
    val location: String,
    val title: String,
    val description: String
)
