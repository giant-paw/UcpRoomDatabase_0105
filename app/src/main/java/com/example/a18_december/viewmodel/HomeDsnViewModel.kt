package com.example.a18_december.viewmodel

import androidx.lifecycle.ViewModel
import com.example.a18_december.data.entity.Dosen
import com.example.a18_december.repository.RepositoryDsn
import kotlinx.coroutines.flow.StateFlow

import kotlinx.coroutines.flow.filterNotNull

class HomeDsnViewModel(
    private val repositoryDsn: RepositoryDsn
) : ViewModel() {
//
//    val HomeUiState: StateFlow<HomeUiState> = repositoryDsn.getAllDosen()
//        .filterNotNull()
//        .map {
//            HomeUiState(
//                listDsn = it.tolist
//            )
//        }
}

data class HomeUiState(
    val listDsn: List<Dosen> = listOf(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
)