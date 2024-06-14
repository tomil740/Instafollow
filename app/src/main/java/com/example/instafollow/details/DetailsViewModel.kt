package com.example.instafollow.details

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import com.example.instafollow.R
import com.example.instafollow.followers.FollowersViewModel
import com.example.instafollow.network.AccountDetails

class DetailsViewModel(accountDetails: AccountDetails, app: Application)
: AndroidViewModel(app) {

    private val _selectedProperty = MutableLiveData<AccountDetails>()
    val selectedProperty: LiveData<AccountDetails>
    get() = _selectedProperty

    private val _onClickLink = MutableLiveData<String?>()
    val onClickLink: LiveData<String?>
        get() = _onClickLink


    init {
        _selectedProperty.value = accountDetails
    }

    fun onClickLink(){
        _onClickLink.value = _selectedProperty.value!!.value
    }

    fun DoneLink(){
        _onClickLink.value = null
    }

}