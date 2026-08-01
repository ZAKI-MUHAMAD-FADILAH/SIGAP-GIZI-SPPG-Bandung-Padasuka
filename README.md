# SIGAP GIZI — SPPG Bandung Padasuka

**Sistem Informasi Geolokasi dan Arsip Penyaluran Gizi**

SIGAP GIZI adalah aplikasi Android native yang dirancang khusus untuk mendokumentasikan proses distribusi gizi di unit SPPG Bandung Padasuka. Aplikasi ini memastikan setiap tahapan pengiriman memiliki bukti foto yang terstruktur, aman, dan langsung terarsip ke Google Drive melalui gateway Supabase.

## Visi Produk
Menyediakan solusi dokumentasi operasional yang handal, tanpa biaya bulanan (zero-cost), dan menjaga privasi data pengguna dengan tidak mencampur foto pekerjaan ke dalam galeri pribadi.

## Arsitektur Utama
- **Frontend**: Android Native (Kotlin, Jetpack Compose)
- **Backend**: Supabase (Auth, Database, Edge Functions)
- **Storage**: Google Drive (via Google Apps Script)
- **Quality Gate**: Detekt, ktlint, Android Lint, Source Line Limit (200 baris)

## Persyaratan Pengembangan
- Android Studio Ladybug atau lebih baru.
- JDK 17+.
- Supabase CLI untuk pengembangan backend lokal.
- Docker (untuk Supabase lokal).

## Lisensi
Copyright © 2026 SPPG Bandung Padasuka. All rights reserved.
Penggunaan internal terbatas.
