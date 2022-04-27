package com.example.shavaapp.domain.usecases.accountFragmentUseCases

import com.example.shavaapp.common.AccountCallBack
import com.example.shavaapp.common.AccountHistoryCallBack
import com.example.shavaapp.data.repository.Repository
import javax.inject.Inject

class AccountUseCase @Inject constructor(private val repository: Repository) {

    fun loadAccount(mac: String, callBack: AccountCallBack) {
        repository.loadAccount(mac, callBack)
    }

    fun loadAccountHistory(mac: String, callBack: AccountHistoryCallBack) {
        repository.loadAccountHistory(mac, callBack)
    }
}