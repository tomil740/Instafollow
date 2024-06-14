package com.example.multitables_database.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.multitables_database.entities.Director
import com.example.multitables_database.entities.School

//this is relation function of type 1 -> 1 when director class has the primary key of school class
data class SchoolAndDirector (
    @Embedded
    val School:School,
    //this value will match
    @Relation(
        //which is the primarey key of the first table school
        parentColumn = "schoolName",
        //and the entity with the match attribute which is the same...
        entityColumn = "schoolName"
    )
    //for 1 -> 1 relation this is the match entite (if that was 1-> N that will be an list of the other objects)
    val director: Director
)