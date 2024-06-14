package com.example.multitables_database

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.multitables_database.databinding.ActivityMainBinding
import com.example.multitables_database.entities.Director
import com.example.multitables_database.entities.School
import com.example.multitables_database.entities.Student
import com.example.multitables_database.entities.Subject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding:ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        val dao =  SchoolDatabase.getInstance(this@MainActivity).schoolDao


        CoroutineScope(Dispatchers.IO).launch {
            dao.insertSchool(School("golda"))
            dao.insertDirector(Director("Hget","golda"))


            dao.insertStudent(Student("liam","golda","gao"))
            dao.insertStudent(Student("tom","golda","gao"))
            dao.insertStudent(Student("noy","golda","gao"))
            dao.insertStudent(Student("tal","golda","gao"))
            dao.insertStudent(Student("maor","golda","gao"))

            dao.insertSubject(Subject("gao","liam"))

            dao.insertSubject(Subject("yo","liam"))
            dao.insertSubject(Subject("tewrrt","liam"))



            val OutCome = dao.getStudentAndSubject("liam")
            Log.i("this","the matched school object from the connected object $OutCome")
            Log.i("this","the matched school object from the regular object ${dao.getDirector("golda")}")





        }

        setContentView(binding.root)


    }
}