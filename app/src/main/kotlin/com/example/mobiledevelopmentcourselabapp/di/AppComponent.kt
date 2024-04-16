package com.example.mobiledevelopmentcourselabapp.di

import android.content.Context
import com.example.mobiledevelopmentcourselabapp.presentation.view.list.CardFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component
interface AppComponent {

    //Fragments
    fun inject(fragment: CardFragment)

    @Component.Factory
    //Fragments
    interface Factory {

        fun create(@BindsInstance context: Context): AppComponent
    }
}
