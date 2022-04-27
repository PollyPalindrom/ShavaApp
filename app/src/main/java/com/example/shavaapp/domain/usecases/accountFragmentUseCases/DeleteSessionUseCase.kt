package com.example.shavaapp.domain.usecases.accountFragmentUseCases

import com.example.shavaapp.common.DeleteSessionCallBack
import com.example.shavaapp.data.repository.Repository
import javax.inject.Inject

class DeleteSessionUseCase @Inject constructor(private val repository: Repository) {

    fun deleteSession(mac: String, callBack: DeleteSessionCallBack) {
        repository.deleteSession(mac, callBack)
    }
}