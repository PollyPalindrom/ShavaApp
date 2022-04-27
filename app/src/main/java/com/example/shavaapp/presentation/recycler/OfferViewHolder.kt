package com.example.shavaapp.presentation.recycler

import androidx.recyclerview.widget.RecyclerView
import com.example.shavaapp.common.*
import com.example.shavaapp.databinding.OfferItemBinding

class OfferViewHolder(
    private val binding: OfferItemBinding,
    private val listenerFull: OffersFullScreenListener
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: OfferPosition) {
        loadImgByUrl(item.imgUrl, binding.offerImage)
        binding.offerText.text = item.name
        binding.root.setOnClickListener {
            listenerFull.createFullScreen(item.imgUrl, item.name, item.description)
        }
    }
}