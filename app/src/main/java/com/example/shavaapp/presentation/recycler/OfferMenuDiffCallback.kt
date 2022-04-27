package com.example.shavaapp.presentation.recycler

import androidx.recyclerview.widget.DiffUtil

class OfferMenuDiffCallback : DiffUtil.ItemCallback<OfferPosition>() {
    override fun areItemsTheSame(oldItem: OfferPosition, newItem: OfferPosition): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: OfferPosition, newItem: OfferPosition): Boolean {
        return oldItem == newItem
    }
}