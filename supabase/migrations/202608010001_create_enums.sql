-- 202608010001_create_enums.sql
-- Inisialisasi PostgreSQL Custom Enums untuk SIGAP GIZI

create type public.team_code as enum (
    'TEAM_DISTRIBUSI_01',
    'TEAM_DISTRIBUSI_02'
);

create type public.user_role as enum (
    'TEAM_DISTRIBUSI'
);

create type public.beneficiary_category as enum (
    'PESERTA_DIDIK',
    'B3'
);
