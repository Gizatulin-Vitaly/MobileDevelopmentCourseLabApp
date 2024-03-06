package com.example.mobiledevelopmentcourselabapp.presentation.view.list.model

import java.io.Serializable

interface ItemUiModel: Serializable


data class CardUiModel(
    val name: String,
    val photoUrl: String,
    val price: Int,
    val frequency: Int,
    val memory: Int,
    val power: Int,
    val heightCard: Int,
    val lengthCard: Int,
    val widthCard: Int,
    val additionalPower: Int,
    val fan: Int,
    var isExpanded: Boolean = false
        ): ItemUiModel{
    val formattedName = "GeForce RTX 40${name}0"
    val formattedPrice = "$price"
    val formattedFrequency = "$frequency Ггц"
    val formattedMemory = "$memory ГБ"
    val formattedPower = "$power Вт"
    val formattedheightCard = "Высота: $heightCard"
    val formattedlengthCard = "Длина: $lengthCard"
    val formattedwidthCard = "Ширина: $widthCard"
    val formattedadditionalPower = "Доп.питание: $additionalPower"
    val formattedfan = "Вентиляторы: $fan"
}

object AdUiModel : ItemUiModel