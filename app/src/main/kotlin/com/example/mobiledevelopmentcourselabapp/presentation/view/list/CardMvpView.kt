package com.example.mobiledevelopmentcourselabapp.presentation.view.list

import androidx.annotation.DrawableRes
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface CardMvpView: MvpView {
    fun setHiddenGroupVisibility(isVisible: Boolean)
    fun setCommentChevronIcon(rotation: Float)
    fun setSendButtonEnabled(isEnabled: Boolean)
}