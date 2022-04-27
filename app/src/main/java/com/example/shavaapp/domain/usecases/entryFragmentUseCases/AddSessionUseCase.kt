package com.example.shavaapp.domain.usecases.entryFragmentUseCases

import com.example.shavaapp.data.repository.Repository
import javax.inject.Inject

class AddSessionUseCase @Inject constructor(private val repository: Repository) {
    fun addSession(mac: String) {
        repository.addSession(mac)
    }
}