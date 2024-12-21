package com.example.a18_december.ui.viewmodel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.a18_december.UmyApp

object PenyediaViewModel {
    val Factory = viewModelFactory {
        initializer {
            DosenViewModel(
                UmyApp().containerApp.repositoryDsn
            )
        }

        initializer {
            HomeDsnViewModel(
                umyApp().containerApp.repositoryDsn
            )
        }

    }
}

fun CreationExtras.umyApp(): UmyApp =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as UmyApp)