-- 202608010004_create_functions.sql
-- Mengimplementasikan fungsi hardened helper RLS & trigger timestamp

create or replace function public.current_team_code()
returns public.team_code
language sql
stable
security definer
set search_path = public
as $$
    select profile.team_code
    from public.profiles as profile
    where profile.id = auth.uid()
      and profile.is_active = true
    limit 1;
$$;

revoke all on function public.current_team_code() from public;
revoke all on function public.current_team_code() from anon;
grant execute on function public.current_team_code() to authenticated;

comment on function public.current_team_code() is 'Mendapatkan team_code aktif dari user authenticated';

create or replace function public.set_updated_at()
returns trigger
language plpgsql
security definer
set search_path = public
as $$
begin
    new.updated_at = now();
    return new;
end;
$$;

revoke all on function public.set_updated_at() from public;
revoke all on function public.set_updated_at() from anon;
