package com.example.inventorygudang.model

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.inventorygudang.AplikasiBarang


object PenyediaViewModel {
    val Factory = viewModelFactory {



    }
}

/**
 * Fungsi ekstensi query untuk objek [Application] dan mengembalikan sebuah instance dari
 *  [AplikasiBarang
 *  ].
 */
fun CreationExtras.aplikasiBarang
            (): AplikasiBarang
        =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as AplikasiBarang
            )