# Daftar Tugas: Proyek SIGAP GIZI

## FASE 0 — EXIT GATE (VERIFIKASI WAJIB)
- [/] **Verifikasi Build & Kualitas**
    - [x] Lengkapi Gradle Wrapper (Executable)
    - [ ] Jalankan `./gradlew clean assembleDebug`
    - [ ] Jalankan `./gradlew ktlintCheck detekt lintDebug`
    - [ ] Jalankan `./gradlew testDebugUnitTest`
    - [x] Jalankan `scripts/check-source-line-limit.ps1`
    - [ ] Generate `android/gradle/verification-metadata.xml`
    - [ ] Verifikasi Supabase local runtime (`supabase start`)
    - [ ] Uji luncur aplikasi di emulator (Compose Root)

## FASE 1 — IDENTITY DAN DATA MASTER
- [ ] **Database & Backend (Supabase Migrations)**
    - [ ] Migrasi 01: Identity Types (ENUMs)
    - [ ] Migrasi 02: Profiles Table & Triggers
    - [ ] Migrasi 03: Beneficiaries Table (CHECK constraint)
    - [ ] Migrasi 04: Profile Security (Functions & RLS)
    - [ ] Migrasi 05: Beneficiary RLS Policies
    - [ ] Migrasi 06: Indexes
    - [ ] Seed: 27 Beneficiaries & Test Accounts
- [ ] **Android Core Infrastructure**
    - [ ] Setup Dependencies (Supabase, Ktor, Hilt)
    - [ ] Implementasi `SupabaseClientProvider`
    - [ ] Baseline Design System (Colors, Theme, Background)
- [ ] **Android Identity Feature**
    - [ ] Data: `AuthRepositoryImpl` & `ProfileRemoteDataSource`
    - [ ] Domain: `ObserveSessionUseCase`, `SignInUseCase`
    - [ ] UI: `LoginScreen` & `LoginViewModel`
    - [ ] App Navigation & Session Routing
- [ ] **Android Data Master Feature**
    - [ ] Data: `BeneficiaryRepositoryImpl` & `BeneficiaryRemoteDataSource`
    - [ ] Domain: `GetBeneficiariesUseCase`
    - [ ] UI: `LocationListScreen` & `BeneficiaryCard` (Liquid Glass)
- [ ] **Security Audit & Verifikasi**
    - [ ] RLS Cross-team Isolation Test
    - [ ] Integrity Check (2.628 portions)
