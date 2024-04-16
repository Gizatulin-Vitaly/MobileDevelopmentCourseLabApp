package com.example.mobiledevelopmentcourselabapp.presentation.view.list.presenter

import com.example.mobiledevelopmentcourselabapp.presentation.view.list.CardFragment.Companion.DEGREE_OF_ROTATION
import com.example.mobiledevelopmentcourselabapp.presentation.view.list.CardFragment.Companion.NOT_ROTATION
import com.example.mobiledevelopmentcourselabapp.presentation.view.list.CardMvpView
import com.example.mobiledevelopmentcourselabapp.utils.orFalse
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@InjectViewState
class CardPresenter @Inject constructor(): MvpPresenter<CardMvpView>() {

    private var isCommentOpen = false
    private var message: String? = null

    fun onCommentsTitleClicked() {
        isCommentOpen = isCommentOpen.not()
        viewState.setHiddenGroupVisibility(isCommentOpen)
        viewState.setCommentChevronIcon(
            if (isCommentOpen) {
                DEGREE_OF_ROTATION
            } else {
                NOT_ROTATION
            }
        )
    }

    fun onCommentChanged(text: CharSequence?){
        viewState.setSendButtonEnabled(text?.isNotBlank().orFalse())
    }
}