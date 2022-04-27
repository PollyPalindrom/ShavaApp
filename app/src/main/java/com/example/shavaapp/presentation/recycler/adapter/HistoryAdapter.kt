package com.example.shavaapp.presentation.recycler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shavaapp.databinding.OrderHistoryItemBinding
import com.example.shavaapp.presentation.recycler.HistoryPosition
import com.example.shavaapp.presentation.recycler.HistoryViewHolder

class HistoryAdapter : RecyclerView.Adapter<HistoryViewHolder>() {
    var items = listOf<HistoryPosition>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val binding =
            OrderHistoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HistoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size
}