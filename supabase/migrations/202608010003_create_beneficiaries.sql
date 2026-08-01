-- 202608010003_create_beneficiaries.sql
-- Membuat tabel public.beneficiaries untuk master lokasi penerima gizi

create table public.beneficiaries (
    id uuid primary key default gen_random_uuid(),
    code text not null unique,
    name text not null,
    category public.beneficiary_category not null,
    team_code public.team_code not null,
    small_portions integer not null default 0 check (small_portions >= 0),
    large_portions integer not null default 0 check (large_portions >= 0),
    teacher_portions integer not null default 0 check (teacher_portions >= 0),
    pregnant_portions integer not null default 0 check (pregnant_portions >= 0),
    breastfeeding_portions integer not null default 0 check (breastfeeding_portions >= 0),
    toddler_portions integer not null default 0 check (toddler_portions >= 0),
    total_portions integer not null default 0 check (total_portions >= 0),
    is_active boolean not null default true,
    created_at timestamptz not null default now(),
    updated_at timestamptz not null default now(),
    constraint check_non_empty_code check (length(trim(code)) > 0),
    constraint check_non_empty_name check (length(trim(name)) > 0),
    constraint check_total_portions check (
        total_portions = (
            small_portions +
            large_portions +
            teacher_portions +
            pregnant_portions +
            breastfeeding_portions +
            toddler_portions
        )
    ),
    constraint check_code_category_format check (
        (category = 'PESERTA_DIDIK' and code ~ '^PD[0-9]{3}$')
        or
        (category = 'B3' and code ~ '^ANG[0-9]{3}$')
    )
);

comment on table public.beneficiaries is 'Master 27 lokasi penerima gizi (Sekolah & Posyandu)';
