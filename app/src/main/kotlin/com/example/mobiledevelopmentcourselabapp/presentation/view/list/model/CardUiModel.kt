package com.example.mobiledevelopmentcourselabapp.presentation.view.list.model

interface ItemUiModel


class CardUiModel(
    val name: String,
    val photoUrl: String,
    val price: Int,
    val frequency: Int,
    val memory: Int,
    val power: Int,
    var isExpanded: Boolean = false
        ): ItemUiModel{
    val formattedName = "GeForce RTX 40${name}0"
    val formattedPrice = "$price руб."
    val formattedFrequency = "Частота: $frequency Ггц"
    val formattedMemory = "Память: $memory ГБ"
    val formattedPower = "Питание: $power Вт"
}

object AdUiModel : ItemUiModel