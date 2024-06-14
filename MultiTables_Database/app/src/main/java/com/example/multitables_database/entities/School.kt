package com.example.multitables_database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

/*
this entity got only one attribute and this its primary key
 */

@Entity
data class School(
    @PrimaryKey(autoGenerate = false)
    val schoolName:String
)
