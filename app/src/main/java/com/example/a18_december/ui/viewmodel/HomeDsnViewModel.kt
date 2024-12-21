package com.example.a18_december.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a18_december.data.entity.Dosen
import com.example.a18_december.repository.RepositoryDsn
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch

import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn

class HomeDsnViewModel(
    private val repositoryDsn: RepositoryDsn
) : ViewModel() {

    val HomeUiState: StateFlow<HomeUiState> = repositoryDsn.getAllDosen()
        .filterNotNull()
        .map {
            HomeUiState(
                listDsn = it.toList(),
                isLoading = false
            )
        }
        .onStart {
            emit(HomeUiState(isLoading = false))
            delay(900)
        }
        .catch {
            emit(
                HomeUiState(
                    isLoading = false,
                    isError = true,
                    errorMessage = it.message ?: "Terjadi Kesalahan"
                )
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = HomeUiState(
                isLoading = true
            )
        )
}

data class HomeUiState(
    val listDsn: List<Dosen> = listOf(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
)