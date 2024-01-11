package com.example.inventorygudang.repositori

import android.content.Context
import com.example.inventorygudang.data.DatabaseBarang

interface ContainerApp {
    val repositoriBarang : RepositoriBarang
}

class ContainerDataApp(private val context: Context): ContainerApp{
    override val repositoriBarang: RepositoriBarang by lazy {
        OfflineRepositoriBarang(DatabaseBarang.getDatabase(context).barangDao())
    }
}