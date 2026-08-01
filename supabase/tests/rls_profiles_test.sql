-- rls_profiles_test.sql
-- Pengujian RLS profiles & Trigger Immutability dalam transaksi terisolasi pgTAP

begin;

select public.create_test_users();

select plan(5);

-- Test 1: User Anonim tidak membaca profile
set local role anon;
select is(
    (select count(*)::integer from public.profiles),
    0,
    'User anonim tidak dapat membaca data profiles'
);

-- Test 2: Wawan hanya membaca profile Wawan
set local role authenticated;
set local "request.jwt.claims" = '{"sub": "11111111-1111-1111-1111-111111111111", "role": "authenticated"}';
select is(
    (select username from public.profiles),
    'WAWAN',
    'Wawan hanya dapat membaca profil milik sendiri'
);

-- Test 3: Wawan tidak melihat profil Yudi
select is(
    (select count(*)::integer from public.profiles),
    1,
    'Wawan hanya mendapatkan 1 baris profil'
);

-- Test 4: Yudi hanya membaca profile Yudi
set local role authenticated;
set local "request.jwt.claims" = '{"sub": "22222222-2222-2222-2222-222222222222", "role": "authenticated"}';
select is(
    (select username from public.profiles),
    'YUDI',
    'Yudi hanya dapat membaca profil milik sendiri'
);

-- Test 5: Trigger Immutability menolak perubahan team_code
set local role postgres; -- Test trigger logic via superuser
select throws_ok(
    $$ update public.profiles set team_code = 'TEAM_DISTRIBUSI_02' where id = '11111111-1111-1111-1111-111111111111' $$,
    'P0001',
    'PROFILE_TEAM_IMMUTABLE',
    'Trigger menolak perubahan team_code pada profil'
);

select * from finish();

rollback;
