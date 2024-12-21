package com.example.a18_december.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.a18_december.data.entity.Dosen
import androidx.lifecycle.viewModelScope

var uiState by mutableStateOf(DsnUIState())



private fun validateFields(): Boolean {
    val event = uiState.dosenEvent
    val errorState = FormErrorState(
        nidn = if (event.nidn.isNotEmpty()) null else "NIDN tidak boleh kosong",
        nama = if (event.nidn.isNotEmpty()) null else "Nama tidak boleh kosong",
        jenisKelamin = if (event.nidn.isNotEmpty()) null else "Jenis Kelamin tidak boleh kosong",
    )
    uiState = uiState.copy(isEntryValid = errorState)
    return errorState.isValid()
}

fun saveData() {
    val currentEvent = uiState.dosenEvent
    if (validateFields()) {
        viewModelScope.launch {
            try {

            }
        }
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