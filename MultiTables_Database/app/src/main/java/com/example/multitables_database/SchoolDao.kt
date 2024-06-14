package com.example.multitables_database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.multitables_database.entities.Director
import com.example.multitables_database.entities.School
import com.example.multitables_database.entities.Student
import com.example.multitables_database.entities.Subject
import com.example.multitables_database.entities.relations.SchoolAndDirector
import com.example.multitables_database.entities.relations.StudentWithSubject

@Dao
interface SchoolDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSchool(school: School)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDirector(director: Director)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStudent(student: Student)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSubject(subject: Subject)

    @Query("SELECT * FROM school WHERE schoolName = :schoolName")
    suspend fun getSchool(schoolName:String):School

    @Query("SELECT * FROM Director WHERE schoolName = :schoolName")
    suspend fun getDirector(schoolName:String):Director

    @Transaction
    //the query will only search in the school entity , because of the return value room will search,
    //the director entity as well because its bund to school entity as attribute
    @Query("SELECT * FROM school WHERE schoolName = :schoolName")
    suspend fun getSchoolAndDirectorWithSchoolName(schoolName: String): List<SchoolAndDirector>

    @Transaction
    @Query("SELECT * FROM Student WHERE studentName = :studentName")
    suspend fun getStudentAndSubject(studentName: String): List<StudentWithSubject>




}