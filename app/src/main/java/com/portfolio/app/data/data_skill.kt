package com.portfolio.app.data

import android.content.ContentValues

fun getSkillSampleData(): List<ContentValues> {
    val list = mutableListOf<ContentValues>()

    val skills = listOf(
        // Kategori Aplikasi Perkantoran
        Pair("Microsoft Word", "Aplikasi Perkantoran"),
        Pair("Microsoft Excel", "Aplikasi Perkantoran"),
        Pair("Microsoft PowerPoint", "Aplikasi Perkantoran"),

        // Kategori Pengembangan Web
        Pair("HTML", "Pengembangan Web"),
        Pair("CSS", "Pengembangan Web"),
        Pair("JavaScript", "Pengembangan Web")
    )

    skills.forEach { (nama, kategori) ->
        val skillValues = ContentValues().apply {
            put(DatabaseHelper.COLUMN_NAMA, nama)
            put(DatabaseHelper.COLUMN_KATEGORI, kategori)
        }
        list.add(skillValues)
    }

    return list
}
