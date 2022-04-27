package com.example.shavaapp.presentation.account

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shavaapp.common.AccountCallBack
import com.example.shavaapp.common.AccountHistoryCallBack
import com.example.shavaapp.common.DeleteSessionCallBack
import com.example.shavaapp.data.database.Account
import com.example.shavaapp.domain.usecases.accountFragmentUseCases.AccountUseCase
import com.example.shavaapp.domain.usecases.accountFragmentUseCases.DeleteSessionUseCase
import com.example.shavaapp.presentation.recycler.HistoryPosition
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(
    private val accountUseCase: AccountUseCase,
    private val deleteSessionUseCase: DeleteSessionUseCase
) : ViewModel() {

    private val liveAccount: MutableLiveData<Account> = MutableLiveData()
    val account: LiveData<Account>
        get() = liveAccount
    private val liveAccountHistoryData: MutableLiveData<List<HistoryPosition>> = MutableLiveData()
    val history: LiveData<List<HistoryPosition>>
        get() = liveAccountHistoryData

    fun loadAccount(uid: String) {
        viewModelScope.launch(Dispatchers.IO) {
            accountUseCase.loadAccount(uid, object : AccountCallBack {
                override fun onCallBack(account: Account) {
                    liveAccount.postValue(account)
                }
            })
        }
    }

    fun loadAccountHistory(mac: String) {
        viewModelScope.launch(Dispatchers.IO) {
            accountUseCase.loadAccountHistory(mac, object : AccountHistoryCallBack {
                override fun onCallback(historyList: List<HistoryPosition>) {
                    liveAccountHistoryData.postValue(historyList)
                }
            })
        }
    }

    fun deleteSession(mac: String, callBack: DeleteSessionCallBack) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteSessionUseCase.deleteSession(mac, callBack)
        }
    }
}