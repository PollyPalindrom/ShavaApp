package com.example.shavaapp.common

import com.example.shavaapp.presentation.recycler.HistoryPosition

interface AccountHistoryCallBack {
    fun onCallback(historyList: List<HistoryPosition>)
}