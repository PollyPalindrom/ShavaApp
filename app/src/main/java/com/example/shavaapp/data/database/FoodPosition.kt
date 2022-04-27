package com.example.shavaapp.data.database

data class FoodPosition(
    var id: Long = 0,
    var name: String = "",
    var imgUrl: String = "",
    var description: String = "",
    var price: List<Double> = arrayListOf(),
    var weight: List<Double> = arrayListOf()
) {
    fun isEmpty(): Boolean =
        id == 0L && name == "" && imgUrl == "" && description == "" && price.isEmpty() && weight.isEmpty()
}
