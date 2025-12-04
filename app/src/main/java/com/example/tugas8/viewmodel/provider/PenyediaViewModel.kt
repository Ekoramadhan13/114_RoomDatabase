package com.example.tugas8.viewmodel.provider



import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.tugas8.repositori.AplikasiSiswa
import com.example.tugas8.viewmodel.DetailViewModel
import com.example.tugas8.viewmodel.EntryViewModel
import com.example.tugas8.viewmodel.HomeViewModel

object PenyediaViewModel {

    val Factory = viewModelFactory{
        initializer {
            HomeViewModel(aplikasiSiswa().container.repositorySiswa)
        }

        initializer {
            EntryViewModel(aplikasiSiswa().container.repositorySiswa)
        }

        initializer {
            DetailViewModel(savedStateHandle = this.createSavedStateHandle(),
                repositoriSiswa = aplikasiSiswa().container.repositorySiswa)
        }

    }
}

fun CreationExtras.aplikasiSiswa(): AplikasiSiswa =
    (this[ViewModelProvider.AndroidViewModelFactory
        .APPLICATION_KEY] as AplikasiSiswa)