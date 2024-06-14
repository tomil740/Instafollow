package com.example.instafollow

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.instafollow.network.AccountDetails


@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<AccountDetails>?) {
    val adapter = recyclerView.adapter as PhotoGridAdapter
    adapter.submitList(data)
}