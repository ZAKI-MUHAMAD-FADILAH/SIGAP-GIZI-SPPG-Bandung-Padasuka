-- 202608010006_create_beneficiary_rls.sql
-- Memasang Row Level Security (RLS) untuk tabel public.beneficiaries

alter table public.beneficiaries enable row level security;

create policy beneficiaries_select_team
on public.beneficiaries
for select
to authenticated
using (
    is_active = true
    and team_code = public.current_team_code()
);
