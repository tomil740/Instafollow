package com.example.instafollow.details

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.instafollow.DetailsViewModelFactory
import com.example.instafollow.databinding.DetailsBinding


class Details: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val application = requireNotNull(activity).application
        val binding = DetailsBinding.inflate(inflater)
        binding.lifecycleOwner = this
        val marsProperty = DetailsArgs.fromBundle(requireArguments()).item
        val viewModelFactory = DetailsViewModelFactory(marsProperty, application)
        binding.viewModel = ViewModelProvider(
            this, viewModelFactory).get(DetailsViewModel::class.java)

        val theView = binding.viewModel

        theView!!.onClickLink.observe(viewLifecycleOwner, Observer {
            if(null != it)
               GetUrlFromIntent(view,"https://www.instagram.com/$it")
        })
        theView.DoneLink()

        return binding.root
    }

    fun GetUrlFromIntent(view: View?,url:String) {
        val url = "$url"
        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse(url)
        startActivity(i)
    }
}