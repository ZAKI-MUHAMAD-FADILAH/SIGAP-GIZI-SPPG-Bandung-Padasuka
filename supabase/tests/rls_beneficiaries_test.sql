-- rls_beneficiaries_test.sql
-- Pengujian RLS beneficiaries dalam transaksi terisolasi pgTAP

begin;

-- Seed test users
select public.create_test_users();

select plan(7);

-- Test 1: User Anonim tidak melihat data
set local role anon;
select is(
    (select count(*)::integer from public.beneficiaries),
    0,
    'User anonim tidak dapat membaca data beneficiaries'
);

-- Test 2: Wawan (Team 01) hanya melihat 13 lokasi
set local role authenticated;
set local "request.jwt.claims" = '{"sub": "11111111-1111-1111-1111-111111111111", "role": "authenticated"}';
select is(
    (select count(*)::integer from public.beneficiaries),
    13,
    'Wawan hanya membaca 13 lokasi Team 01'
);

-- Test 3: Wawan melihat total 1.402 porsi
select is(
    (select sum(total_portions)::integer from public.beneficiaries),
    1402,
    'Wawan membaca total 1.402 porsi Team 01'
);

-- Test 4: Yudi (Team 02) hanya melihat 14 lokasi
set local role authenticated;
set local "request.jwt.claims" = '{"sub": "22222222-2222-2222-2222-222222222222", "role": "authenticated"}';
select is(
    (select count(*)::integer from public.beneficiaries),
    14,
    'Yudi hanya membaca 14 lokasi Team 02'
);

-- Test 5: Yudi melihat total 1.226 porsi
select is(
    (select sum(total_portions)::integer from public.beneficiaries),
    1226,
    'Yudi membaca total 1.226 porsi Team 02'
);

-- Test 6: Inactive user tidak membaca data
set local role authenticated;
set local "request.jwt.claims" = '{"sub": "33333333-3333-3333-3333-333333333333", "role": "authenticated"}';
select is(
    (select count(*)::integer from public.beneficiaries),
    0,
    'User inaktif tidak dapat membaca data beneficiaries'
);

-- Test 7: Mutasi INSERT dari client ditolak
set local role authenticated;
set local "request.jwt.claims" = '{"sub": "11111111-1111-1111-1111-111111111111", "role": "authenticated"}';
select throws_ok(
    $$ insert into public.beneficiaries (code, name, category, team_code, total_portions) values ('PD999', 'Tes Invalid', 'PESERTA_DIDIK', 'TEAM_DISTRIBUSI_01', 0) $$,
    '42501',
    null,
    'Client tidak diizinkan melakukan INSERT ke beneficiaries'
);

select * from finish();

rollback;
