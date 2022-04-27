package com.example.shavaapp.common

import com.example.shavaapp.data.database.FoodPosition

interface MenuCallBack {
    fun onCallback(positionsList: List<FoodPosition>)
}