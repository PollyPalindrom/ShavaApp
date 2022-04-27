package com.example.shavaapp.presentation.entry

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shavaapp.common.AuthCallBack
import com.example.shavaapp.common.DeviceStatusCallBack
import com.example.shavaapp.data.database.Account
import com.example.shavaapp.domain.usecases.entryFragmentUseCases.*
import com.google.firebase.auth.PhoneAuthProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EntryViewModel @Inject constructor(
    private val addSessionUseCase: AddSessionUseCase,
    private val addUserUseCase: AddUserUseCase,
    private val authorizationStatusUseCase: AuthorizationStatusUseCase,
    private val getDeviceMacAddressUseCase: GetDeviceMacAddressUseCase,
    private val phoneNumberVerificationUseCase: PhoneNumberVerificationUseCase
) : ViewModel() {

    private val isSignSuccessfulLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val isSignSuccessful: LiveData<Boolean>
        get() = isSignSuccessfulLiveData
    private val isAuthorizedLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val isAuthorized: LiveData<Boolean>
        get() = isAuthorizedLiveData

    fun verifyPhoneNumberWithCode(code: String) {
        viewModelScope.launch(Dispatchers.IO) {
            phoneNumberVerificationUseCase.verifyPhoneNumberWithCode(code, object : AuthCallBack {
                override fun onCallback(isSigned: Boolean) {
                    isSignSuccessfulLiveData.postValue(isSigned)
                }
            })
        }
    }

    fun getAuthorizationStatus(mac: String) {
        viewModelScope.launch(Dispatchers.IO) {
            authorizationStatusUseCase.getAuthorizationStatus(mac, object : DeviceStatusCallBack {
                override fun onCallback(isAuthorized: Boolean) {
                    isAuthorizedLiveData.postValue(isAuthorized)
                }
            })
        }
    }

    fun getDeviceMacAddress(): String {
        return getDeviceMacAddressUseCase.getDeviceMacAddress()
    }

    fun addSession(mac: String) {
        viewModelScope.launch(Dispatchers.IO) {
            addSessionUseCase.addSession(mac)
        }
    }

    fun addUser(account: Account) {
        viewModelScope.launch(Dispatchers.IO) {
            addUserUseCase.addUser(account)
        }
    }

    fun getCallBacks(): PhoneAuthProvider.OnVerificationStateChangedCallbacks {
        return phoneNumberVerificationUseCase.getCallBacks()
    }
}