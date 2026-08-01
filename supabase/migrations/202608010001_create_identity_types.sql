-- Migrasi 0001: Definisi Tipe Identitas (ENUM)
-- Tanggung Jawab: Menyediakan tipe data terbatas untuk integritas tim dan kategori.

DO $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'team_code') THEN
        CREATE TYPE public.team_code AS ENUM (
            'TEAM_DISTRIBUSI_01',
            'TEAM_DISTRIBUSI_02'
        );
    END IF;

    IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'beneficiary_category') THEN
        CREATE TYPE public.beneficiary_category AS ENUM (
            'PESERTA_DIDIK',
            'B3'
        );
    END IF;
END $$;
