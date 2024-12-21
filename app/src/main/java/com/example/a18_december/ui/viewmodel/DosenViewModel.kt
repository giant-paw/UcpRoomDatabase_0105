package com.example.a18_december.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.a18_december.data.entity.Dosen
import androidx.lifecycle.viewModelScope
import com.example.a18_december.repository.RepositoryDsn
import kotlinx.coroutines.launch

var uiState by mutableStateOf(DsnUIState())

class DosenViewModel (private val repositoryDsn: RepositoryDsn) : ViewModel() {

    var uiState by mutableStateOf(DsnUIState())

    // memperbarui state berdasarkan input penggguna

    fun updateState(dosenEvent: DosenEvent) {
        uiState = uiState.copy(
            dosenEvent = dosenEvent
        )
    }

    // Validasi data input pengguna
    private fun validateFields(): Boolean {
        val event = uiState.dosenEvent
        val errorState = FormErrorState(
            nidn = if (event.nidn.isNotEmpty()) null else "NIDN tidak boleh kosong",
            nama = if (event.nama.isNotEmpty()) null else "Nama tidak boleh kosong",
            jenisKelamin = if (event.jenisKelamin.isNotEmpty()) null else "Jenis Kelamin tidak boleh kosong"
        )
        uiState = uiState.copy(isEntryValid = errorState)
        return errorState.isValid()
    }

    fun saveData() {
        val currentEvent = uiState.dosenEvent
        if (validateFields()) {
            viewModelScope.launch {
                try {
                    repositoryDsn.insertDsn(currentEvent.toDosenEntity())
                    uiState = uiState.copy(
                        snackBarMessage = "Data Berhasil Dismpan",
                        dosenEvent = DosenEvent(), // Reset input form
                        isEntryValid = FormErrorState() // Reset Error State
                    )
                } catch (e: Exception) {
                    uiState = uiState.copy(
                        snackBarMessage = "Data Gagal Disimpan"
                    )
                }
            }
        } else {
            uiState = uiState.copy(
                snackBarMessage = "Input Tidak Valid. Periksa Kembali Data Anda!!"
            )
        }
    }

    // Reset Pesan SnackBar Setelah Ditampilkan
    fun resetSnackBarMessage() {
        uiState = uiState.copy(snackBarMessage = null)
    }
}

data class DsnUIState(
    val dosenEvent: DosenEvent = DosenEvent(),
    val isEntryValid: FormErrorState = FormErrorState(),
    val snackBarMessage: String? = null
)

data class FormErrorState(
    val nidn: String? = null,
    val nama: String? = null,
    val jenisKelamin: String? = null
) {
    fun isValid(): Boolean {
        return nidn == null && nama == null &&
                jenisKelamin == null
    }
}

data class DosenEvent(
    val nidn: String = "",
    val nama: String = "",
    val jenisKelamin: String = ""
)

fun DosenEvent.toDosenEntity(): Dosen = Dosen(
    nidn = nidn,
    nama = nama,
    jenisKelamin = jenisKelamin
)