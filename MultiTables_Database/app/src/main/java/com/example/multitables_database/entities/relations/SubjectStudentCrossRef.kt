package com.example.multitables_database.entities.relations

import androidx.room.Entity

@Entity(primaryKeys = ["subjectName","studentName"])
data class SubjectStudentCrossRef (
    val subjectName:String,
    val studentName:String
)

