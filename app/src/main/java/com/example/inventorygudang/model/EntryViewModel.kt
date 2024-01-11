package com.example.inventorygudang.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.inventorygudang.data.Barang
import com.example.inventorygudang.repositori.RepositoriBarang


class EntryViewModel(private val repositoriBarang: RepositoriBarang): ViewModel() {


    var uiStateBarang by mutableStateOf(UIStateBarang())
        private set


    /* Fungsi untuk memvalidasi input */
    private fun validasiInput(uiState: DetailBarang = uiStateBarang.detailBarang ): Boolean {
        return with(uiState) {
            nama.isNotBlank() && kode.isNotBlank() && jumlah.isNotBlank() && harga.isNotBlank()
        }
    }

    fun updateUiState(detailBarang: DetailBarang) {
        uiStateBarang =
            UIStateBarang(detailBarang = detailBarang, isEntryValid = validasiInput(detailBarang))
    }

    /* Fungsi untuk menyimpan data yang di-entry */
    suspend fun saveBarang() {
        if (validasiInput()) {
            repositoriBarang.insertBarang(uiStateBarang.detailBarang.toBarang())
        }
    }

}

data class UIStateBarang(
    val detailBarang: DetailBarang = DetailBarang(),
    val isEntryValid: Boolean = false
)

data class DetailBarang(
    val id: Int = 0,
    val nama : String = "",
    val kode : String = "",
    val jumlah: String = "",
    val harga : String = "",
    val tanggal: String = "",

    )
/* Fungsi untuk mengkonversi data input ke data dalam tabel sesuai jenis datanya*/
fun DetailBarang.toBarang(): Barang = Barang(
    id = id,
    nama = nama,
    kode = kode,
    jumlah = jumlah,
    harga = harga,
    tanggal = tanggal
)

fun Barang.toUiStateBarang(isEntryValid: Boolean = false): UIStateBarang = UIStateBarang(
    detailBarang = this.toDetailBarang(),
    isEntryValid = isEntryValid
)

fun Barang.toDetailBarang(): DetailBarang = DetailBarang(
    id = id,
    nama = nama,
    kode = kode,
    jumlah = jumlah,
    harga = harga,
    tanggal = tanggal
)