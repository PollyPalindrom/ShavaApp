package com.example.shavaapp.presentation.recycler

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import com.example.shavaapp.common.ShavaHolder
import com.example.shavaapp.databinding.CartPositionBinding

class CartViewHolder(private val binding: CartPositionBinding) :
    RecyclerView.ViewHolder(binding.root) {
    @SuppressLint("SetTextI18n")
    fun bind(item: ShoppingCartPosition) {
        var holder = ShavaHolder
        binding.title.text = item.item.name
        binding.priceTitle.text = (item.item.price[0] * item.number).toString()
        binding.numberTitle.text = item.number.toString()
        if (item.item.description != "") {
            binding.description.text = item.item.description
        } else {
            binding.description.text = "Classic"
        }
        binding.plus.setOnClickListener {
            if (item.number < 99) {
                holder.getSpecialList().forEach {
                    if (it.item.name == item.item.name && it.item.description == item.item.description) {
                        binding.numberTitle.text = (it.number + 1).toString()
                    }
                }
                holder.addShava(item.item)
            }
        }
        binding.minus.setOnClickListener {
            if (item.number > 0) {
                holder.getSpecialList().forEach {
                    if (it.item.name == item.item.name && it.item.description == item.item.description) {
                        binding.numberTitle.text = (it.number - 1).toString()
                    }
                }
                holder.deleteShava(item.item)
            }
        }
    }
}