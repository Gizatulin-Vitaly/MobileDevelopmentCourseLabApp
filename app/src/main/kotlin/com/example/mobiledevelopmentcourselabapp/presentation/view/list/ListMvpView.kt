package com.example.mobiledevelopmentcourselabapp.presentation.view.list

import com.example.mobiledevelopmentcourselabapp.presentation.view.list.model.CardUiModel
import com.example.mobiledevelopmentcourselabapp.presentation.view.list.model.ItemUiModel
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.OneExecution

@AddToEndSingle
interface ListMvpView: MvpView {
    fun showCards(cards: List<ItemUiModel>)
    @OneExecution
    fun navigateToCard(card: CardUiModel)
}