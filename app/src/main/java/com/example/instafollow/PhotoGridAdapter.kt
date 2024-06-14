package com.example.instafollow

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.instafollow.databinding.GridViewItemBinding
import com.example.instafollow.network.AccountDetails

class PhotoGridAdapter(private val onClickListener: OnClickListener) : ListAdapter<AccountDetails, PhotoGridAdapter.AccountInfoViewHolder>(DiffCallback) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PhotoGridAdapter.AccountInfoViewHolder {
        return AccountInfoViewHolder(GridViewItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: PhotoGridAdapter.AccountInfoViewHolder, position: Int) {
        val accountDetails = getItem(position)
        holder.itemView.setOnClickListener{
            onClickListener.onClick(accountDetails)
        }
        holder.bind(accountDetails)
    }
    companion object DiffCallback : DiffUtil.ItemCallback<AccountDetails>() {
        override fun areItemsTheSame(oldItem: AccountDetails, newItem: AccountDetails): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: AccountDetails, newItem: AccountDetails): Boolean {
            return oldItem.id == newItem.id
        }
    }


    class AccountInfoViewHolder(private var binding: GridViewItemBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(AccountInfo: AccountDetails) {
            binding.property = AccountInfo
            binding.executePendingBindings()
        }
    }

    class OnClickListener(val clickListener: (AccountInfo: AccountDetails) -> Unit){
        fun onClick(AccountInfo:AccountDetails) = clickListener(AccountInfo)

    }


}


