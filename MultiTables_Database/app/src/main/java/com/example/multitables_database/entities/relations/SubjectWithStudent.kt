package com.example.multitables_database.entities.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.multitables_database.entities.Student
import com.example.multitables_database.entities.Subject

data class SubjectWithStudent(
    @Embedded
    val subject:Subject,
    @Relation(
        parentColumn = "subjectName",
        entityColumn = "studentName",
        associateBy = Junction(SubjectStudentCrossRef::class)
    )
    val students: List<Student>
)