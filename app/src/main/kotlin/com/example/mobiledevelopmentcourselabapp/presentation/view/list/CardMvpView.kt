package com.example.mobiledevelopmentcourselabapp.presentation.view.list


import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEnd
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.OneExecution


@AddToEndSingle
interface CardMvpView: MvpView {
    fun setHiddenGroupVisibility(isVisible: Boolean)
    fun setCommentChevronIcon(rotation: Float)
    fun setSendButtonEnabled(isEnabled: Boolean)
    fun setMessageError(error: String)
    @AddToEnd
    fun addComment(comment: String)
    @OneExecution
    fun showSnackbar()
    fun setCommentText(text: String)
}