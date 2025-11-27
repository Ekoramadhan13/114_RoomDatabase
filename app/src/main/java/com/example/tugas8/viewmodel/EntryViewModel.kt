package com.example.tugas8.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.tugas8.repositori.RepositoriSiswa
import com.example.tugas8.room.Siswa

class EntryViewModel(private val repository: RepositoriSiswa) : ViewModel() {

    // Menyimpan state input UI
    var uiStateSiswa by mutableStateOf(UIStateSiswa())
        private set

    // Validasi input (jangan sampai ada yang kosong)
    private fun validasiInput(): Boolean {
        return with(uiStateSiswa.detailSiswa) {
            nama.isNotBlank() && alamat.isNotBlank() && telpon.isNotBlank()
        }
    }

    // Update state saat user mengetik di form
    fun updateUIState(detail: DetailSiswa) {
        uiStateSiswa = UIStateSiswa(
            detailSiswa = detail,
            isEntryValid = validasiInput()
        )
    }

    // Simpan data ke database Room
    suspend fun insertSiswa() {
        if (validasiInput()) {
            repository.insertSiswa(uiStateSiswa.detailSiswa.toSiswa())
        }
    }
}

// -------- STATE UI --------

data class UIStateSiswa(
    val detailSiswa: DetailSiswa = DetailSiswa(),
    val isEntryValid: Boolean = false
)

data class DetailSiswa(
    val id: Int = 0,
    val nama: String = "",
    val alamat: String = "",
    val telpon: String = ""
)

// Konversi dari input UI ke entity table di Room
fun DetailSiswa.toSiswa(): Siswa = Siswa(
    id = id,
    nama = nama,
    alamat = alamat,
    telpon = telpon
)
