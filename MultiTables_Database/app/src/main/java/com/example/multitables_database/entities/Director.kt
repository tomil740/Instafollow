package com.example.multitables_database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

/*
this entity includes an realtion with other entity of type 1-> 1
 */

@Entity
data class Director(
    @PrimaryKey(autoGenerate = false)
    val directorName: String,
    val schoolName: String
)
