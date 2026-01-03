package com.portfolio.app.data

import android.content.ContentValues

fun getPendidikanSampleData(): List<ContentValues> {
    val list = mutableListOf<ContentValues>()

    val pendidikan1 = ContentValues().apply {
        put(DatabaseHelper.COLUMN_INSTITUSI, "Universitas Muhammadiyah Palangka Raya")
        put(DatabaseHelper.COLUMN_JURUSAN, "Sistem Informasi")
        put(DatabaseHelper.COLUMN_TAHUN_MULAI, "2023")
        put(DatabaseHelper.COLUMN_TAHUN_SELESAI, "Sekarang")
        put(DatabaseHelper.COLUMN_DESKRIPSI, "Fakultas Bisnis Dan Informatika")
    }
    list.add(pendidikan1)

    val pendidikan2 = ContentValues().apply {
        put(DatabaseHelper.COLUMN_INSTITUSI, "SMAN- 5 Palangka Raya")
        put(DatabaseHelper.COLUMN_JURUSAN, "Bahasa Indonesia")
        put(DatabaseHelper.COLUMN_TAHUN_MULAI, "2020")
        put(DatabaseHelper.COLUMN_TAHUN_SELESAI, "2023")
        put(DatabaseHelper.COLUMN_DESKRIPSI, "Tahun Kelulusan: 2023")
    }
    list.add(pendidikan2)

    return list
}
