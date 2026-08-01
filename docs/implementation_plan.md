# Rencana Implementasi: Fase 1 — Identity dan Data Master (Revisi Disetujui)

Dokumen ini merinci rencana teknis untuk mengimplementasikan sistem identitas (Auth & Profile) dan manajemen data master penerima manfaat sesuai dengan koreksi presisi dan batasan roadmap **GEMINI.MD**.

## Status Eksekusi
> [!CAUTION]
> **BLOCKED**: Eksekusi Fase 1 hanya dapat dimulai setelah seluruh kriteria **Exit Gate Fase 0** terpenuhi (Build verifikasi, Quality Gate, dan Supabase local runtime).

## User Review Required

> [!IMPORTANT]
> - **Drizzle ORM Dihapus**: Sesuai keputusan review, Drizzle ORM tidak digunakan pada Fase 1 untuk menghindari redundansi skema. Kita akan fokus pada **Supabase SQL Migrations**.
> - **Scope Ketat**: Tabel fase lanjutan (`delivery_sessions`, `capture_slots`, dsb.) ditunda hingga fase terkait guna menjaga keselarasan arsitektur.
> - **Keamanan Server-side**: RLS dan Trigger menjadi pertahanan utama isolasi data antar tim.

## Proposed Changes

### 1. Database & Security (`supabase/`)
Pemisahan migrasi berdasarkan tanggung jawab (SRP) dan maksimal 200 baris per berkas.

#### [NEW] [202608010001_create_identity_types.sql](file:///F:/SIGAP_GIZI/supabase/migrations/202608010001_create_identity_types.sql)
- ENUM: `team_code`, `beneficiary_category`.

#### [NEW] [202608010002_create_profiles.sql](file:///F:/SIGAP_GIZI/supabase/migrations/202608010002_create_profiles.sql)
- Tabel `profiles` dengan relasi `auth.users`.
- Trigger `updated_at`.

#### [NEW] [202608010003_create_beneficiaries.sql](file:///F:/SIGAP_GIZI/supabase/migrations/202608010003_create_beneficiaries.sql)
- Tabel `beneficiaries` dengan `CHECK constraint` total porsi yang ketat.

#### [NEW] [202608010004_create_profile_security.sql](file:///F:/SIGAP_GIZI/supabase/migrations/202608010004_create_profile_security.sql)
- Fungsi `current_team_code()` (Security Definer).
- Trigger `protect_profile_authorization_fields` (Username, Team, Role, Is_Active menjadi immutable).
- RLS Policies untuk `profiles`.

#### [NEW] [202608010005_create_beneficiary_rls.sql](file:///F:/SIGAP_GIZI/supabase/migrations/202608010005_create_beneficiary_rls.sql)
- Kebijakan RLS: Hanya `SELECT` untuk tim yang sama. Mutasi (`INSERT/UPDATE/DELETE`) dilarang bagi akun operasional.

#### [NEW] [202608010006_create_indexes.sql](file:///F:/SIGAP_GIZI/supabase/migrations/202608010006_create_indexes.sql)
- Indeks performa untuk filter tim dan kategori.

#### [MODIFY] [seed.sql](file:///F:/SIGAP_GIZI/supabase/seed.sql)
- Injeksi 27 lokasi dengan idenpotensi `ON CONFLICT (code)`.
- Provisioning akun tes lokal (Wawan & Yudi).

### 2. Integrasi Android (`android/`)
Implementasi Clean Architecture (Domain, Data, Feature).

#### [MODIFY] [libs.versions.toml](file:///F:/SIGAP_GIZI/android/gradle/libs.versions.toml)
- Menambahkan `supabase-kt` (Auth, Postgrest), `Ktor`, dan `Navigation Compose`.

#### [NEW] Core Infrastructure
- `SupabaseClientProvider.kt`: Singleton Hilt untuk klien Supabase.
- `SigapTheme.kt` & `SigapBackground.kt`: Token desain awal.

#### [NEW] Identity Domain & Data
- `AuthRepository.kt` & `SignInUseCase.kt`.
- `SessionViewModel.kt`: Untuk *routing* awal aplikasi (Login vs Home).

#### [NEW] Data Master Domain & Data
- `BeneficiaryRepository.kt` & `GetBeneficiariesUseCase.kt`.
- `BeneficiaryRemoteDataSource.kt`: Query PostgREST tanpa filter manual di klien (mengandalkan RLS).

#### [NEW] Feature UI
- `LoginScreen.kt`: UI Login baku.
- `LocationListScreen.kt`: Menampilkan 13/14 lokasi sesuai tim pengguna.
- `LiquidGlassCard.kt`: Komponen kartu lokasi dengan estetika transparan ringan.

## Verification Plan

### Database & Security
- **RLS Audit**: Verifikasi bahwa Wawan tidak dapat melihat data Team 02 melalui unit test SQL di `supabase/tests/`.
- **Integrity Check**: Verifikasi total 2.628 porsi di seluruh 27 lokasi.

### Android
- **Session Restore**: Pastikan pengguna tidak perlu login ulang setelah aplikasi ditutup.
- **Team Isolation**: Login sebagai Yudi harus menampilkan tepat 14 lokasi.
- **Quality Gate**: Lulus Detekt, ktlint, dan unit test untuk Repository mapping.
