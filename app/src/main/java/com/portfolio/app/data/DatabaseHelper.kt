package com.portfolio.app.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION = 8
        const val DATABASE_NAME = "portfolio.db"

        // Tables
        const val TABLE_PROFILE = "profile"
        const val TABLE_PENDIDIKAN = "pendidikan"
        const val TABLE_PENGALAMAN = "pengalaman"
        const val TABLE_SKILL = "skill"
        const val TABLE_PORTOFOLIO = "portofolio"

        // Common Columns
        const val COLUMN_ID = "id"
        const val COLUMN_NAMA = "nama"
        const val COLUMN_DESKRIPSI = "deskripsi"

        // Profile Table
        const val COLUMN_TITLE = "title"
        const val COLUMN_BIO = "bio"
        const val COLUMN_DESKRIPSI_SINGKAT = "deskripsi_singkat"
        const val COLUMN_EMAIL = "email"
        const val COLUMN_PHONE = "phone"
        const val COLUMN_ADDRESS = "address"
        const val COLUMN_ADDRESS_URL = "address_url"
        const val COLUMN_GITHUB = "github"
        const val COLUMN_TEMPAT_TANGGAL_LAHIR = "tempat_tanggal_lahir"
        const val COLUMN_STATUS_PERNIKAHAN = "status_pernikahan"
        const val COLUMN_KEWARGANEGARAAN = "kewarganegaraan"
        const val COLUMN_AGAMA = "agama"
        const val COLUMN_JENIS_KELAMIN = "jenis_kelamin"

        // Pendidikan & Pengalaman Tables
        const val COLUMN_INSTITUSI = "institusi"
        const val COLUMN_JURUSAN = "jurusan"
        const val COLUMN_TAHUN_MULAI = "tahun_mulai"
        const val COLUMN_TAHUN_SELESAI = "tahun_selesai"
        const val COLUMN_PERUSAHAAN = "perusahaan"
        const val COLUMN_POSISI = "posisi"

        // Skill Table
        const val COLUMN_LEVEL = "level"
        const val COLUMN_KATEGORI = "kategori"

        // Portofolio Table
        const val COLUMN_JUDUL = "judul"
        const val COLUMN_TEKNOLOGI = "teknologi"
        const val COLUMN_IMAGE = "image"
        const val COLUMN_LINK_GENERAL = "link_general"
        const val COLUMN_LINK_GITHUB = "link_github"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_PROFILE_TABLE = ("CREATE TABLE " + TABLE_PROFILE + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY," +
                COLUMN_NAMA + " TEXT," +
                COLUMN_TITLE + " TEXT," +
                COLUMN_BIO + " TEXT," +
                COLUMN_DESKRIPSI_SINGKAT + " TEXT," +
                COLUMN_EMAIL + " TEXT," +
                COLUMN_PHONE + " TEXT," +
                COLUMN_ADDRESS + " TEXT," +
                COLUMN_ADDRESS_URL + " TEXT," +
                COLUMN_GITHUB + " TEXT," +
                COLUMN_TEMPAT_TANGGAL_LAHIR + " TEXT," +
                COLUMN_STATUS_PERNIKAHAN + " TEXT," +
                COLUMN_KEWARGANEGARAAN + " TEXT," +
                COLUMN_AGAMA + " TEXT," +
                COLUMN_JENIS_KELAMIN + " TEXT" + ")")
        db.execSQL(CREATE_PROFILE_TABLE)

        val CREATE_PENDIDIKAN_TABLE = ("CREATE TABLE " + TABLE_PENDIDIKAN + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_INSTITUSI + " TEXT," + COLUMN_JURUSAN + " TEXT," + COLUMN_TAHUN_MULAI + " TEXT," + COLUMN_TAHUN_SELESAI + " TEXT," + COLUMN_DESKRIPSI + " TEXT" + ")")
        db.execSQL(CREATE_PENDIDIKAN_TABLE)

        val CREATE_PENGALAMAN_TABLE = ("CREATE TABLE " + TABLE_PENGALAMAN + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_PERUSAHAAN + " TEXT," + COLUMN_POSISI + " TEXT," + COLUMN_TAHUN_MULAI + " TEXT," + COLUMN_TAHUN_SELESAI + " TEXT," + COLUMN_DESKRIPSI + " TEXT" + ")")
        db.execSQL(CREATE_PENGALAMAN_TABLE)

        val CREATE_SKILL_TABLE = ("CREATE TABLE " + TABLE_SKILL + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_NAMA + " TEXT," + COLUMN_KATEGORI + " TEXT" + ")")
        db.execSQL(CREATE_SKILL_TABLE)

        val CREATE_PORTOFOLIO_TABLE = ("CREATE TABLE " + TABLE_PORTOFOLIO + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_JUDUL + " TEXT," + COLUMN_DESKRIPSI + " TEXT," + COLUMN_TEKNOLOGI + " TEXT," + COLUMN_IMAGE + " TEXT," + COLUMN_LINK_GENERAL + " TEXT," + COLUMN_LINK_GITHUB + " TEXT" + ")")
        db.execSQL(CREATE_PORTOFOLIO_TABLE)

        db.insert(TABLE_PROFILE, null, getProfileSampleData())
        getPendidikanSampleData().forEach { db.insert(TABLE_PENDIDIKAN, null, it) }
        getPengalamanSampleData().forEach { db.insert(TABLE_PENGALAMAN, null, it) }
        getSkillSampleData().forEach { db.insert(TABLE_SKILL, null, it) }
        getPortofolioSampleData().forEach { db.insert(TABLE_PORTOFOLIO, null, it) }
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_PROFILE")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_PENDIDIKAN")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_PENGALAMAN")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_SKILL")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_PORTOFOLIO")
        onCreate(db)
    }

    fun getProfile(): Profile? {
        val db = readableDatabase
        val cursor = db.query(TABLE_PROFILE, null, "$COLUMN_ID = ?", arrayOf("1"), null, null, null)
        return if (cursor.moveToFirst()) {
            Profile(
                nama = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAMA)),
                title = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITLE)),
                bio = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_BIO)),
                deskripsiSingkat = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DESKRIPSI_SINGKAT)),
                email = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_EMAIL)),
                phone = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PHONE)),
                address = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ADDRESS)),
                addressUrl = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ADDRESS_URL)),
                github = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_GITHUB)),
                tempatTanggalLahir = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TEMPAT_TANGGAL_LAHIR)),
                statusPernikahan = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_STATUS_PERNIKAHAN)),
                kewarganegaraan = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_KEWARGANEGARAAN)),
                agama = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_AGAMA)),
                jenisKelamin = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_JENIS_KELAMIN))
            ).also { cursor.close() }
        } else {
            cursor.close()
            null
        }
    }

    fun getPendidikan(): List<Pendidikan> {
        val list = mutableListOf<Pendidikan>()
        val db = readableDatabase
        val cursor = db.query(TABLE_PENDIDIKAN, null, null, null, null, null, "$COLUMN_TAHUN_SELESAI DESC")
        while (cursor.moveToNext()) {
            list.add(Pendidikan(
                institusi = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_INSTITUSI)),
                jurusan = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_JURUSAN)),
                tahunMulai = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TAHUN_MULAI)),
                tahunSelesai = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TAHUN_SELESAI)),
                deskripsi = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DESKRIPSI))
            ))
        }
        cursor.close()
        return list
    }

    fun getPengalaman(): List<Pengalaman> {
        val list = mutableListOf<Pengalaman>()
        val db = readableDatabase
        val cursor = db.query(TABLE_PENGALAMAN, null, null, null, null, null, "$COLUMN_TAHUN_SELESAI DESC")
        while (cursor.moveToNext()) {
            list.add(Pengalaman(
                perusahaan = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PERUSAHAAN)),
                posisi = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_POSISI)),
                tahunMulai = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TAHUN_MULAI)),
                tahunSelesai = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TAHUN_SELESAI)),
                deskripsi = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DESKRIPSI))
            ))
        }
        cursor.close()
        return list
    }

    fun getSkills(): List<Skill> {
        val list = mutableListOf<Skill>()
        val db = readableDatabase
        val cursor = db.query(TABLE_SKILL, null, null, null, null, null, "$COLUMN_KATEGORI, $COLUMN_NAMA ASC")
        while (cursor.moveToNext()) {
            list.add(Skill(
                nama = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAMA)),
                kategori = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_KATEGORI))
            ))
        }
        cursor.close()
        return list
    }

    fun getPortofolio(): List<Portofolio> {
        val list = mutableListOf<Portofolio>()
        val db = readableDatabase
        val cursor = db.query(TABLE_PORTOFOLIO, null, null, null, null, null, null)
        while (cursor.moveToNext()) {
            list.add(Portofolio(
                id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)),
                judul = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_JUDUL)),
                deskripsi = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DESKRIPSI)),
                teknologi = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TEKNOLOGI)),
                image = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_IMAGE)),
                linkGeneral = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_LINK_GENERAL)),
                linkGithub = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_LINK_GITHUB))
            ))
        }
        cursor.close()
        return list
    }

    fun getPortfolioById(id: Int): Portofolio? {
        val db = readableDatabase
        val cursor = db.query(TABLE_PORTOFOLIO, null, "$COLUMN_ID = ?", arrayOf(id.toString()), null, null, null)
        return if (cursor.moveToFirst()) {
            Portofolio(
                id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)),
                judul = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_JUDUL)),
                deskripsi = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DESKRIPSI)),
                teknologi = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TEKNOLOGI)),
                image = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_IMAGE)),
                linkGeneral = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_LINK_GENERAL)),
                linkGithub = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_LINK_GITHUB))
            ).also { cursor.close() }
        } else {
            cursor.close()
            null
        }
    }
}
