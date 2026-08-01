-- 202608010002_create_profiles.sql
-- Membuat tabel public.profiles untuk identitas petugas distribusi

create table public.profiles (
    id uuid primary key references auth.users(id) on delete cascade,
    username text not null unique,
    display_name text not null,
    phone_number text,
    bio text,
    avatar_path text,
    team_code public.team_code not null,
    role public.user_role not null default 'TEAM_DISTRIBUSI',
    is_active boolean not null default true,
    updated_at timestamptz not null default now()
);

comment on table public.profiles is 'Profil petugas distribusi SIGAP GIZI';
comment on column public.profiles.id is 'ID user mereferensikan auth.users';
comment on column public.profiles.username is 'Username unik petugas';
comment on column public.profiles.team_code is 'Kode tim distribusi (01/02)';
