package com.example.shavaapp.presentation.recycler

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import com.example.shavaapp.databinding.OrderHistoryItemBinding

class HistoryViewHolder(private val binding: OrderHistoryItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    @SuppressLint("SetTextI18n")
    fun bind(item: HistoryPosition) {
        binding.cashBack.text = "+ " + item.cashback
        binding.orderBody.text = item.body
    }
}