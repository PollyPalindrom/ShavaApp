package com.example.shavaapp.common

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.shavaapp.data.database.FoodPosition
import com.example.shavaapp.presentation.recycler.ShoppingCartPosition

object ShavaHolder {
    private val order = mutableListOf<FoodPosition>()
    private var special = mutableSetOf<ShoppingCartPosition>()

    fun addShava(item: FoodPosition) {
        order.add(item)
    }

    fun deleteShava(item: FoodPosition) {
        order.remove(item)
    }

    fun getSpecialList(): List<ShoppingCartPosition> {
        val specialList = mutableSetOf<ShoppingCartPosition>()
        order.forEach {
            var number = 0
            for (item in order) {
                if (item.name.equals(it.name) && item.description.equals(it.description)) {
                    number++
                }
            }
            specialList.add(ShoppingCartPosition(it, number))
        }
        special = specialList
        return specialList.toList()
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun deleteItem(item: ShoppingCartPosition) {
        special.remove(item)
        order.removeIf { it.name == item.item.name && it.description == item.item.description }
    }

    fun deleteAll() {
        order.removeAll(order)
    }
}