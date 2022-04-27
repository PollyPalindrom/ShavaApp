package com.example.shavaapp.presentation.recycler

import androidx.recyclerview.widget.DiffUtil

class CartDiffCallback : DiffUtil.ItemCallback<ShoppingCartPosition>() {
    override fun areItemsTheSame(
        oldItem: ShoppingCartPosition,
        newItem: ShoppingCartPosition
    ): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: ShoppingCartPosition,
        newItem: ShoppingCartPosition
    ): Boolean {
        return oldItem == newItem
    }
}