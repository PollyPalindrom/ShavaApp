package com.example.shavaapp.domain.usecases.entryFragmentUseCases

import com.example.shavaapp.common.DeviceStatusCallBack
import com.example.shavaapp.data.repository.Repository
import javax.inject.Inject

class AuthorizationStatusUseCase @Inject constructor(private val repository: Repository) {
    fun getAuthorizationStatus(mac: String, callBack: DeviceStatusCallBack) {
        repository.loadDeviceAuthStatus(mac, callBack)
    }
}