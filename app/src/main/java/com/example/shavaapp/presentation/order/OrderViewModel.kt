package com.example.shavaapp.presentation.order

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shavaapp.domain.usecases.orderFragmentUseCases.AddOrderUseCase
import com.example.shavaapp.presentation.recycler.HistoryPosition
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrderViewModel @Inject constructor(private val addOrderUseCase: AddOrderUseCase) :
    ViewModel() {

    fun addOrderToHistory(order: HistoryPosition) {
        viewModelScope.launch(Dispatchers.IO) {
            addOrderUseCase.addOrderToHistory(order)
        }
    }
}