# Kebijakan Keamanan — SIGAP GIZI

## Komitmen Keamanan
SIGAP GIZI memprioritaskan keamanan data operasional dan privasi pengguna. Arsitektur sistem dirancang dengan prinsip *Security by Default*.

## Larangan Keras
Dilarang keras melakukan tindakan berikut dalam repositori ini:
- Menyimpan kunci rahasia (Secret Key), token, atau kredensial dalam kode sumber.
- Menyimpan berkas signing (keystore) dalam repositori.
- Menonaktifkan Row Level Security (RLS) di Supabase.
- Mengaktifkan `usesCleartextTraffic` (HTTP) pada aplikasi Android.

## Pelaporan Kerentanan
Jika Anda menemukan celah keamanan, mohon laporkan segera ke tim pengembang internal. Jangan mempublikasikan kerentanan sebelum perbaikan tersedia.

## Pengamanan Data
- Foto operasional disimpan di *app-private storage* dan dihapus setelah upload berhasil.
- Metadata dikirim melalui koneksi HTTPS terenkripsi.
- Otorisasi dilakukan di sisi server (Supabase RLS & Edge Functions).
