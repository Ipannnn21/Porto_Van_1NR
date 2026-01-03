package com.portfolio.app

import android.app.Application
import com.portfolio.app.data.DatabaseHelper
import com.portfolio.app.BuildConfig

/**
 * Kelas Application kustom untuk inisialisasi global.
 */
class PortfolioApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        // HANYA UNTUK DEVELOPMENT
        // Kode ini akan menghapus database setiap kali aplikasi dimulai dalam mode DEBUG.
        // Ini memastikan data sampel selalu yang terbaru saat Anda melakukan perubahan.
        // Blok ini tidak akan pernah berjalan di versi rilis untuk melindungi data pengguna.
        if (BuildConfig.DEBUG) {
            this.deleteDatabase(DatabaseHelper.DATABASE_NAME)
        }
    }
}
