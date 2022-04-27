package com.example.shavaapp.domain.usecases.common

import com.example.shavaapp.common.OfferCallBack
import com.example.shavaapp.data.repository.Repository
import javax.inject.Inject

class LoadOffersUseCase @Inject constructor(private val repository: Repository) {
    fun loadOffers(callBack: OfferCallBack) {
        repository.loadOffers(callBack)
    }
}