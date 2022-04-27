package com.example.shavaapp.domain.usecases.entryFragmentUseCases

import com.example.shavaapp.common.AuthCallBack
import com.example.shavaapp.data.repository.Repository
import com.google.firebase.auth.PhoneAuthProvider
import javax.inject.Inject

class PhoneNumberVerificationUseCase @Inject constructor(private val repository: Repository) {

    fun verifyPhoneNumberWithCode(code: String, callBack: AuthCallBack) {
        repository.verifyPhoneNumberWithCode(code, callBack)
    }

    fun getCallBacks(): PhoneAuthProvider.OnVerificationStateChangedCallbacks {
        return repository.getCallBacks()
    }
}