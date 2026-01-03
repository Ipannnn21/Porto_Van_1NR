package com.portfolio.app.data

import android.content.ContentValues

fun getProfileSampleData(): ContentValues {
    return ContentValues().apply {
        put(DatabaseHelper.COLUMN_ID, 1)
        put(DatabaseHelper.COLUMN_NAMA, "Irfan Harits Hamman Akbar")
        put(DatabaseHelper.COLUMN_TITLE, "Mahasiswa Sistem Informasi")
        put(DatabaseHelper.COLUMN_BIO, "NIM: 23.54.0276\nFakultas Bisnis dan Informatika")

        // Deskripsi singkat untuk CV ATS
        put(DatabaseHelper.COLUMN_DESKRIPSI_SINGKAT, "Mahasiswa Sistem Informasi yang proaktif dan bermotivasi tinggi dengan pengalaman magang di lingkungan pemerintahan. Terampil dalam pendataan data penduduk dan memberikan layanan kepada masyarakat. Mencari kesempatan untuk menerapkan dan mengembangkan kemampuan dalam analisis sistem dan pengembangan perangkat lunak.")

        // --- BIODATA LENGKAP ---
        put(DatabaseHelper.COLUMN_TEMPAT_TANGGAL_LAHIR, "PALANGKA RAYA, 05 JULI 2005")
        put(DatabaseHelper.COLUMN_STATUS_PERNIKAHAN, "BELUM MENIKAH")
        put(DatabaseHelper.COLUMN_KEWARGANEGARAAN, "INDONESIA")
        put(DatabaseHelper.COLUMN_AGAMA, "ISLAM")
        put(DatabaseHelper.COLUMN_JENIS_KELAMIN, "LAKI-LAKI")

        // --- DATA KONTAK INTERAKTIF ---
        put(DatabaseHelper.COLUMN_EMAIL, "ipanbadak234@gmail.com")
        put(DatabaseHelper.COLUMN_PHONE, "0895336956998")
        put(DatabaseHelper.COLUMN_ADDRESS, "Jl.Badak IX No.06 B, PERUM KAHAYAN PERMAI")
        
        // --- URL UNTUK DATA KONTAK ---
        put(DatabaseHelper.COLUMN_ADDRESS_URL, "https://maps.app.goo.gl/XgHQHLh4QhWnvD7E9")
        put(DatabaseHelper.COLUMN_GITHUB, "https://github.com/Ipannnn21")
    }
}
