package com.example.mobiledevelopmentcourselabapp.di

import android.content.Context
import com.example.mobiledevelopmentcourselabapp.presentation.view.list.ListFragment
import com.example.mobiledevelopmentcourselabapp.presentation.view.list.CardFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component
@Singleton
interface AppComponent {

    //Fragments
    fun inject(fragment: ListFragment)
    fun inject(fragment: CardFragment)


    @Component.Factory
    interface Factory {

        fun create(@BindsInstance context: Context): AppComponent
    }
}