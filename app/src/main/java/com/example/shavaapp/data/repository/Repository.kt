package com.example.shavaapp.data.repository

import com.example.shavaapp.common.*
import com.example.shavaapp.data.database.Account
import com.example.shavaapp.data.database.DatabaseDataSource
import com.example.shavaapp.presentation.recycler.HistoryPosition
import com.google.firebase.auth.PhoneAuthProvider
import javax.inject.Inject

class Repository @Inject constructor(
    private val databaseDataSource: DatabaseDataSource,
    private val verificationManager: VerificationManager,
    private val getMacAddressManager: GetMacAddressManager
) {
    fun loadDeviceAuthStatus(mac: String, myCallBack: DeviceStatusCallBack) {
        databaseDataSource.loadDeviceAuthStatus(mac, myCallBack)
    }

    fun loadAccount(mac: String, myCallBack: AccountCallBack) {
        databaseDataSource.loadAccount(mac, myCallBack)
    }

    fun loadAccountHistory(mac: String, myCallBack: AccountHistoryCallBack) {
        databaseDataSource.loadAccountHistory(mac, myCallBack)
    }

    fun addSession(mac: String) {
        databaseDataSource.addSession(mac)

    }

    fun uploadAccount(account: Account) {
        databaseDataSource.uploadAccount(account)
    }

    fun loadOffers(myCallBack: OfferCallBack) {
        databaseDataSource.loadOffers(myCallBack)
    }

    fun loadMenuPositions(myCallBack: MenuCallBack) {
        databaseDataSource.loadMenuPositions(myCallBack)
    }

    fun uploadOrder(order: HistoryPosition) {
        databaseDataSource.uploadOrder(order)
    }

    fun deleteSession(mac: String, callBack: DeleteSessionCallBack) {
        databaseDataSource.deleteSession(mac, callBack)
    }

    fun verifyPhoneNumberWithCode(code: String, authCallBack: AuthCallBack) {
        verificationManager.verifyPhoneNumberWithCode(code, authCallBack)
    }

    fun getDeviceMacAddress(): String {
        return getMacAddressManager.getDeviceMacAddress()
    }

    fun getCallBacks(): PhoneAuthProvider.OnVerificationStateChangedCallbacks {
        return verificationManager.getCallBacks()
    }
}