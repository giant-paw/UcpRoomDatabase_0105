package com.example.a18_december.ui.view.dosen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.a18_december.ui.customwidget.CustomTopAppBar
import com.example.a18_december.ui.navigation.AlamatNavigasi
import com.example.a18_december.ui.viewmodel.DosenEvent
import com.example.a18_december.ui.viewmodel.DosenViewModel
import com.example.a18_december.ui.viewmodel.DsnUIState
import com.example.a18_december.ui.viewmodel.FormErrorState
import com.example.a18_december.ui.viewmodel.PenyediaViewModel
import kotlinx.coroutines.launch

object DestinasiInsertDosen : AlamatNavigasi {
    override val route: String = "insert_dsn"
}
//
//@SuppressLint("RememberReturnType", "UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun InsertDsnView(
    onBack: () -> Unit,
    onNavigate: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DosenViewModel = viewModel(factory = PenyediaViewModel.Factory)  // Inisialisasi ViewModel
) {
    val uiState = viewModel.uiState         // Ambil UI State dari ViewModel
    val snackbarHostState = remember { SnackbarHostState() } // Snackbar state
    val coroutineScope = rememberCoroutineScope()

    // Observasi Perubahan SnackBar effect
    LaunchedEffect(uiState.snackBarMessage) {
        uiState.snackBarMessage?.let { message ->
            coroutineScope.launch {
                snackbarHostState.showSnackbar(message)     // Tampilkan Snackbar
                viewModel.resetSnackBarMessage()
            }
        }
    }

    Scaffold (
        modifier = modifier,
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) } //Tempelkan snackbar di Scaffold
    ) { padding ->
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ){
            CustomTopAppBar(
                onBack = onBack,
                showBackButton = true,
                judul = "Tambah Dosen"
            )

            // ISI BODY
            InsertBodyDsn(
                uiState = uiState,
                onValueChange = { updatedEvent ->
                    viewModel.updateState(updatedEvent)  // Update State di ViewModel
                },
                onClick = {
                    coroutineScope.launch {
                        viewModel.saveData()    // Simpan Data
                    }
                    onNavigate()
                }
            )
        }
    }
}

@Composable
fun InsertBodyDsn(
    modifier: Modifier = Modifier,
    onValueChange: (DosenEvent) -> Unit,
    uiState: DsnUIState,
    onClick: () -> Unit
) {
    Column (
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        FormDosen(
            dosenEvent = uiState.dosenEvent,
            onValueChange = onValueChange,
            errorState = uiState.isEntryValid,
            modifier = modifier.fillMaxWidth()
        )
        Button(
            onClick = onClick,
            modifier = modifier.fillMaxWidth(),
        ) {
            Text("Save")
        }
    }
}

@Composable
fun FormDosen(
    dosenEvent: DosenEvent = DosenEvent(),
    onValueChange: (DosenEvent) -> Unit,
    errorState: FormErrorState = FormErrorState(),
    modifier: Modifier = Modifier
) {
    val jenisKelamin = listOf("Laki-Laki", "Perempuan")

    Column (
        modifier = modifier.fillMaxWidth()
    ) {

        // NAMA

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = dosenEvent.nama,
            onValueChange = {
                onValueChange(dosenEvent.copy(nama = it))
            },
            label = { Text("nama") },
            isError = errorState.nama != null,
            placeholder = { Text("Masukkan Nama Dosen") }
        )
        Text(
            text = errorState.nama ?: "",
            color = Color.Red
        )

        // NIDN

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = dosenEvent.nidn,
            onValueChange = {
                onValueChange(dosenEvent.copy(nidn = it))
            },
            label = { Text("NIDN") },
            isError = errorState.nidn != null,
            placeholder = { Text("Masukkan 10 digit NIDN") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Text(
            text = errorState.nidn ?: "",
            color = Color.Red
        )

        Spacer(modifier = Modifier.height(16.dp))

        // KELAS

        Text(text = "Jenis Kelamin")
        Row (
            modifier = Modifier.fillMaxWidth()
        ) {
            jenisKelamin.forEach{ jk ->
                Row (
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    RadioButton(
                        selected = dosenEvent.jenisKelamin == jk,
                        onClick = {
                            onValueChange(dosenEvent.copy(jenisKelamin = jk))
                        },
                    )

                    Text(
                        text = jk
                    )
                }
            }
        }
        Text(
            text = errorState.jenisKelamin ?: "",
            color = Color.Red
        )
    }
}