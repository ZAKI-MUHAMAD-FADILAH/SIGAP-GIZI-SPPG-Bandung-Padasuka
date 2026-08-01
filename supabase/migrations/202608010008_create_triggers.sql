-- 202608010008_create_triggers.sql
-- Memasang trigger immutability profil dan pengelola timestamp updated_at

create or replace function public.protect_profile_authorization_fields()
returns trigger
language plpgsql
security definer
set search_path = public
as $$
begin
    if new.id is distinct from old.id then
        raise exception 'PROFILE_ID_IMMUTABLE';
    end if;

    if new.username is distinct from old.username then
        raise exception 'PROFILE_USERNAME_IMMUTABLE';
    end if;

    if new.team_code is distinct from old.team_code then
        raise exception 'PROFILE_TEAM_IMMUTABLE';
    end if;

    if new.role is distinct from old.role then
        raise exception 'PROFILE_ROLE_IMMUTABLE';
    end if;

    if new.is_active is distinct from old.is_active then
        raise exception 'PROFILE_STATUS_IMMUTABLE';
    end if;

    return new;
end;
$$;

revoke all on function public.protect_profile_authorization_fields() from public;
revoke all on function public.protect_profile_authorization_fields() from anon;

create trigger trg_protect_profile_authorization_fields
    before update on public.profiles
    for each row
    execute function public.protect_profile_authorization_fields();

create trigger trg_set_updated_at_profiles
    before update on public.profiles
    for each row
    execute function public.set_updated_at();

create trigger trg_set_updated_at_beneficiaries
    before update on public.beneficiaries
    for each row
    execute function public.set_updated_at();
