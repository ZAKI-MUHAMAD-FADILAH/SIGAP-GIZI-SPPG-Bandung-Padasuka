-- 202608010005_create_profile_rls.sql
-- Memasang Row Level Security (RLS) untuk tabel public.profiles

alter table public.profiles enable row level security;

create policy profiles_select_own
on public.profiles
for select
to authenticated
using (id = auth.uid());
