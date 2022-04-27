package com.example.shavaapp.presentation.recycler

import androidx.recyclerview.widget.RecyclerView
import com.example.shavaapp.R
import com.example.shavaapp.databinding.ItemBinding
import com.squareup.picasso.Picasso

class MenuViewHolder(private val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: OfferPosition) {
        Picasso.get()
            .load(item.imgUrl)
            .error(R.drawable.ic_baseline_account_circle_24)
            .into(binding.image)
    }
}