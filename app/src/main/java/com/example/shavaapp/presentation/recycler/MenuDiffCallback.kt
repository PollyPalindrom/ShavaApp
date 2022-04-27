package com.example.shavaapp.presentation.recycler

import androidx.recyclerview.widget.DiffUtil
import com.example.shavaapp.data.database.FoodPosition

class MenuDiffCallback : DiffUtil.ItemCallback<FoodPosition>() {
    override fun areItemsTheSame(oldItem: FoodPosition, newItem: FoodPosition): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: FoodPosition, newItem: FoodPosition): Boolean {
        return oldItem == newItem
    }
}