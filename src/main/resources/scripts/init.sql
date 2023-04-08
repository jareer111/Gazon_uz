set search_path to gazonuz;

INSERT INTO region(id, name)
values (1, 'Andijon'),
       (2, 'Buxoro'),
       (3, 'Jizzax'),
       (4, 'Qoraqalpogiston Respublikasi'),
       (5, 'Xorazm'),
       (6, 'Navoiy'),
       (7, 'Samarqand'),
       (8, 'Qashqadaryo'),
       (9, 'Surxondaryo'),
       (10, 'Sirdaryo'),
       (11, 'Toshkent'),
       (12, 'Namangan'),
       (13, 'Fargona'),
       (14, 'Toshkent Sh');


-- 1 Andijan
insert into district(id, region_id, name)
values (1, 1, 'Andijan'),
       (2, 1, 'Asaka'),
       (3, 1, 'Baliqchi'),
       (4, 1, 'Buz'),
       (5, 1, 'Izboskan'),
       (6, 1, 'Jalaquduq'),
       (7, 1, 'Marhamat'),
       (8, 1, 'Oltinkul'),
       (9, 1, 'Paxtaobod'),
       (10, 1, 'Shahrixon'),
       (11, 1, 'Ulugnor'),
       (12, 1, 'Xonobod');

-- 2.list of districts in Bukhara region Bukhara, Vobkent, Zhondor, Kogon, Olot, Peshku, Romitan, Shofirkon, Korovulbozor, Korakul, Gizhduvon.
insert into district(id, region_id, name)
values (13, 2, 'Bukhara'),
       (14, 2, 'Vobkent'),
       (15, 2, 'Zhondor'),
       (16, 2, 'Kogon'),
       (17, 2, 'Olot'),
       (18, 2, 'Peshku'),
       (19, 2, 'Romitan'),
       (20, 2, 'Shofirkon'),
       (21, 2, 'Korovulbozor'),
       (22, 2, 'Korakul'),
       (23, 2, 'Gizhduvon');

-- list of districts Djizzak region Arnasoy ,Baxmal ,Doʻstlik ,Forish ,Gʻallaorol ,Sharof Rashidov  ,Mirzachoʻl ,Paxtakor ,Yangiobod ,Zomin ,Zafarobod ,Zarbdor.
insert into district(id, region_id, name)
values (24, 3, 'Arnasoy'),
       (25, 3, 'Baxmal'),
       (26, 3, 'Doʻstlik'),
       (27, 3, 'Forish'),
       (28, 3, 'Gʻallaorol'),
       (29, 3, 'Sharof Rashidov'),
       (30, 3, 'Mirzachoʻl'),
       (31, 3, 'Paxtakor'),
       (32, 3, 'Yangiobod'),
       (33, 3, 'Zomin'),
       (34, 3, 'Zafarobod'),
       (35, 3, 'Zarbdor');


-- List of district Karakalpakstan region Amudaryo  ,Beruniy   ,Chimboy   ,Ellikqala ,Kegeyli   ,Moʻynoq   ,Nukus     ,Qanlikoʻl ,Qoʻngʻirot,Qoraoʻzak ,Shumanay  ,Taxtakoʻpi,Toʻrtkoʻl ,Xoʻjayli  ,Taxiatosh ,Boʻzatov.
insert into district(id, region_id, name)
values (36, 4, 'Amudaryo'),
       (37, 4, 'Beruniy'),
       (38, 4, 'Chimboy'),
       (39, 4, 'Ellikqala'),
       (40, 4, 'Kegeyli'),
       (41, 4, 'Moʻynoq'),
       (42, 4, 'Nukus'),
       (43, 4, 'Qanlikoʻl'),
       (44, 4, 'Qoʻngʻirot'),
       (45, 4, 'Qoraoʻzak'),
       (46, 4, 'Shumanay'),
       (47, 4, 'Taxtakoʻpi'),
       (48, 4, 'Toʻrtkoʻl'),
       (49, 4, 'Xoʻjayli'),
       (50, 4, 'Taxiatosh'),
       (51, 4, 'Boʻzatov'),
       (52, 4, 'Nukush Sh');


-- list of district Xorazm region Bogʻot ,Gurlan  ,Xonqa  ,Hazorasp ,Khiva   ,Qoʻshkoʻpir  ,Shovot   ,Urganch ,Yangiariq,Yangibozor ,Tuproqqala  .

insert into district(id, region_id, name)
values (53, 5, 'Bogʻot'),
       (54, 5, 'Gurlan'),
       (55, 5, 'Xonqa'),
       (56, 5, 'Hazorasp'),
       (57, 5, 'Khiva'),
       (58, 5, 'Qoʻshkoʻpir'),
       (59, 5, 'Shovot'),
       (60, 5, 'Urganch'),
       (61, 5, 'Yangiariq'),
       (62, 5, 'Yangibozor'),
       (63, 5, 'Tuproqqala');


-- list of district Navoiy region Konimex      ,Qiziltepa    ,Xatirchi     ,Navbahor     ,Karmana      ,Nurota       ,Tomdi  ,Uchquduq
insert into district(id, region_id, name)
values (64, 6, 'Konimex'),
       (65, 6, 'Qiziltepa'),
       (66, 6, 'Xatirchi'),
       (67, 6, 'Navbahor'),
       (68, 6, 'Karmana'),
       (69, 6, 'Nurota'),
       (70, 6, 'Tomdi'),
       (71, 6, 'Uchquduq');


-- list of district Samarkand region Bulungʻur   ,Ishtixon        ,Jomboy          ,Kattakurgan     ,Qoʻshrabot      ,Narpay          ,Nurobod         ,Oqdaryo         ,Paxtachi         ,Payariq          ,Pastdargʻom      ,Samarqand        ,Toyloq           ,Urgut
insert into district(id, region_id, name)
values (72, 7, 'Bulungʻur'),
       (73, 7, 'Ishtixon'),
       (74, 7, 'Jomboy'),
       (75, 7, 'Kattakurgan'),
       (76, 7, 'Qoʻshrabot'),
       (77, 7, 'Narpay'),
       (78, 7, 'Nurobod'),
       (79, 7, 'Oqdaryo'),
       (80, 7, 'Paxtachi'),
       (81, 7, 'Payariq'),
       (82, 7, 'Pastdargʻom'),
       (83, 7, 'Samarqand'),
       (84, 7, 'Toyloq'),
       (85, 7, 'Urgut');

-- list of district Qashqadaryo region Chiroqchi    ,Dehqonobod   ,Gʻuzor       ,Qamashi      ,Qarshi       ,Koson        ,Kasbi        ,Kitob        ,Mirishkor  ,Muborak  ,Nishon  ,Shakhrisabz  ,Yakkabog.
insert into district(id, region_id, name)
values (86, 8, 'Chiroqchi'),
       (87, 8, 'Dehqonobod'),
       (88, 8, 'Gʻuzor'),
       (89, 8, 'Qamashi'),
       (90, 8, 'Qarshi'),
       (91, 8, 'Koson'),
       (92, 8, 'Kasbi'),
       (93, 8, 'Kitob'),
       (94, 8, 'Mirishkor'),
       (95, 8, 'Muborak'),
       (96, 8, 'Nishon'),
       (97, 8, 'Shakhrisabz'),
       (98, 8, 'Yakkabog');

-- list of district Surxondaryo region Angor       ,Bandixon    ,Boysun      ,Denov       ,Jarqoʻrgʻon ,Qiziriq     ,Qumqoʻrgʻon ,Muzrabot    ,Oltinsoy    ,Sariosiyo   ,Sherobod    ,Shoʻrchi    ,Termiz      ,Uzun
insert into district(id, region_id, name)
values (99, 9, 'Angor'),
       (100, 9, 'Bandixon'),
       (101, 9, 'Boysun'),
       (102, 9, 'Denov'),
       (103, 9, 'Jarqoʻrgʻon'),
       (104, 9, 'Qiziriq'),
       (105, 9, 'Qumqoʻrgʻon'),
       (106, 9, 'Muzrabot'),
       (107, 9, 'Oltinsoy'),
       (108, 9, 'Sariosiyo'),
       (109, 9, 'Sherobod'),
       (110, 9, 'Shoʻrchi'),
       (111, 9, 'Termiz'),
       (112, 9, 'Uzun');


-- list of district Sirdaryo region Oqoltin   ,Boyovut   ,Guliston  ,Xovos     ,Mirzaobod ,Sardoba   ,Sayxunobod,Sirdaryo

insert into district(id, region_id, name)
values (113, 10, 'Oqoltin'),
       (114, 10, 'Boyovut'),
       (115, 10, 'Guliston'),
       (116, 10, 'Xovos'),
       (117, 10, 'Mirzaobod'),
       (118, 10, 'Sardoba'),
       (119, 10, 'Sayxunobod'),
       (120, 10, 'Sirdaryo');

-- list of district Tashkent region Bekabad          ,Boʻstonliq       ,Boʻka            ,Chinoz           ,Qibray           ,Ohangaron        ,Oqqoʻrgʻon       ,Parkent          ,Piskent          ,Quyichirchiq     ,Zangiota         ,Oʻrtachirchiq    ,Yangiyoʻl        ,Yuqorichirchiq   ,Tashkent
insert into district(id, region_id, name)
values (121, 11, 'Bekabad'),
       (122, 11, 'Boʻstonliq'),
       (123, 11, 'Boʻka'),
       (124, 11, 'Chinoz'),
       (125, 11, 'Qibray'),
       (126, 11, 'Ohangaron'),
       (127, 11, 'Oqqoʻrgʻon'),
       (128, 11, 'Parkent'),
       (129, 11, 'Piskent'),
       (130, 11, 'Quyichirchiq'),
       (131, 11, 'Zangiota'),
       (132, 11, 'Oʻrtachirchiq'),
       (133, 11, 'Yangiyoʻl'),
       (134, 11, 'Yuqorichirchiq'),
       (135, 11, 'Tashkent');

-- list of district Namangan region Chortoq      ,Chust        ,Kosonsoy     ,Mingbuloq    ,Namangan     ,Norin        ,Pop          ,Toʻraqoʻrgʻo ,Uchqoʻrgʻon   ,Uychi         ,Yangiqoʻrgʻon
insert into district(id, region_id, name)
values (136, 12, 'Chortoq'),
       (137, 12, 'Chust'),
       (138, 12, 'Kosonsoy'),
       (139, 12, 'Mingbuloq'),
       (140, 12, 'Namangan'),
       (141, 12, 'Norin'),
       (142, 12, 'Pop'),
       (143, 12, 'Toʻraqoʻrgʻo'),
       (144, 12, 'Uchqoʻrgʻon'),
       (145, 12, 'Uychi'),
       (146, 12, 'Yangiqoʻrgʻon');

-- list of district Fergana region Oltiariq  ,Bagʻdod   ,Beshariq  ,Buvayda   ,Dangʻara  ,Fergana   ,Furqat    ,Qoʻshtepa ,Quva       ,Rishton    ,Soʻx       ,Toshloq    ,Uchkoʻprik ,Uzbekistan ,Yozyovon
insert into district(id, region_id, name)
values (147, 13, 'Oltiariq'),
       (148, 13, 'Bagʻdod'),
       (149, 13, 'Beshariq'),
       (150, 13, 'Buvayda'),
       (151, 13, 'Dangʻara'),
       (152, 13, 'Fergana'),
       (153, 13, 'Furqat'),
       (154, 13, 'Qoʻshtepa'),
       (155, 13, 'Quva'),
       (156, 13, 'Rishton'),
       (157, 13, 'Soʻx'),
       (158, 13, 'Toshloq'),
       (159, 13, 'Uchkoʻprik'),
       (160, 13, 'Uzbekistan'),
       (161, 13, 'Yozyovon');

-- list of district Tashkent City region Bektemir,Chilanzar,Yashnobod,Mirobod,Mirzo Ulugbek,Sergeli,Shayxontoxur,Olmazor,Uchtepa,Yakkasaray,Yunusabad,Yangihayot
insert into district(id, region_id, name)
values (162, 14, 'Bektemir'),
       (163, 14, 'Chilanzar'),
       (164, 14, 'Yashnobod'),
       (165, 14, 'Mirobod'),
       (166, 14, 'Mirzo Ulugbek'),
       (167, 14, 'Sergeli'),
       (168, 14, 'Shayxontoxur'),
       (169, 14, 'Olmazor'),
       (170, 14, 'Uchtepa'),
       (171, 14, 'Yakkasaray'),
       (172, 14, 'Yunusabad'),
       (173, 14, 'Yangihayot');



insert into role (code, name)
values ('ADMIN', 'Admin');
insert into role (code, name)
values ('PITCH_OWNER', 'Pitch Owner');
insert into role (code, name)
values ('USER', 'User');



insert into permission(code, name)
values ('MANAGE_ADMINS', 'Manage Admins');
insert into permission(code, name)
values ('MANAGE_PERMISSION', 'Manage Permissions');
insert into permission(code, name)
values ('ADD_PERMISSION', 'Add Permissions');


insert into orders(minutes, start_time, order_status, pitch_id, user_id)
values (120, now() + interval '2 hours', 0, '5e9efa5d-81ab-4b88-b1f4-b23a77626ddd',
        '842311e4-3fd7-40df-978e-3336e0ae1312');

insert into orders(minutes, start_time, order_status, pitch_id, user_id)
values (120, now() + interval '4 hours', 0, '5e9efa5d-81ab-4b88-b1f4-b23a77626ddd',
        '842311e4-3fd7-40df-978e-3336e0ae1312');

insert into orders(minutes, start_time, order_status, pitch_id, user_id)
values (120, now() + interval '4 hours', 0, '5e9efa5d-81ab-4b88-b1f4-b23a77626ddd',
        '842311e4-3fd7-40df-978e-3336e0ae1312');
