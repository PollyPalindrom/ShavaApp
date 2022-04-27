package com.example.shavaapp.common

import android.util.Log
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider

class VerificationManager {

    private var forceResendingToken: PhoneAuthProvider.ForceResendingToken? = null
    private var authCallBacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks
    private lateinit var verificationId: String
    private var firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

    init {
        authCallBacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onCodeSent(
                verId: String,
                token: PhoneAuthProvider.ForceResendingToken
            ) {
                super.onCodeSent(verId, token)
                Log.d("mine", "onCodeSent $verId")
                forceResendingToken = token
                verificationId = verId
            }

            override fun onVerificationCompleted(phoneAuthCredential: PhoneAuthCredential) {}

            override fun onVerificationFailed(e: FirebaseException) {
                Log.d("mine", "Ver failed $e")

            }
        }
    }

    fun getCallBacks(): PhoneAuthProvider.OnVerificationStateChangedCallbacks {
        return authCallBacks
    }

    fun verifyPhoneNumberWithCode(code: String, authCallBack: AuthCallBack) {
        val credential = PhoneAuthProvider.getCredential(verificationId, code);
        signInWithPhoneAuthCredential(credential, authCallBack)
    }

    private fun signInWithPhoneAuthCredential(
        credential: PhoneAuthCredential,
        authCallBack: AuthCallBack?
    ) {
        firebaseAuth.signInWithCredential(credential)
            .addOnSuccessListener {
                val phone = firebaseAuth.currentUser?.phoneNumber
                CurrentUser.account.phone = phone ?: ""
                authCallBack?.onCallback(true)
            }
            .addOnFailureListener { e ->
                Log.d("mine", "sign in with auth cred $e")
                authCallBack?.onCallback(false)
            }
    }

}