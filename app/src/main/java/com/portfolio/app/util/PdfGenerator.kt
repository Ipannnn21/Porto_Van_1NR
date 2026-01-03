package com.portfolio.app.util

import android.content.ContentValues
import android.content.Context
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import com.itextpdf.io.font.constants.StandardFonts
import com.itextpdf.kernel.font.PdfFontFactory
import com.itextpdf.kernel.pdf.PdfDocument
import com.itextpdf.kernel.pdf.PdfWriter
import com.itextpdf.layout.Document
import com.itextpdf.layout.borders.Border
import com.itextpdf.layout.element.Cell
import com.itextpdf.layout.element.LineSeparator
import com.itextpdf.layout.element.List
import com.itextpdf.layout.element.ListItem
import com.itextpdf.layout.element.Paragraph
import com.itextpdf.layout.element.Table
import com.itextpdf.layout.properties.TextAlignment
import com.itextpdf.layout.properties.UnitValue
import com.portfolio.app.data.DatabaseHelper
import java.io.OutputStream

class PdfGenerator(private val context: Context) {

    private val dbHelper = DatabaseHelper(context)
    private val appFolderName = "PortfolioApp"

    fun generateAndSaveAtsCv() {
        val fileName = "CV_Irfan_Harits_ATS.pdf"
        val outputStream = getOutputStream(fileName)
            ?: throw Exception("Gagal mendapatkan output stream untuk menyimpan PDF.")

        val pdfWriter = PdfWriter(outputStream)
        val pdfDocument = PdfDocument(pdfWriter)
        
        // PERBAIKAN: Inisialisasi dan konfigurasi document dipisah
        val document = Document(pdfDocument)
        document.setMargins(36f, 36f, 36f, 36f)

        val font = PdfFontFactory.createFont(StandardFonts.HELVETICA)
        val boldFont = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD)

        val profile = dbHelper.getProfile() ?: throw Exception("Profil tidak ditemukan")
        val pendidikanList = dbHelper.getPendidikan()
        val pengalamanList = dbHelper.getPengalaman()
        val skillsList = dbHelper.getSkills()

        // 1. Header
        document.add(Paragraph(profile.nama).setFont(boldFont).setFontSize(22f).setMarginBottom(2f).setTextAlignment(TextAlignment.CENTER))
        document.add(Paragraph("${profile.email} | ${profile.phone} | ${profile.address}").setFont(font).setFontSize(10f).setTextAlignment(TextAlignment.CENTER))
        if (!profile.github.isNullOrBlank()) {
            document.add(Paragraph("GitHub: ${profile.github}").setFont(font).setFontSize(10f).setTextAlignment(TextAlignment.CENTER))
        }

        // 2. Deskripsi Singkat
        addSectionHeaderWithLine(document, "Deskripsi Singkat", boldFont)
        if (!profile.deskripsiSingkat.isNullOrBlank()) {
            document.add(Paragraph(profile.deskripsiSingkat).setFont(font).setFontSize(10f).setMarginBottom(8f))
        }

        // 3. Biodata
        addSectionHeaderWithLine(document, "Biodata", boldFont)
        val biodataTable = Table(UnitValue.createPercentArray(floatArrayOf(0.4f, 0.6f))).useAllAvailableWidth()
        addBiodataRowToTable(biodataTable, "Tempat, Tgl Lahir", profile.tempatTanggalLahir, font)
        addBiodataRowToTable(biodataTable, "Status", profile.statusPernikahan, font)
        addBiodataRowToTable(biodataTable, "Kewarganegaraan", profile.kewarganegaraan, font)
        addBiodataRowToTable(biodataTable, "Agama", profile.agama, font)
        addBiodataRowToTable(biodataTable, "Jenis Kelamin", profile.jenisKelamin, font)
        document.add(biodataTable.setMarginBottom(8f))

        // 4. Pengalaman Kerja
        addSectionHeaderWithLine(document, "Pengalaman Kerja", boldFont)
        val pengalamanListElement = List().setSymbolIndent(12f).setListSymbol("•").setFontSize(10f).setFont(font)
        pengalamanList.forEach { exp ->
            val item = ListItem()
            item.add(Paragraph("${exp.posisi} | ${exp.perusahaan} | ${exp.tahunMulai} - ${exp.tahunSelesai}").setFont(boldFont))
            val deskripsiList = List().setSymbolIndent(24f).setListSymbol("-")
            exp.deskripsi.split("\n").forEach { descLine ->
                if (descLine.trim().isNotBlank()) {
                    val subItem = ListItem(descLine.trim().removePrefix("-").trim())
                    deskripsiList.add(subItem)
                }
            }
            item.add(deskripsiList)
            pengalamanListElement.add(item)
        }
        document.add(pengalamanListElement.setMarginBottom(8f))

        // 5. Pendidikan
        addSectionHeaderWithLine(document, "Pendidikan", boldFont)
        val pendidikanListElement = List().setSymbolIndent(12f).setListSymbol("•").setFontSize(10f).setFont(font)
        pendidikanList.forEach { edu ->
            val text = "${edu.institusi} - ${edu.jurusan} (${edu.tahunMulai} - ${edu.tahunSelesai})"
            val item = ListItem(text)
            pendidikanListElement.add(item)
        }
        document.add(pendidikanListElement.setMarginBottom(8f))
        
        // 6. Keahlian
        addSectionHeaderWithLine(document, "Keahlian", boldFont)
        val skillsByCategory = skillsList.groupBy { it.kategori }
        val skillListElement = List().setSymbolIndent(12f).setListSymbol("").setFontSize(10f).setFont(font)
        skillsByCategory.forEach { (kategori, skills) ->
            val skillNames = skills.joinToString(", ") { it.nama }
            val text = "${kategori}: ${skillNames}"
            val item = ListItem(text)
            skillListElement.add(item)
        }
        document.add(skillListElement)

        document.close()
    }

    private fun addBiodataRowToTable(table: Table, label: String, value: String?, font: com.itextpdf.kernel.font.PdfFont) {
        if (!value.isNullOrBlank()) {
            val labelCell = Cell().add(Paragraph(label).setFont(font).setFontSize(10f))
            labelCell.setBorder(Border.NO_BORDER).setPadding(0f).setPaddingRight(4f)
            val valueCell = Cell().add(Paragraph(": $value").setFont(font).setFontSize(10f))
            valueCell.setBorder(Border.NO_BORDER).setPadding(0f)
            table.addCell(labelCell)
            table.addCell(valueCell)
        }
    }

    private fun addSectionHeaderWithLine(document: Document, title: String, font: com.itextpdf.kernel.font.PdfFont) {
        document.add(Paragraph(title).setFont(font).setFontSize(12f).setMarginTop(8f))
        document.add(LineSeparator(com.itextpdf.kernel.pdf.canvas.draw.SolidLine(0.5f)).setMarginBottom(4f))
    }

    private fun getOutputStream(fileName: String): OutputStream? {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val resolver = context.contentResolver
            val contentValues = ContentValues().apply {
                put(MediaStore.MediaColumns.DISPLAY_NAME, fileName)
                put(MediaStore.MediaColumns.MIME_TYPE, "application/pdf")
                put(MediaStore.MediaColumns.RELATIVE_PATH, "${Environment.DIRECTORY_DOWNLOADS}/$appFolderName")
            }
            val uri = resolver.insert(MediaStore.Downloads.EXTERNAL_CONTENT_URI, contentValues)
            return uri?.let { resolver.openOutputStream(it) }
        } else {
            @Suppress("DEPRECATION")
            val downloadsDir = Environment.getExternalStoragePublicDirectory("${Environment.DIRECTORY_DOWNLOADS}/$appFolderName")
            if (!downloadsDir.exists()) {
                downloadsDir.mkdirs()
            }
            val file = java.io.File(downloadsDir, fileName)
            return java.io.FileOutputStream(file)
        }
    }
}
