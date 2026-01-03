package com.portfolio.app.data

import android.content.ContentValues

fun getPengalamanSampleData(): List<ContentValues> {
    val list = mutableListOf<ContentValues>()

    val pengalaman1 = ContentValues().apply {
        put(DatabaseHelper.COLUMN_PERUSAHAAN, "Dukcapil Kota Palangka Raya")
        put(DatabaseHelper.COLUMN_POSISI, "Magang - Bidang Dapduk")
        put(DatabaseHelper.COLUMN_TAHUN_MULAI, "Juli 2025")
        put(DatabaseHelper.COLUMN_TAHUN_SELESAI, "Agustus 2025")
        put(DatabaseHelper.COLUMN_DESKRIPSI, "- Membantu masyarakat mengurus KTP, KK, KIA, AKTE KELAHIRAN, AKTE KEMATIAN, dan dokumen lainnya.\n- Memberikan layanan cepat,mudah,dan transparan kepada masyarakat.\n- Melakukan pencatatan kelahiran,kematian,pindah datang dan perubahan status.")
    }
    list.add(pengalaman1)

    return list
}
