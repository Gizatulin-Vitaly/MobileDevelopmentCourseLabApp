package com.example.mobiledevelopmentcourselabapp.presentation.view.list.presenter

import com.example.mobiledevelopmentcourselabapp.presentation.view.list.CardMvpView
import com.example.mobiledevelopmentcourselabapp.presentation.view.list.ListMvpView
import com.example.mobiledevelopmentcourselabapp.presentation.view.list.generator.Generator
import com.example.mobiledevelopmentcourselabapp.presentation.view.list.model.CardUiModel
import moxy.MvpPresenter
import javax.inject.Inject

class ListPresenter @Inject constructor(): MvpPresenter<ListMvpView>(){

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        val cards = Generator.generate()
        viewState.showCards(cards)
    }

    fun onCardClicked(cardUiModel: CardUiModel) {
        viewState.navigateToCard(cardUiModel)
    }
}