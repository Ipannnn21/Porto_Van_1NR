# 📱 Porto Van_1NR.apk — Android Portfolio App

<p align="center">
  <img src="https://img.shields.io/badge/Platform-Android-green?style=flat-square" />
  <img src="https://img.shields.io/badge/Language-Kotlin-blue?style=flat-square" />
  <img src="https://img.shields.io/badge/UI-XML%20Layout-orange?style=flat-square" />
  <img src="https://img.shields.io/badge/License-Slimmy_V1-purple?style=flat-square" />
</p>

**Porto Van_1NR.apk** adalah aplikasi Android milik **Irfan Harits Hamman Akbar** yang berfungsi sebagai **profil diri dan portofolio digital**. Aplikasi ini menampilkan **CV digital**, **riwayat pendidikan**, **pengalaman**, **keahlian**, **portofolio proyek**, hingga **informasi kontak**.
Aplikasi dikembangkan menggunakan **Kotlin**, **Navigation Component**, **Fragment-based UI**, **SQLite**, serta **DataStore** untuk pengelolaan tema gelap dan terang.

---

## ✨ Fitur Utama

<table>
<thead>
<tr>
<th>Fitur</th>
<th>Status / Implementasi</th>
</tr>
</thead>
<tbody>
<tr>
<td><b>6 Fragment/Halaman</b></td>
<td>Tentang Saya, Pendidikan, Pengalaman, Skills, Portofolio, Kontak</td>
</tr>
<tr>
<td><b>Bottom Navigation</b></td>
<td>Navigasi antar halaman menggunakan <i>Navigation Component</i></td>
</tr>
<tr>
<td><b>Dark / Light Mode</b></td>
<td>Pengaturan tema disimpan permanen menggunakan <b>DataStore Preferences</b></td>
</tr>
<tr>
<td><b>Download CV (PDF)</b></td>
<td>Pembuatan CV dalam bentuk PDF menggunakan <code>iText</code></td>
</tr>
<tr>
<td><b>Galeri Portofolio</b></td>
<td>Menampilkan proyek dalam bentuk grid menggunakan RecyclerView</td>
</tr>
<tr>
<td><b>SQLite Database</b></td>
<td>Penyimpanan data lokal untuk pendidikan, pengalaman, portofolio, dan lainnya</td>
</tr>
</tbody>
</table>

---

## 🧠 Teknologi yang Digunakan

* Kotlin
* Android Navigation Component
* Fragment
* UI/UX berbasis XML
* RecyclerView (List & Grid)
* DataStore Preferences
* SQLite Database
* iText PDF Generator

---

## 📂 Struktur Proyek

```
porto_Van_1NR/
├── app/src/main/java/com/portfolio/app/
│   ├── MainActivity.kt
│   ├── data/
│   │   └── DatabaseHelper.kt         # SQLite & Data Classes
│   ├── util/
│   │   ├── ThemeManager.kt           # DataStore Theme Manager
│   │   └── PdfGenerator.kt           # Generator CV PDF
│   └── ui/
│       ├── tentang/TentangFragment.kt
│       ├── pendidikan/PendidikanFragment.kt + Adapter
│       ├── pengalaman/PengalamanFragment.kt + Adapter
│       ├── skill/SkillFragment.kt + Adapter
│       ├── portofolio/PortofolioFragment.kt + Adapter (Grid)
│       └── kontak/KontakFragment.kt
```

---

## 🚀 Cara Menggunakan Proyek

### **1. Clone via Android Studio**

```
Android Studio → Get from VCS → GitHub → Paste URL Repository → Clone
```

Langkah detail:

1. Buka Android Studio
2. Pilih **Get from Version Control**
3. Pilih **GitHub**
4. Paste URL repository
5. Klik **Clone**

---

### **2. Import Manual**

1. Download folder proyek
2. Buka Android Studio
3. Pilih:

   ```
   File → Open → pilih folder proyek
   ```
4. Tunggu proses **Gradle Sync**
5. Jalankan aplikasi di emulator atau perangkat Android

---

## 📄 Lisensi

Proyek **Porto Van_1NR.apk** dilisensikan di bawah **Slimmy License v1.0**.

Lisensi ini memberikan izin luas untuk:

* Menggunakan, menyalin, memodifikasi, dan mendistribusikan aplikasi
* Membuat dan menyebarkan turunan proyek (derivative works)
* Penggunaan komersial dan sublicensing

Dengan ketentuan utama:

* **Atribusi ke Slimmy Projects wajib dipertahankan**
* Perubahan signifikan harus **didokumentasikan dengan jelas**
* Tidak diperkenankan menggunakan nama, logo, atau merek **Slimmy Projects** untuk promosi tanpa izin tertulis
* Lisensi mencakup **grant paten** dengan klausul **patent retaliation**
* Perangkat lunak disediakan **“AS IS” tanpa garansi**

Detail lengkap lisensi dapat dilihat pada file **LICENSE** di repositori ini.

```
Slimmy License v1.0
Copyright (c) 2025 Slimmy Projects
```

---
---

# Ringkasan Pembaruan & Arsitektur Saat Ini

Berikut adalah ringkasan yang lebih akurat mengenai kondisi aplikasi setelah melalui serangkaian pembaruan dan refaktor.

## ✨ Fitur & Fungsionalitas Modern

- **Navigasi Modern**: Sistem navigasi telah diubah dari `BottomNavigationView` menjadi **Navigasi Samping (Hamburger Menu)** yang muncul dari sisi kanan, dikelola oleh Android Navigation Component.
- **Halaman Detail Portofolio**: Setiap item portofolio dapat diklik untuk membuka halaman detail yang menampilkan deskripsi lengkap, teknologi yang digunakan (dalam bentuk `Chip`), dan **gambar proyek yang dimuat secara dinamis dari URL** menggunakan library **Coil**.
- **Tautan Eksternal Aman**: Tombol untuk melihat demo proyek atau kode di GitHub akan menampilkan **dialog konfirmasi** sebelum membuka browser dan akan nonaktif jika URL tidak tersedia.
- **Halaman Kontak Interaktif**: Menyediakan aksi cepat untuk:
    - Membuka aplikasi Email.
    - Membuka **WhatsApp** dengan nomor yang telah diformat secara otomatis (`08` menjadi `62`).
    - Membuka lokasi di **Peta** menggunakan URL Google Maps.
    - Membuka profil **GitHub**.
- **Fitur Unduh CV Ganda**:
    1.  **CV Desain**: Mengunduh CV yang sudah jadi dari URL eksternal menggunakan `DownloadManager`.
    2.  **CV ATS-Friendly**: Membuat (generate) CV yang dioptimalkan untuk **Applicant Tracking System (ATS)** secara *on-the-fly* menggunakan data dari aplikasi. CV ini menggunakan font standar (Helvetica) dan struktur yang mudah dipindai mesin.
- **Penyimpanan Terorganisir**: Semua CV yang diunduh atau dibuat akan disimpan ke dalam folder khusus `Downloads/PortfolioApp/` untuk menjaga kerapian.
- **Arsitektur Penyimpanan Modern**: Menggunakan `MediaStore` API untuk menyimpan file yang dibuat, sehingga **tidak memerlukan izin penyimpanan** (`WRITE_EXTERNAL_STORAGE`) pada versi Android modern (Android 10+), yang mengatasi masalah izin yang tidak muncul di pengaturan.

## 🧠 Teknologi & Library Tambahan

- **Image Loading**: **Coil** - Untuk memuat gambar dari URL secara efisien.
- **PDF Generation**: **iText7** - Untuk membuat file PDF CV ATS.

## 📂 Arsitektur Data yang Diperbarui

Struktur data telah mengalami refaktor signifikan untuk kemudahan pemeliharaan:

- **Pemisahan Model**: Semua `data class` (seperti `Profile`, `Pendidikan`, dll.) telah dipindahkan ke file `Models.kt`.
- **Pemisahan Data Sampel**: Inisialisasi data sampel telah dipisahkan ke dalam file-file Kotlin khusus (`data_profile.kt`, `data_skill.kt`, dll.). Ini membuat `DatabaseHelper` hanya bertanggung jawab sebagai inisiator database, bukan sebagai penyimpan data mentah.

```
porto_Van_1NR/
├── app/src/main/java/com/portfolio/app/
│   ├── MainActivity.kt
│   ├── PortfolioApplication.kt   # Kelas Application untuk logika startup
│   ├── data/
│   │   ├── DatabaseHelper.kt       # Inisiator & pengelola skema DB
│   │   ├── Models.kt               # Definisi semua data class
│   │   ├── data_profile.kt         # Data sampel untuk profil
│   │   ├── data_pendidikan.kt      # Data sampel untuk pendidikan
│   │   └── ... (file data lainnya)
│   └── ... (struktur lainnya)
```

## 🚀 Proses Development (Debug)

Untuk kemudahan pengembangan, aplikasi ini dilengkapi dengan mekanisme yang akan **menghapus dan membuat ulang database** setiap kali aplikasi dijalankan dalam mode *Debug*. Ini memastikan bahwa setiap perubahan pada data sampel (misalnya, mengubah URL di `data_portofolio.kt`) akan langsung terlihat pada proses *run* berikutnya tanpa perlu menaikkan versi database atau meng-install ulang aplikasi.
