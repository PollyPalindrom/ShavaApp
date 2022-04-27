package com.example.shavaapp.domain.usecases.menuFragmentUseCases

import com.example.shavaapp.common.MenuCallBack
import com.example.shavaapp.data.repository.Repository
import javax.inject.Inject

class LoadMenuPositionsUseCase @Inject constructor(private val repository: Repository) {
    fun loadMenuPositions(callBack: MenuCallBack) {
        repository.loadMenuPositions(callBack)
    }
}