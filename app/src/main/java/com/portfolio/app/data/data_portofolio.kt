package com.portfolio.app.data

import android.content.ContentValues

/**
 * File ini berisi data sampel untuk portofolio.
 * Untuk menambah proyek baru, cukup salin salah satu blok "ContentValues().apply { ... }",
 * tempel di bawahnya, dan ubah nilainya.
 */
fun getPortofolioSampleData(): List<ContentValues> {
    return listOf(

        // --- PROYEK 1 ---
        ContentValues().apply {
            put(DatabaseHelper.COLUMN_JUDUL, "Pembuatan Aplikasi di Android Studio")
            put(DatabaseHelper.COLUMN_DESKRIPSI, "Pembuatan Aplikasi di Android Studio Berbasis (Kotlin, Fragment, UI/UX XML, SQLite Database,iText PDF Generator)")
            put(DatabaseHelper.COLUMN_TEKNOLOGI, "Kotlin, Fragment, XML, SQLite, iText PDF")
            put(DatabaseHelper.COLUMN_IMAGE, "https://raw.githubusercontent.com/Ipannnn21/database-android/refs/heads/main/proyek1/image.png") // <-- GANTI DENGAN URL GAMBAR PROYEK
            put(DatabaseHelper.COLUMN_LINK_GENERAL, "https://github.com/Ipannnn21/Porto_Van_1NR/releases") // <-- GANTI DENGAN URL DEMO LANGSUNG
            put(DatabaseHelper.COLUMN_LINK_GITHUB, "https://github.com/Ipannnn21/Porto_Van_1NR")  // <-- GANTI DENGAN URL GITHUB
        },

        // --- PROYEK 2 ---
        ContentValues().apply {
            put(DatabaseHelper.COLUMN_JUDUL, "Sistem Informasi Penjualan Pada Minimarket")
            put(DatabaseHelper.COLUMN_DESKRIPSI, "Sistem Informasi Penjualan Pada Minimarket Menggunakan Bahasa Pemrograman (Php murni,Mysql,Css)")
            put(DatabaseHelper.COLUMN_TEKNOLOGI, "PHP, MySQL, CSS")
            put(DatabaseHelper.COLUMN_IMAGE, "https://raw.githubusercontent.com/Ipannnn21/database-android/refs/heads/main/proyek1/Gambar1.jpg") // <-- GANTI DENGAN URL GAMBAR PROYEK
            put(DatabaseHelper.COLUMN_LINK_GENERAL, "https://github.com/Ipannnn21/database-android/raw/refs/heads/main/proyek1/laporan%20kel%205%20sistem%20informasi%20penjualan%20minimarket%20(6).docx") // <-- GANTI DENGAN URL DEMO LANGSUNG
            put(DatabaseHelper.COLUMN_LINK_GITHUB, "")  // <-- GANTI DENGAN URL GITHUB
        },

        // --- PROYEK 3 ---
        ContentValues().apply {
            put(DatabaseHelper.COLUMN_JUDUL, "Food Court Berbasis Web")
            put(DatabaseHelper.COLUMN_DESKRIPSI, "Food Court Berbasis Web (Html,Css,Js)")
            put(DatabaseHelper.COLUMN_TEKNOLOGI, "HTML, CSS, JS")
            put(DatabaseHelper.COLUMN_IMAGE, "https://raw.githubusercontent.com/Ipannnn21/database-android/refs/heads/main/proyek3/image.png") // <-- GANTI DENGAN URL GAMBAR PROYEK
            put(DatabaseHelper.COLUMN_LINK_GENERAL, "https://ipannnn21.github.io/foodcourt/") // <-- GANTI DENGAN URL DEMO LANGSUNG
            put(DatabaseHelper.COLUMN_LINK_GITHUB, "https://github.com/Ipannnn21/foodcourt")  // <-- GANTI DENGAN URL GITHUB
        },

        // --- PROYEK 4 ---
        ContentValues().apply {
            put(DatabaseHelper.COLUMN_JUDUL, "Calculator Berbasis Bahasa Pemrograman Java")
            put(DatabaseHelper.COLUMN_DESKRIPSI, "Calculator Berbasis Bahasa Pemrograman Java di (Visual Basic 6.0)")
            put(DatabaseHelper.COLUMN_TEKNOLOGI, "Java, Visual Basic 6.0")
            put(DatabaseHelper.COLUMN_IMAGE, "https://raw.githubusercontent.com/Ipannnn21/database-android/refs/heads/main/proyek4/image.png") // <-- GANTI DENGAN URL GAMBAR PROYEK
            put(DatabaseHelper.COLUMN_LINK_GENERAL, "https://github.com/Ipannnn21/kalkulator-vb6/releases/tag/v1") // <-- GANTI DENGAN URL DEMO LANGSUNG
            put(DatabaseHelper.COLUMN_LINK_GITHUB, "https://github.com/Ipannnn21/kalkulator-vb6")  // <-- GANTI DENGAN URL GITHUB
        }

        // --- TAMBAHKAN PROYEK BARU DI SINI ---
        // Cukup salin blok ContentValues() di atas dan sesuaikan isinya.

    )
}
