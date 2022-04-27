package com.example.shavaapp.presentation.recycler

import androidx.recyclerview.widget.RecyclerView
import com.example.shavaapp.common.FullScreenListener
import com.example.shavaapp.common.ShavaHolder
import com.example.shavaapp.common.ShavaListener
import com.example.shavaapp.common.loadImgByUrl
import com.example.shavaapp.data.database.FoodPosition
import com.example.shavaapp.databinding.MenuPositionBinding

class MenuPositionViewHolder(
    private val binding: MenuPositionBinding,
    private val listenerFull: FullScreenListener
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: FoodPosition) {
        loadImgByUrl(item.imgUrl, binding.shaurmaimg)

        binding.addToCart.setOnClickListener {
            listenerFull.createFullScreen(item.imgUrl, item.name)
        }

        binding.shaurmaimg.setOnClickListener {
            listenerFull.createFullScreen(item.imgUrl, item.name)
        }
    }
}