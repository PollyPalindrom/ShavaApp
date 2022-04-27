package com.example.shavaapp.data.database

import android.util.Log
import com.example.shavaapp.common.*
import com.example.shavaapp.presentation.recycler.HistoryPosition
import com.example.shavaapp.presentation.recycler.OfferPosition
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject

class DatabaseDataSource {

    fun loadDeviceAuthStatus(mac: String, myCallBack: DeviceStatusCallBack) {
        val db = FirebaseFirestore.getInstance()
        db.collection("session").whereEqualTo("mac", mac)
            .get()
            .addOnSuccessListener { result ->
                val documents = result.documents
                if (!documents.isNullOrEmpty()) {
                    CurrentUser.sessionId = documents[0].id
                }
                myCallBack.onCallback(!documents.isNullOrEmpty())
            }
            .addOnFailureListener {
                Log.d("mine", "Error upload account")
                throw Exception("Database upload", it)
            }
    }

    fun loadAccount(mac: String, myCallBack: AccountCallBack) {
        val db = FirebaseFirestore.getInstance()
        db.collection("user").whereEqualTo("mac", mac)
            .get()
            .addOnSuccessListener { result ->
                val documents = result.documents
                val account =
                    if (documents.isEmpty()) Account() else documents[0].toObject<Account>()
                if (!documents.isNullOrEmpty()) {
                    CurrentUser.accountId = documents[0].id
                }
                myCallBack.onCallBack(account!!)
            }
            .addOnFailureListener {
                Log.d("mine", "Error upload account")
                throw Exception("Database upload", it)
            }
    }

    fun loadAccountHistory(mac: String, myCallBack: AccountHistoryCallBack) {
        val db = FirebaseFirestore.getInstance()
        db.collection("order").whereEqualTo("mac", mac)
            .get()
            .addOnSuccessListener { result ->
                val list = mutableListOf<HistoryPosition>()
                for (document in result.documents) {
                    list.add(document.toObject<HistoryPosition>()!!)
                }
                myCallBack.onCallback(list)
            }
            .addOnFailureListener {
                Log.d("mine", "Error upload account")
                throw Exception("Database upload", it)
            }
    }

    fun addSession(mac: String) {
        val map = hashMapOf<String, String>()
        map["mac"] = mac
        val db = FirebaseFirestore.getInstance()
        db.collection("session")
            .add(map)
            .addOnSuccessListener {
                CurrentUser.sessionId = it.id
            }
            .addOnFailureListener {
                Log.d("mine", "Error upload session")
                throw Exception("Database upload", it)
            }

    }

    fun uploadAccount(account: Account) {
        val db = FirebaseFirestore.getInstance()

        db.collection("user")
            .add(account)
            .addOnFailureListener {
                Log.d("mine", "Error upload account")
                throw Exception("Database upload", it)
            }
    }

    fun loadOffers(myCallBack: OfferCallBack) {
        val db = FirebaseFirestore.getInstance()
        db.collection("offer")
            .get()
            .addOnSuccessListener { result ->
                val list = mutableListOf<OfferPosition>()
                for (document in result.documents) {
                    list.add(document.toObject<OfferPosition>()!!)
                }
                myCallBack.onCallback(list)
            }
            .addOnFailureListener {
                Log.d("mine", "Error load menu")
                throw Exception("Database load", it)
            }
    }

    fun loadMenuPositions(myCallBack: MenuCallBack) {
        val db = FirebaseFirestore.getInstance()
        db.collection("shaurma")
            .get()
            .addOnSuccessListener { result ->
                val list = mutableListOf<FoodPosition>()
                for (document in result.documents) {
                    list.add(document.toObject<FoodPosition>()!!)
                }
                myCallBack.onCallback(list)
            }
            .addOnFailureListener {
                Log.d("mine", "Error load menu")
                throw Exception("Database load", it)
            }
    }

    fun uploadOrder(order: HistoryPosition) {
        val db = FirebaseFirestore.getInstance()
        db.collection("order")
            .add(order)
            .addOnFailureListener {
                Log.d("mine", "Error upload order")
                throw Exception("Database upload", it)
            }
    }

    fun deleteSession(mac: String, callBack: DeleteSessionCallBack) {
        if (CurrentUser.sessionId.isBlank() || CurrentUser.sessionId.isEmpty()) {
            callBack.onCallBack()
            return
        }
        val db = FirebaseFirestore.getInstance()
        db.collection("session")
            .document(CurrentUser.sessionId)
            .delete()
            .addOnSuccessListener {
                callBack.onCallBack()
            }
            .addOnFailureListener {
                Log.d("mine", "Error upload order")
                throw Exception("Database upload", it)
            }
    }
}