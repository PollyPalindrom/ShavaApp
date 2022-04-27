package com.example.shavaapp.common

import com.example.shavaapp.data.database.Account


object CurrentUser {
    val account by lazy { Account() }
    var sessionId: String = ""
    var accountId: String = ""
}
