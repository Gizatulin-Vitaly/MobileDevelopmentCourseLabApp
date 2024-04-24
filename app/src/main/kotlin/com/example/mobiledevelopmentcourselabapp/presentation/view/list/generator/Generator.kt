package com.example.mobiledevelopmentcourselabapp.presentation.view.list.generator

import com.example.mobiledevelopmentcourselabapp.presentation.view.list.model.AdUiModel
import com.example.mobiledevelopmentcourselabapp.presentation.view.list.model.CardUiModel
import com.example.mobiledevelopmentcourselabapp.presentation.view.list.model.ItemUiModel
import com.github.javafaker.Faker
import kotlin.random.Random

object Generator {
    private const val ITEMS_COUNT = 100
    private const val PHOTO_LINK = "https://c.dns-shop.ru/thumb/st4/fit/200/200/"
    private val photos = arrayOf(
        "56bc7bf1c1777948301f84a0da8945ba/4b787d96cb70156e03ade2c8b3d55842bd00900da2686c16f3157642798516e3.jpg",
        "7cbc293cd2d4007bfafb49972cba7774/44e0044f1dccf7d7ed7d5c8233a2d2f768f126826a62e757559777ea1f0edb39.jpg",
        "e541e7c08eeb71577f3c845d34e53f42/331690632885fd3d34e54ae647b9b17435d20dc1e3b116bc7ebcdf36022be222.png",
        "20fbd3de4a37dfdd1df7c1b903397b23/98583f3bad8bf3307e255fe094084a5fe73f5c51a0046d358d87c1bacc79b3c9.jpg",
        "2b1e9138d29a6a1309c508ee859c7e73/3e7eb11ad032fdeb50d5b6fab5ad396cf26b3f28e74ad967996ca990f2df52f7.jpg",
        "e3082a89e0724a36666dbda6f5c1deed/220d9a1006be6359dcd3a172779887121e78301b5f25cf48fdf02c932b9368f8.png",
        "1a696eaad7a53b17f54c873f4737aeef/2490c632f34294911e213d33671883c0c77dbeb3cae00746c4ad883857304333.png",
        "b313fd30b6c1e5f3680eb137e0d20d9d/d647bc3e9cda8b9133a9e48ea2cb835730c701270170801fd0e66164c8e0484b.png",
        "08455bfbbcd8f73d811c226ac5dd301a/30e5c13feb9b170f2bb6c1190cab81bd098f0604cd9a7a89e4b81d0d1d3bac37.png",
        "daf6eca5c22eeb82db0698e682d1910d/009f141fe2e802053564f67461c6315b752f23aa6de1dd0b5d1cae705b7e9ac8.png",
        )

    fun generate(): List<ItemUiModel> {
        val faker = Faker()
        return mutableListOf<ItemUiModel>().apply {
            repeat(ITEMS_COUNT) {
                add(
                    CardUiModel(
                        name = (3..9).random().toString(),
                        frequency = (1900..2500).random(),
                        price = (30000..300000).random(),
                        memory = (4..24).random(),
                        power = (500..700).random(),
                        photoUrl = PHOTO_LINK + photos.random(),
                        heightCard = (120..150).random(),
                        lengthCard = (200..350).random(),
                        widthCard = (30..60).random(),
                        additionalPower = (6..12).random(),
                        fan = (1..4).random()
                    )
                )
            }
        }
    }
}

