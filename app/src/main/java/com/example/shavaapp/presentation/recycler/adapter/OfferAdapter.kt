package com.example.shavaapp.presentation.recycler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.shavaapp.common.FullScreenListener
import com.example.shavaapp.common.OffersFullScreenListener
import com.example.shavaapp.common.ShavaHolder
import com.example.shavaapp.common.ShavaListener
import com.example.shavaapp.databinding.OfferItemBinding
import com.example.shavaapp.presentation.recycler.OfferDiffCallback
import com.example.shavaapp.presentation.recycler.OfferPosition
import com.example.shavaapp.presentation.recycler.OfferViewHolder

class OfferAdapter(
    private val listenerFull: OffersFullScreenListener
) :
    ListAdapter<OfferPosition, OfferViewHolder>(
        OfferDiffCallback()
    ) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OfferViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = OfferItemBinding.inflate(layoutInflater, parent, false)
        return OfferViewHolder(binding,listenerFull)
    }

    override fun onBindViewHolder(holder: OfferViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

}