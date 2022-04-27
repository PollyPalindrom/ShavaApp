package com.example.shavaapp.presentation.recycler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.shavaapp.databinding.ItemBinding
import com.example.shavaapp.presentation.recycler.MenuViewHolder
import com.example.shavaapp.presentation.recycler.OfferMenuDiffCallback
import com.example.shavaapp.presentation.recycler.OfferPosition

class MenuAdapter : ListAdapter<OfferPosition, MenuViewHolder>(OfferMenuDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemBinding.inflate(layoutInflater, parent, false)
        return MenuViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }
}