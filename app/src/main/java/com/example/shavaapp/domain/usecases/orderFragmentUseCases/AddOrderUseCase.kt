package com.example.shavaapp.domain.usecases.orderFragmentUseCases

import com.example.shavaapp.data.repository.Repository
import com.example.shavaapp.presentation.recycler.HistoryPosition
import javax.inject.Inject

class AddOrderUseCase @Inject constructor(private val repository: Repository) {
    fun addOrderToHistory(order: HistoryPosition) {
        repository.uploadOrder(order)
    }
}