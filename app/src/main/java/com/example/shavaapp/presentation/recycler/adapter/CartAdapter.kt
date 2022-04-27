package com.example.shavaapp.presentation.recycler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.shavaapp.presentation.recycler.CartViewHolder
import com.example.shavaapp.common.ShavaHolder
import com.example.shavaapp.databinding.CartPositionBinding
import com.example.shavaapp.presentation.recycler.CartDiffCallback
import com.example.shavaapp.presentation.recycler.ItemTouchHelperAdapter
import com.example.shavaapp.presentation.recycler.ShoppingCartPosition

class CartAdapter : ListAdapter<ShoppingCartPosition, CartViewHolder>(CartDiffCallback()),
    ItemTouchHelperAdapter {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = CartPositionBinding.inflate(layoutInflater, parent, false)
        return CartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int): Boolean {
        TODO("Not yet implemented")
    }

    override fun onItemDismiss(position: Int) {
        val holder = ShavaHolder;
        holder.deleteItem(getItem(position))
        println(getItem(position).number)
        submitList(holder.getSpecialList())
    }
}