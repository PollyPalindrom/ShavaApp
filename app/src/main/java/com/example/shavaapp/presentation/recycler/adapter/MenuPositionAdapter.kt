package com.example.shavaapp.presentation.recycler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.shavaapp.common.FullScreenListener
import com.example.shavaapp.common.ShavaHolder
import com.example.shavaapp.data.database.FoodPosition
import com.example.shavaapp.databinding.MenuPositionBinding
import com.example.shavaapp.presentation.recycler.MenuDiffCallback
import com.example.shavaapp.presentation.recycler.MenuPositionViewHolder

class MenuPositionAdapter(
    private val listenerFull: FullScreenListener
) :
    ListAdapter<FoodPosition, MenuPositionViewHolder>(
        MenuDiffCallback()
    ) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuPositionViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = MenuPositionBinding.inflate(layoutInflater, parent, false)
        return MenuPositionViewHolder(binding, listenerFull)
    }

    override fun onBindViewHolder(holder: MenuPositionViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }
}