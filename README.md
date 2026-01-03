# Aplikasi Portofolio Android (Porto Irfan)

Aplikasi portofolio pribadi berbasis Android yang dibangun menggunakan Kotlin. Aplikasi ini dirancang untuk menampilkan profil, riwayat pendidikan, pengalaman kerja, keahlian, dan proyek-proyek yang pernah dikerjakan. Aplikasi ini menggunakan arsitektur yang bersih dengan pemisahan antara data, UI, dan logika.

## Fitur Utama

- **Navigasi Modern**: Menggunakan Navigasi Samping (Hamburger Menu) yang muncul dari kanan, dikelola oleh Android Navigation Component.
- **Tema Dinamis**: Dilengkapi dengan tombol *toggle* untuk beralih antara mode Terang (Light) dan Gelap (Dark).
- **Halaman Dinamis**: Semua konten (profil, pendidikan, pengalaman, dll.) diambil dari database SQLite lokal, membuatnya mudah untuk diperbarui.
- **Manajemen Data Terpusat**: Inisialisasi data sampel dipisahkan ke dalam file-file Kotlin (`data_profile.kt`, `data_skill.kt`, dll.) untuk kemudahan pemeliharaan.
- **Halaman Detail Portofolio**: Setiap item portofolio dapat diklik untuk membuka halaman detail yang menampilkan deskripsi lengkap, teknologi yang digunakan (dalam bentuk *chips*), dan gambar proyek yang dimuat dari URL.
- **Tautan Eksternal Aman**: Tombol untuk melihat demo proyek atau kode di GitHub akan menampilkan dialog konfirmasi sebelum membuka browser.
- **Halaman Kontak Interaktif**: Menyediakan aksi cepat untuk:
    - Membuka aplikasi Email.
    - Membuka WhatsApp dengan nomor yang telah diformat.
    - Membuka lokasi di Peta.
    - Membuka profil GitHub.
- **Fitur Unduh CV Ganda**:
    1.  **CV Desain**: Mengunduh CV yang sudah jadi dari URL menggunakan `DownloadManager`.
    2.  **CV ATS-Friendly**: Membuat (generate) CV yang dioptimalkan untuk *Applicant Tracking System* (ATS) secara *on-the-fly* menggunakan data dari aplikasi.
- **Penyimpanan Terorganisir**: Semua CV yang diunduh atau dibuat akan disimpan ke dalam folder khusus `Downloads/Porto Irfan/`.
- **Arsitektur Penyimpanan Modern**: Menggunakan `MediaStore` API untuk menyimpan file yang dibuat, sehingga tidak memerlukan izin penyimpanan yang usang pada versi Android modern.

## Teknologi yang Digunakan

- **Bahasa Pemrograman**: Kotlin
- **Arsitektur UI**: Single-Activity, Multi-Fragment
- **Navigasi**: Android Navigation Component (NavGraph, Safe Args)
- **Tampilan & Layout**: Material Design 3, XML Layouts, ConstraintLayout, RecyclerView, CardView, ChipGroup.
- **Database**: SQLite (dikelola melalui `SQLiteOpenHelper`).
- **Image Loading**: Coil (untuk memuat gambar dari URL).
- **PDF Generation**: iText7
- **Concurrency**: Kotlin Coroutines (untuk preferensi tema).

## Proses Development (Debug)

Untuk kemudahan pengembangan, aplikasi ini dilengkapi dengan mekanisme yang akan **menghapus dan membuat ulang database** setiap kali aplikasi dijalankan dalam mode *Debug*. Ini memastikan bahwa setiap perubahan pada data sampel akan langsung terlihat pada proses *run* berikutnya tanpa perlu menaikkan versi database atau meng-install ulang aplikasi.
