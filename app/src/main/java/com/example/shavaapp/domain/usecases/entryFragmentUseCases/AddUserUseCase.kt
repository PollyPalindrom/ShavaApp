package com.example.shavaapp.domain.usecases.entryFragmentUseCases

import com.example.shavaapp.data.database.Account
import com.example.shavaapp.data.repository.Repository
import javax.inject.Inject

class AddUserUseCase @Inject constructor(private val repository: Repository) {
    fun addUser(account: Account) {
        repository.uploadAccount(account)
    }
}