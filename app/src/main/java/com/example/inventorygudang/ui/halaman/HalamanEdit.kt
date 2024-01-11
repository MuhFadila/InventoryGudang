package com.example.inventorygudang.ui.halaman

import com.example.inventorygudang.R
import com.example.inventorygudang.navigasi.DestinasiNavigasi

object ItemEditDestination : DestinasiNavigasi {
    override val route = "item_edit"
    override val titleRes = R.string.edit_barang
    const val itemIdArg = "itemId"
    val routeWithArgs = "$route/{$itemIdArg}"
}