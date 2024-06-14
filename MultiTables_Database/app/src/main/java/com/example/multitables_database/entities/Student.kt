package com.example.multitables_database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

/*
the Student is an table "entity" in the data base with realtion with other entity of type 1->N with school entity
*/

@Entity
data class Student(
    @PrimaryKey(autoGenerate = false)
    val studentName: String,
    val schoolName: String,
    val subjectName:String
)