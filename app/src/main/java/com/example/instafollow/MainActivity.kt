package com.example.instafollow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Switch
import com.example.instafollow.databinding.ActivityMainBinding
import com.example.instafollow.followers.FollowersViewModel
import com.example.instafollow.followers.WhichLst

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



    }






    //get the json file data from the local files
}