-- 202608010007_create_indexes.sql
-- Membuat indeks performa non-constraint untuk kueri beneficiaries

create index beneficiaries_team_category_code_idx
on public.beneficiaries (team_code, category, code)
where is_active = true;
