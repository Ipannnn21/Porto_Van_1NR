package com.portfolio.app.data

// File ini mendefinisikan struktur data (model) untuk seluruh aplikasi.

data class Profile(
    val nama: String,
    val title: String,
    val bio: String,
    val deskripsiSingkat: String?,
    val email: String,
    val phone: String,
    val address: String,
    val addressUrl: String?,
    val github: String?,
    val tempatTanggalLahir: String?,
    val statusPernikahan: String?,
    val kewarganegaraan: String?,
    val agama: String?,
    val jenisKelamin: String?
)

data class Pendidikan(
    val institusi: String,
    val jurusan: String,
    val tahunMulai: String,
    val tahunSelesai: String,
    val deskripsi: String
)

data class Pengalaman(
    val perusahaan: String,
    val posisi: String,
    val tahunMulai: String,
    val tahunSelesai: String,
    val deskripsi: String
)

data class Skill(
    val nama: String,
    val kategori: String
)

data class Portofolio(
    val id: Int,
    val judul: String,
    val deskripsi: String,
    val teknologi: String,
    val image: String?,
    val linkGeneral: String?,
    val linkGithub: String?
)
