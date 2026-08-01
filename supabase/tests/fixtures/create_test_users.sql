-- create_test_users.sql
-- Provisioning akun test lokal (Wawan & Yudi) untuk pengujian RLS & development

create or replace function public.create_test_users()
returns void
language plpgsql
security definer
set search_path = public
as $$
declare
    v_wawan_id uuid := '11111111-1111-1111-1111-111111111111';
    v_yudi_id uuid := '22222222-2222-2222-2222-222222222222';
    v_inactive_id uuid := '33333333-3333-3333-3333-333333333333';
begin
    -- Clean up previous test entries
    delete from public.profiles where id in (v_wawan_id, v_yudi_id, v_inactive_id);

    -- Insert Wawan Profile (Team 01)
    insert into public.profiles (id, username, display_name, phone_number, team_code, role, is_active)
    values (v_wawan_id, 'WAWAN', 'Wawan (Team 01)', '081234567890', 'TEAM_DISTRIBUSI_01', 'TEAM_DISTRIBUSI', true);

    -- Insert Yudi Profile (Team 02)
    insert into public.profiles (id, username, display_name, phone_number, team_code, role, is_active)
    values (v_yudi_id, 'YUDI', 'Yudi (Team 02)', '089876543210', 'TEAM_DISTRIBUSI_02', 'TEAM_DISTRIBUSI', true);

    -- Insert Inactive Profile
    insert into public.profiles (id, username, display_name, phone_number, team_code, role, is_active)
    values (v_inactive_id, 'INACTIVE_USER', 'User Inaktif', '080000000000', 'TEAM_DISTRIBUSI_01', 'TEAM_DISTRIBUSI', false);
end;
$$;
