package com.example.inventorygudang

import android.app.Application
import com.example.inventorygudang.repositori.ContainerApp
import com.example.inventorygudang.repositori.ContainerDataApp


class AplikasiBarang : Application() {
    /**
     * AppContainer instance digunakan oleh kelas-kelas lainnya untuk mendapatkan dependensi
     */
    lateinit var container: ContainerApp

    override fun onCreate() {
        super.onCreate()
        container = ContainerDataApp(this)
    }
}