package com.example.shavaapp.common

import com.example.shavaapp.data.database.Account

interface AccountCallBack {
    fun onCallBack(account: Account)
}