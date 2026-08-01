-- seed.sql: Data 27 Lokasi Penerima Gizi SPPG Bandung Padasuka
-- Team 01 (13 lokasi / 1.402 porsi) & Team 02 (14 lokasi / 1.226 porsi). Total = 2.628 porsi.

truncate table public.beneficiaries restart identity cascade;

-- TEAM DISTRIBUSI 01 (13 Lokasi - Total 1.402 Porsi)
insert into public.beneficiaries (code, name, category, team_code, small_portions, large_portions, teacher_portions, pregnant_portions, breastfeeding_portions, toddler_portions, total_portions) values
('PD001', 'SDN Padasuka 01', 'PESERTA_DIDIK', 'TEAM_DISTRIBUSI_01', 100, 150, 10, 0, 0, 0, 260),
('PD002', 'SDN Padasuka 02', 'PESERTA_DIDIK', 'TEAM_DISTRIBUSI_01', 80, 120, 10, 0, 0, 0, 210),
('PD003', 'SDN Padasuka 03', 'PESERTA_DIDIK', 'TEAM_DISTRIBUSI_01', 60, 90, 8, 0, 0, 0, 158),
('PD004', 'SMPN Padasuka 01', 'PESERTA_DIDIK', 'TEAM_DISTRIBUSI_01', 0, 200, 15, 0, 0, 0, 215),
('PD005', 'TK Padasuka Ceria', 'PESERTA_DIDIK', 'TEAM_DISTRIBUSI_01', 50, 0, 5, 0, 0, 0, 55),
('PD006', 'RA Al-Hidayah', 'PESERTA_DIDIK', 'TEAM_DISTRIBUSI_01', 40, 0, 4, 0, 0, 0, 44),
('PD007', 'KB Nurul Iman', 'PESERTA_DIDIK', 'TEAM_DISTRIBUSI_01', 35, 0, 3, 0, 0, 0, 38),
('PD008', 'SD IT Insan Kamil', 'PESERTA_DIDIK', 'TEAM_DISTRIBUSI_01', 70, 110, 10, 0, 0, 0, 190),
('PD009', 'MIS Padasuka', 'PESERTA_DIDIK', 'TEAM_DISTRIBUSI_01', 45, 60, 5, 0, 0, 0, 110),
('ANG001', 'Posyandu Mawar 01', 'B3', 'TEAM_DISTRIBUSI_01', 0, 0, 0, 5, 10, 15, 30),
('ANG002', 'Posyandu Melati 01', 'B3', 'TEAM_DISTRIBUSI_01', 0, 0, 0, 4, 8, 18, 30),
('ANG003', 'Posyandu Anggrek 01', 'B3', 'TEAM_DISTRIBUSI_01', 0, 0, 0, 6, 12, 14, 32),
('ANG004', 'Posyandu Kenanga 01', 'B3', 'TEAM_DISTRIBUSI_01', 0, 0, 0, 5, 10, 15, 30);

-- TEAM DISTRIBUSI 02 (14 Lokasi - Total 1.226 Porsi)
insert into public.beneficiaries (code, name, category, team_code, small_portions, large_portions, teacher_portions, pregnant_portions, breastfeeding_portions, toddler_portions, total_portions) values
('PD010', 'SDN Padasuka 04', 'PESERTA_DIDIK', 'TEAM_DISTRIBUSI_02', 70, 100, 10, 0, 0, 0, 180),
('PD011', 'SDN Padasuka 05', 'PESERTA_DIDIK', 'TEAM_DISTRIBUSI_02', 60, 90, 8, 0, 0, 0, 158),
('PD012', 'SMPN Padasuka 02', 'PESERTA_DIDIK', 'TEAM_DISTRIBUSI_02', 0, 180, 12, 0, 0, 0, 192),
('PD013', 'TK Mekar Sari', 'PESERTA_DIDIK', 'TEAM_DISTRIBUSI_02', 40, 0, 4, 0, 0, 0, 44),
('PD014', 'RA Al-Falah', 'PESERTA_DIDIK', 'TEAM_DISTRIBUSI_02', 35, 0, 3, 0, 0, 0, 38),
('PD015', 'KB Tunas Bangsa', 'PESERTA_DIDIK', 'TEAM_DISTRIBUSI_02', 30, 0, 3, 0, 0, 0, 33),
('PD016', 'SD IT Al-Azhar', 'PESERTA_DIDIK', 'TEAM_DISTRIBUSI_02', 65, 95, 10, 0, 0, 0, 170),
('PD017', 'MIS Al-Barkah', 'PESERTA_DIDIK', 'TEAM_DISTRIBUSI_02', 40, 60, 6, 0, 0, 0, 106),
('PD018', 'SMAS Padasuka', 'PESERTA_DIDIK', 'TEAM_DISTRIBUSI_02', 0, 110, 8, 0, 0, 0, 118),
('PD019', 'SMKN Padasuka', 'PESERTA_DIDIK', 'TEAM_DISTRIBUSI_02', 0, 65, 6, 0, 0, 0, 71),
('ANG005', 'Posyandu Flamboyan 02', 'B3', 'TEAM_DISTRIBUSI_02', 0, 0, 0, 4, 8, 17, 29),
('ANG006', 'Posyandu Dahlia 02', 'B3', 'TEAM_DISTRIBUSI_02', 0, 0, 0, 5, 9, 15, 29),
('ANG007', 'Posyandu Teratai 02', 'B3', 'TEAM_DISTRIBUSI_02', 0, 0, 0, 6, 10, 14, 30),
('ANG008', 'Posyandu Cempaka 02', 'B3', 'TEAM_DISTRIBUSI_02', 0, 0, 0, 4, 8, 16, 28);
