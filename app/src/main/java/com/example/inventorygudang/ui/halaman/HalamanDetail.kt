package com.example.inventorygudang.ui.halaman

import com.example.inventorygudang.R
import com.example.inventorygudang.navigasi.DestinasiNavigasi

object DetailsDestination : DestinasiNavigasi {
    override val route = "item_details"
    override val titleRes = R.string.detail_barang
    const val barangIdArg = "itemId"
    val routeWithArgs = "$route/{$barangIdArg}"
}