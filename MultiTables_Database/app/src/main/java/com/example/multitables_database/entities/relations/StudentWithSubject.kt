package com.example.multitables_database.entities.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.multitables_database.entities.Student
import com.example.multitables_database.entities.Subject

data class StudentWithSubject(
    @Embedded val student: Student,
    @Relation(
        parentColumn = "studentName",
        entityColumn = "subjectName",
        associateBy = Junction(SubjectStudentCrossRef::class)
    )
    val subjects: List<Subject>
)
