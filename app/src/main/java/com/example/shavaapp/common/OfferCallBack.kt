package com.example.shavaapp.common

import com.example.shavaapp.presentation.recycler.OfferPosition

interface OfferCallBack {
    fun onCallback(positionsList: List<OfferPosition>)
}