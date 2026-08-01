# Walkthrough: Fase 0 — Bootstrap Proyek SIGAP GIZI

Seluruh infrastruktur dasar untuk proyek **SIGAP GIZI** telah berhasil diinisialisasi sesuai dengan mandat [GEMINI.MD](file:///F:/SIGAP%20GIZI%20—%20SPPG%20Bandung%20Padasuka/GEMINI.MD).

## Perubahan yang Dilakukan

### 1. Tata Kelola Repositori (Root)
- Menambahkan berkas dokumentasi: `README.md`, `CHANGELOG.md`, `SECURITY.md`, dan `LICENSE`.
- Mengonfigurasi `.gitignore` untuk melindungi berkas sensitif (secret, signing keys, local properties).
- Menetapkan aturan pemformatan berkas melalui `.editorconfig`.

### 2. Infrastruktur Android (`android/`)
- Menginisialisasi proyek Android di sub-direktori `android/`.
- Mengonfigurasi Gradle Kotlin DSL (`build.gradle.kts`, `settings.gradle.kts`) dan Version Catalog (`libs.versions.toml`).
- Menetapkan optimasi build dan standar Kotlin di `gradle.properties`.
- **Berhasil men-generate Gradle Wrapper** (`gradlew`, `gradlew.bat`, `gradle-wrapper.jar`) menggunakan Java JBR dari Android Studio di Drive F:.

### 3. Modul Aplikasi (`android/app/`)
- Membuat entry point utama: `SigapGiziApplication` dan `MainActivity` (Compose).
- Menetapkan kebijakan keamanan:
    - **HTTPS Only**: Melalui `network_security_config.xml`.
    - **No Backup**: Menonaktifkan pencadangan otomatis melalui `backup_rules.xml` dan `data_extraction_rules.xml`.
- Konfigurasi Build Release: Mengaktifkan minifikasi dan ProGuard.

### 4. Supabase & Backend Bootstrap
- Menyiapkan struktur direktori `supabase/` untuk migrasi dan pengujian.
- Menambahkan `config.toml` dasar untuk pengembangan lokal.

### 5. Penjaminan Kualitas (Quality Gates)
- Menambahkan konfigurasi **Detekt** untuk analisis statis.
- Membuat skrip `check-source-line-limit.sh` dan `check-source-line-limit.ps1` untuk verifikasi batas 200 baris.

## Status Akhir
- [x] Struktur direktori sesuai spesifikasi.
- [x] Gradle Wrapper lengkap dan executable.
- [x] Keamanan dasar diaktifkan.

> [!WARNING]
> **Kendala Path Non-ASCII**: Nama folder `SIGAP GIZI — SPPG Bandung Padasuka` mengandung karakter em dash (`—`) yang menyebabkan Android Gradle Plugin gagal melakukan sinkronisasi di Windows.
> **Solusi**: Mohon ubah nama folder proyek menjadi tanpa karakter khusus, misalnya: `SIGAP-GIZI-SPPG-BANDUNG`.
