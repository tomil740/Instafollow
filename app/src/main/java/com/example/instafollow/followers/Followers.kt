package com.example.instafollow.followers

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.instafollow.PhotoGridAdapter
import com.example.instafollow.R
import com.example.instafollow.databinding.FragmentFollowersBinding
import com.example.instafollow.network.AccountDetails
import com.example.instafollow.network.AllInfo
import kotlinx.serialization.json.Json

class Followers : Fragment() {

    lateinit var binding:FragmentFollowersBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

         binding = FragmentFollowersBinding.inflate(inflater)
        val application = requireNotNull(activity).application
        val viewModelFactory = FollowersViewModelFactory(application)
        binding.lifecycleOwner = this
        binding.viewModel = ViewModelProvider(
            this, viewModelFactory
        ).get(FollowersViewModel::class.java)


        val theView = binding!!.viewModel

        binding.photosGrid.adapter = PhotoGridAdapter(PhotoGridAdapter.OnClickListener {
            theView!!.displayPropertyDetails(it)
        })

        theView!!.navToDetails.observe(viewLifecycleOwner, Observer {
            if (null != it)
                this.findNavController().navigate(FollowersDirections.actionFollowersToDetails(it))
        })
        theView!!.displayPropertyDetailsComplete()

        binding.bottomNavigationView.setOnItemSelectedListener(){

        theView!!.updateList(
            when(it.itemId){
                 R.id.mutual-> WhichLst.MUTAL
                 R.id.only_following -> WhichLst.FOLLOWING_NO1
                 R.id.only_followers -> WhichLst.FOLLOWER_NO2
                else -> WhichLst.FOLLOWERS1
            }
        )

            return@setOnItemSelectedListener true

        }




        return binding.root

    }


}





