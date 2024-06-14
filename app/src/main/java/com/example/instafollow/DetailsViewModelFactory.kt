package com.example.instafollow

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.instafollow.details.DetailsViewModel
import com.example.instafollow.network.AccountDetails

class DetailsViewModelFactory(
    private val accountInfo: AccountDetails,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailsViewModel::class.java)) {
            return DetailsViewModel(accountInfo, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}