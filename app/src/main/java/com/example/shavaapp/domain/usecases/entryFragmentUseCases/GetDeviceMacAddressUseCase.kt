package com.example.shavaapp.domain.usecases.entryFragmentUseCases

import com.example.shavaapp.data.repository.Repository
import javax.inject.Inject

class GetDeviceMacAddressUseCase @Inject constructor(private val repository: Repository) {
    fun getDeviceMacAddress(): String {
        return repository.getDeviceMacAddress()
    }
}