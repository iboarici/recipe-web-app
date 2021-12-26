INSERT INTO public.USERS(username, password, ROLE) VALUES('admin', '$2a$10$.USkMOMyXJrVpsXVnzgCReodT1FxnHs7kf0XKeGZdXhdU6X0kny1u', 'ADMIN' ) on conflict (username) do nothing;

INSERT INTO public.CATEGORIES (ID, NAME) VALUES (1, 'All') on conflict (ID) do nothing;
INSERT INTO public.CATEGORIES (ID, NAME) VALUES (2, 'Breakfast') on conflict (ID) do nothing;
INSERT INTO public.CATEGORIES (ID, NAME) VALUES (3, 'Lunch') on conflict (ID) do nothing;
INSERT INTO public.CATEGORIES (ID, NAME) VALUES (4, 'Dinner') on conflict (ID) do nothing;
INSERT INTO public.CATEGORIES (ID, NAME) VALUES (5, 'Snack') on conflict (ID) do nothing;

INSERT INTO public.RECIPES (ID, CATEGORY_ID, TITLE, CREATE_DATE, VEGETARIAN, SUITABLE_PEOPLE_COUNT,
                           PREPARE_TIME_IN_MINUTES,
                           COOKING_TIME_IN_MINUTES, CALORIES, IMAGE_URL, VIDEO_URL, CREATED_USER)
    VALUES (1,4, 'İçli Köfte', TO_TIMESTAMP('05-7-2018 10:27', 'DD-MM-YYYY HH24:MI'), false, 4, 40, 120, 1500, '', '', 'admin') on conflict (ID) do nothing;

INSERT INTO public.RECIPES (ID, CATEGORY_ID, TITLE, CREATE_DATE, VEGETARIAN, SUITABLE_PEOPLE_COUNT,
                           PREPARE_TIME_IN_MINUTES,
                           COOKING_TIME_IN_MINUTES, CALORIES, IMAGE_URL, VIDEO_URL, CREATED_USER)
    VALUES (2,3, 'Izgara Tavuk', TO_TIMESTAMP('19-4-2019 10:27', 'DD-MM-YYYY HH24:MI'), false, 6, 40, 30, 700, '', '', 'admin' ) on conflict (ID) do nothing;

INSERT INTO public.RECIPES (ID, CATEGORY_ID, TITLE, CREATE_DATE, VEGETARIAN, SUITABLE_PEOPLE_COUNT,
                           PREPARE_TIME_IN_MINUTES,
                           COOKING_TIME_IN_MINUTES, CALORIES, IMAGE_URL, VIDEO_URL, CREATED_USER)
    VALUES (3,3, 'Kuru Fasulye', TO_TIMESTAMP('04-02-2021 10:27', 'DD-MM-YYYY HH24:MI'), false, 4, 40, 60, 900, '', '', 'admin' ) on conflict (ID) do nothing;

INSERT INTO public.RECIPES (ID, CATEGORY_ID, TITLE, CREATE_DATE, VEGETARIAN, SUITABLE_PEOPLE_COUNT,
                           PREPARE_TIME_IN_MINUTES,
                           COOKING_TIME_IN_MINUTES, CALORIES, IMAGE_URL, VIDEO_URL, CREATED_USER)
    VALUES (4,3, 'Taze Fasulye', TO_TIMESTAMP('11-06-2020 15:12', 'DD-MM-YYYY HH24:MI'), false, 2, 40, 60, 600, '', '', 'admin' ) on conflict (ID) do nothing;

INSERT INTO public.RECIPES (ID, CATEGORY_ID, TITLE, CREATE_DATE, VEGETARIAN, SUITABLE_PEOPLE_COUNT,
                           PREPARE_TIME_IN_MINUTES,
                           COOKING_TIME_IN_MINUTES, CALORIES, IMAGE_URL, VIDEO_URL, CREATED_USER)
    VALUES (5,2, 'Sucuklu Yumurta', TO_TIMESTAMP('11-06-2020 15:12', 'DD-MM-YYYY HH24:MI'), false, 2, 5, 5, 500, '', '', 'admin' ) on conflict (ID) do nothing;

INSERT INTO public.RECIPES (ID, CATEGORY_ID, TITLE, CREATE_DATE, VEGETARIAN, SUITABLE_PEOPLE_COUNT,
                           PREPARE_TIME_IN_MINUTES,
                           COOKING_TIME_IN_MINUTES, CALORIES, IMAGE_URL, VIDEO_URL, CREATED_USER)
    VALUES (6,5, 'Sigara Boregi', TO_TIMESTAMP('05-05-2012 11:12', 'DD-MM-YYYY HH24:MI'), false, 2, 50, 10, 1200, '', '', 'admin' ) on conflict (ID) do nothing;

INSERT INTO public.RECIPES (ID, CATEGORY_ID, TITLE, CREATE_DATE, VEGETARIAN, SUITABLE_PEOPLE_COUNT,
                           PREPARE_TIME_IN_MINUTES,
                           COOKING_TIME_IN_MINUTES, CALORIES, IMAGE_URL, VIDEO_URL, CREATED_USER)
    VALUES (7,5, 'Patates Kızartması', TO_TIMESTAMP('01-01-2011 08:00', 'DD-MM-YYYY HH24:MI'), false, 2, 30, 10, 1600, '', '', 'admin' ) on conflict (ID) do nothing;

INSERT INTO public.INGREDIENTS (ID, RECIPE_ID, NAME, QUANTITY, UNIT, SIZE, ADDITONAL_INFO) VALUES (1, 1, 'ince köftelik bulgur', 2, 'su bardağı', '', '' ) on conflict (ID) do nothing;
INSERT INTO public.INGREDIENTS (ID, RECIPE_ID, NAME, QUANTITY, UNIT, SIZE, ADDITONAL_INFO) VALUES (2, 1, 'irmik', 1, 'su bardağı', '', '' ) on conflict (ID) do nothing;
INSERT INTO public.INGREDIENTS (ID, RECIPE_ID, NAME, QUANTITY, UNIT, SIZE, ADDITONAL_INFO) VALUES (3, 1, 'yumurta', 1, 'adet', '', '' ) on conflict (ID) do nothing;
INSERT INTO public.INGREDIENTS (ID, RECIPE_ID, NAME, QUANTITY, UNIT, SIZE, ADDITONAL_INFO) VALUES (4, 1, 'un', 3, 'yemek kaşığı', '', '' ) on conflict (ID) do nothing;
INSERT INTO public.INGREDIENTS (ID, RECIPE_ID, NAME, QUANTITY, UNIT, SIZE, ADDITONAL_INFO) VALUES (5, 1, 'domates salçası', 1, 'yemek kaşığı', '', '' ) on conflict (ID) do nothing;
INSERT INTO public.INGREDIENTS (ID, RECIPE_ID, NAME, QUANTITY, UNIT, SIZE, ADDITONAL_INFO) VALUES (6, 1, 'biber salçası', 1, 'yemek kaşığı', '', '' ) on conflict (ID) do nothing;
INSERT INTO public.INGREDIENTS (ID, RECIPE_ID, NAME, QUANTITY, UNIT, SIZE, ADDITONAL_INFO) VALUES (7, 1, 'zeytinyağı', 1, 'yemek kaşığı', '', '' ) on conflict (ID) do nothing;
INSERT INTO public.INGREDIENTS (ID, RECIPE_ID, NAME, QUANTITY, UNIT, SIZE, ADDITONAL_INFO) VALUES (8, 1, 'pulbiber', 1, 'çay kaşığı', '', 'silme' ) on conflict (ID) do nothing;
INSERT INTO public.INGREDIENTS (ID, RECIPE_ID, NAME, QUANTITY, UNIT, SIZE, ADDITONAL_INFO) VALUES (9, 1, 'sıcak su', 3, 'su bardağı', '', '' ) on conflict (ID) do nothing;
INSERT INTO public.INGREDIENTS (ID, RECIPE_ID, NAME, QUANTITY, UNIT, SIZE, ADDITONAL_INFO) VALUES (10, 1, 'kıyma', 550, 'gram', '', '' ) on conflict (ID) do nothing;
INSERT INTO public.INGREDIENTS (ID, RECIPE_ID, NAME, QUANTITY, UNIT, SIZE, ADDITONAL_INFO) VALUES (11, 1, 'soğan', 4, 'adet', 'orta boy', '' ) on conflict (ID) do nothing;
INSERT INTO public.INGREDIENTS (ID, RECIPE_ID, NAME, QUANTITY, UNIT, SIZE, ADDITONAL_INFO) VALUES (12, 1, 'tuz', 4, 'çay kaşığı', '', '' ) on conflict (ID) do nothing;
INSERT INTO public.INGREDIENTS (ID, RECIPE_ID, NAME, QUANTITY, UNIT, SIZE, ADDITONAL_INFO) VALUES (13, 1, 'nane', 1, 'çay kaşığı', '', 'arzuya göre' ) on conflict (ID) do nothing;
INSERT INTO public.INGREDIENTS (ID, RECIPE_ID, NAME, QUANTITY, UNIT, SIZE, ADDITONAL_INFO) VALUES (14, 1, 'pul biber', 2, 'çay kaşığı', '', 'arzuya göre' ) on conflict (ID) do nothing;
INSERT INTO public.INGREDIENTS (ID, RECIPE_ID, NAME, QUANTITY, UNIT, SIZE, ADDITONAL_INFO) VALUES (15, 1, 'kekik', 1, 'çay kaşığı', '', 'arzuya göre' ) on conflict (ID) do nothing;
INSERT INTO public.INGREDIENTS (ID, RECIPE_ID, NAME, QUANTITY, UNIT, SIZE, ADDITONAL_INFO) VALUES (16, 1, 'karabiber', 1, 'çay kaşığı', '', 'arzuya göre' ) on conflict (ID) do nothing;
INSERT INTO public.INGREDIENTS (ID, RECIPE_ID, NAME, QUANTITY, UNIT, SIZE, ADDITONAL_INFO) VALUES (17, 1, 'kimyon', 1, 'çimdik', '', 'arzuya göre' ) on conflict (ID) do nothing;
INSERT INTO public.INGREDIENTS (ID, RECIPE_ID, NAME, QUANTITY, UNIT, SIZE, ADDITONAL_INFO) VALUES (18, 1, 'maydonoz', 10, 'sap', '', '' ) on conflict (ID) do nothing;
INSERT INTO public.INGREDIENTS (ID, RECIPE_ID, NAME, QUANTITY, UNIT, SIZE, ADDITONAL_INFO) VALUES (19, 1, 'ceviz içi', 1, 'dilediğiniz kadar', '', 'iri çekilmiş' ) on conflict (ID) do nothing;
INSERT INTO public.INGREDIENTS (ID, RECIPE_ID, NAME, QUANTITY, UNIT, SIZE, ADDITONAL_INFO) VALUES (20, 1, 'tereyağı veya margarin', 200, 'gram', '', '' ) on conflict (ID) do nothing;

INSERT INTO public.INGREDIENTS (ID, RECIPE_ID, NAME, QUANTITY, UNIT, SIZE, ADDITONAL_INFO) VALUES (21, 2, 'tavuk göğsü', 1, 'kilogram', '', '' ) on conflict (ID) do nothing;
INSERT INTO public.INGREDIENTS (ID, RECIPE_ID, NAME, QUANTITY, UNIT, SIZE, ADDITONAL_INFO) VALUES (22, 2, 'salça', 1, 'yemek kaşığı', '', '' ) on conflict (ID) do nothing;
INSERT INTO public.INGREDIENTS (ID, RECIPE_ID, NAME, QUANTITY, UNIT, SIZE, ADDITONAL_INFO) VALUES (23, 2, 'yoğurt', 2, 'yemek kaşığı', '', '' ) on conflict (ID) do nothing;
INSERT INTO public.INGREDIENTS (ID, RECIPE_ID, NAME, QUANTITY, UNIT, SIZE, ADDITONAL_INFO) VALUES (24, 2, 'sıvı yağ', 1, 'yemek kaşığı', '', '' ) on conflict (ID) do nothing;
INSERT INTO public.INGREDIENTS (ID, RECIPE_ID, NAME, QUANTITY, UNIT, SIZE, ADDITONAL_INFO) VALUES (25, 2, ' pul biber', 1, 'tatlı kaşığı', '', '' ) on conflict (ID) do nothing;
INSERT INTO public.INGREDIENTS (ID, RECIPE_ID, NAME, QUANTITY, UNIT, SIZE, ADDITONAL_INFO) VALUES (26, 2, 'kekik', 1.5, 'tatlı kaşığı', '', '' ) on conflict (ID) do nothing;
INSERT INTO public.INGREDIENTS (ID, RECIPE_ID, NAME, QUANTITY, UNIT, SIZE, ADDITONAL_INFO) VALUES (27, 2, 'kimyon', 1, 'çay kaşığı', '', '' ) on conflict (ID) do nothing;
INSERT INTO public.INGREDIENTS (ID, RECIPE_ID, NAME, QUANTITY, UNIT, SIZE, ADDITONAL_INFO) VALUES (28, 2, 'tuz', 2, 'tatlı kaşığı', '', '' ) on conflict (ID) do nothing;

INSERT INTO public.INGREDIENTS (ID, RECIPE_ID, NAME, QUANTITY, UNIT, SIZE, ADDITONAL_INFO) VALUES (29, 3, 'sıvı yağ', 4, 'yemek kaşığı', '', '' ) on conflict (ID) do nothing;
INSERT INTO public.INGREDIENTS (ID, RECIPE_ID, NAME, QUANTITY, UNIT, SIZE, ADDITONAL_INFO) VALUES (30, 3, 'tereyağı', 2, 'yemek kaşığı', '', '' ) on conflict (ID) do nothing;
INSERT INTO public.INGREDIENTS (ID, RECIPE_ID, NAME, QUANTITY, UNIT, SIZE, ADDITONAL_INFO) VALUES (31, 3, 'kuru soğan', 1, 'adet', 'orta boy', 'yemeklik doğranmış' ) on conflict (ID) do nothing;
INSERT INTO public.INGREDIENTS (ID, RECIPE_ID, NAME, QUANTITY, UNIT, SIZE, ADDITONAL_INFO) VALUES (32, 3, 'domates salçası', 1, 'yemek kaşığı', '', '' ) on conflict (ID) do nothing;
INSERT INTO public.INGREDIENTS (ID, RECIPE_ID, NAME, QUANTITY, UNIT, SIZE, ADDITONAL_INFO) VALUES (33, 3, 'kuru fasulye', 500, 'gram', '', '1 gece önceden suda bekletilmiş' ) on conflict (ID) do nothing;
INSERT INTO public.INGREDIENTS (ID, RECIPE_ID, NAME, QUANTITY, UNIT, SIZE, ADDITONAL_INFO) VALUES (34, 3, 'su', 3, 'su bardağı', '', '' ) on conflict (ID) do nothing;
INSERT INTO public.INGREDIENTS (ID, RECIPE_ID, NAME, QUANTITY, UNIT, SIZE, ADDITONAL_INFO) VALUES (35, 3, 'tuz', 1, 'çay kaşığı', '', '' ) on conflict (ID) do nothing;
INSERT INTO public.INGREDIENTS (ID, RECIPE_ID, NAME, QUANTITY, UNIT, SIZE, ADDITONAL_INFO) VALUES (36, 3, 'toz şeker', 1, 'çay kaşığı', '', '' ) on conflict (ID) do nothing;
INSERT INTO public.INGREDIENTS (ID, RECIPE_ID, NAME, QUANTITY, UNIT, SIZE, ADDITONAL_INFO) VALUES (37, 3, 'tatlı toz kırmızı biber', 0.5, 'çay kaşığı', '', '' ) on conflict (ID) do nothing;


INSERT INTO public.INGREDIENTS (ID, RECIPE_ID, NAME, QUANTITY, UNIT, SIZE, ADDITONAL_INFO) VALUES (38, 4, 'taze fasulye', 600, 'gram', '', '' ) on conflict (ID) do nothing;
INSERT INTO public.INGREDIENTS (ID, RECIPE_ID, NAME, QUANTITY, UNIT, SIZE, ADDITONAL_INFO) VALUES (39, 4, 'zeytinyağı', 5, 'yemek kaşığı', '', '' ) on conflict (ID) do nothing;
INSERT INTO public.INGREDIENTS (ID, RECIPE_ID, NAME, QUANTITY, UNIT, SIZE, ADDITONAL_INFO) VALUES (40, 4, 'kuru soğan', 1, 'adet', 'büyük boy', '' ) on conflict (ID) do nothing;
INSERT INTO public.INGREDIENTS (ID, RECIPE_ID, NAME, QUANTITY, UNIT, SIZE, ADDITONAL_INFO) VALUES (41, 4, 'sarımsak', 2, 'diş', '', '' ) on conflict (ID) do nothing;
INSERT INTO public.INGREDIENTS (ID, RECIPE_ID, NAME, QUANTITY, UNIT, SIZE, ADDITONAL_INFO) VALUES (42, 4, 'domates', 3, 'adet', 'orta boy', 'etli ve sulu' ) on conflict (ID) do nothing;
INSERT INTO public.INGREDIENTS (ID, RECIPE_ID, NAME, QUANTITY, UNIT, SIZE, ADDITONAL_INFO) VALUES (43, 4, 'sıcak su', 1, 'su bardağı', '', '' ) on conflict (ID) do nothing;
INSERT INTO public.INGREDIENTS (ID, RECIPE_ID, NAME, QUANTITY, UNIT, SIZE, ADDITONAL_INFO) VALUES (44, 4, 'toz şeker', 1, 'tatlı kaşığı', '', '' ) on conflict (ID) do nothing;
INSERT INTO public.INGREDIENTS (ID, RECIPE_ID, NAME, QUANTITY, UNIT, SIZE, ADDITONAL_INFO) VALUES (45, 4, 'tuz', 2, 'çay kaşığı', '', '' ) on conflict (ID) do nothing;


INSERT INTO public.INGREDIENTS (ID, RECIPE_ID, NAME, QUANTITY, UNIT, SIZE, ADDITONAL_INFO) VALUES (46, 5, 'sucuk', 100, 'gram', '', '' ) on conflict (ID) do nothing;
INSERT INTO public.INGREDIENTS (ID, RECIPE_ID, NAME, QUANTITY, UNIT, SIZE, ADDITONAL_INFO) VALUES (47, 5, 'yumurta', 1, 'adet', '', '' ) on conflict (ID) do nothing;
INSERT INTO public.INGREDIENTS (ID, RECIPE_ID, NAME, QUANTITY, UNIT, SIZE, ADDITONAL_INFO) VALUES (48, 5, 'tuz', 0.25, 'çay kaşığı', '', '' ) on conflict (ID) do nothing;


INSERT INTO public.INGREDIENTS (ID, RECIPE_ID, NAME, QUANTITY, UNIT, SIZE, ADDITONAL_INFO) VALUES (49, 6, 'yufka', 2, 'adet', '', 'tercihen günlük taze' ) on conflict (ID) do nothing;
INSERT INTO public.INGREDIENTS (ID, RECIPE_ID, NAME, QUANTITY, UNIT, SIZE, ADDITONAL_INFO) VALUES (50, 6, 'lor peyniri', 250, 'gram', '', '' ) on conflict (ID) do nothing;
INSERT INTO public.INGREDIENTS (ID, RECIPE_ID, NAME, QUANTITY, UNIT, SIZE, ADDITONAL_INFO) VALUES (51, 6, 'maydanoz', 0.5, 'demet', '', 'ayıklanmış' ) on conflict (ID) do nothing;
INSERT INTO public.INGREDIENTS (ID, RECIPE_ID, NAME, QUANTITY, UNIT, SIZE, ADDITONAL_INFO) VALUES (52, 6, 'tuz', 1, 'çay kaşığı', '', '' ) on conflict (ID) do nothing;
INSERT INTO public.INGREDIENTS (ID, RECIPE_ID, NAME, QUANTITY, UNIT, SIZE, ADDITONAL_INFO) VALUES (53, 6, 'ayçiçek yağı', 1, 'su bardağı', '', '' ) on conflict (ID) do nothing;

INSERT INTO public.INGREDIENTS (ID, RECIPE_ID, NAME, QUANTITY, UNIT, SIZE, ADDITONAL_INFO) VALUES (54, 7, 'kızartmalık patates', 4, 'adet', 'orta boy', '' ) on conflict (ID) do nothing;
INSERT INTO public.INGREDIENTS (ID, RECIPE_ID, NAME, QUANTITY, UNIT, SIZE, ADDITONAL_INFO) VALUES (55, 7, 'ayçiçek yağı', 2, 'su bardağı', '', '' ) on conflict (ID) do nothing;
INSERT INTO public.INGREDIENTS (ID, RECIPE_ID, NAME, QUANTITY, UNIT, SIZE, ADDITONAL_INFO) VALUES (56, 7, 'tuz', 0.5, 'çay kaşığı', '', '' ) on conflict (ID) do nothing;
INSERT INTO public.INGREDIENTS (ID, RECIPE_ID, NAME, QUANTITY, UNIT, SIZE, ADDITONAL_INFO) VALUES (57, 7, 'kuru baharat karışımı', 0.5, 'çay kaşığı', '', '' ) on conflict (ID) do nothing;


INSERT INTO public.COOKING_INSTRUCTIONS (ID, RECIPE_ID, ORDER_ID, DESCRIPTION, IMAGE_URL, VIDEO_URL) VALUES (1, 1, 1, 'Bulguru ve irmiği ağzı kapaklı bir kapta ıslatın ve ağzını kapatıp yaklaşık 30 dakika dinlenmeye bırakın.', '', '' ) on conflict (ID) do nothing;
INSERT INTO public.COOKING_INSTRUCTIONS (ID, RECIPE_ID, ORDER_ID, DESCRIPTION, IMAGE_URL, VIDEO_URL) VALUES (2, 1, 2, '200 gram yağı tencereye alın ve kıymayı rengi dönene kadar sürekli karıştırarak pişirin', '', '' ) on conflict (ID) do nothing;
INSERT INTO public.COOKING_INSTRUCTIONS (ID, RECIPE_ID, ORDER_ID, DESCRIPTION, IMAGE_URL, VIDEO_URL) VALUES (3, 1, 3, 'Kıymanın her yeri renk aldıktan sonra tane tane olması için yarım çay bardağı su ekleyin ve suyunu çekene kadar kavurun.', '', '' ) on conflict (ID) do nothing;
INSERT INTO public.COOKING_INSTRUCTIONS (ID, RECIPE_ID, ORDER_ID, DESCRIPTION, IMAGE_URL, VIDEO_URL) VALUES (4, 1, 4, 'Suyunu çektikten sonra küp küp doğranmış soğanı ilave edip kavurmaya devam edin.', '', '' ) on conflict (ID) do nothing;
INSERT INTO public.COOKING_INSTRUCTIONS (ID, RECIPE_ID, ORDER_ID, DESCRIPTION, IMAGE_URL, VIDEO_URL) VALUES (5, 1, 5, 'Soğanlar tamamen kavrulduktan sonra 2 çay kaşığı tuzu ve baharatları ilave edin', '', '' ) on conflict (ID) do nothing;
INSERT INTO public.COOKING_INSTRUCTIONS (ID, RECIPE_ID, ORDER_ID, DESCRIPTION, IMAGE_URL, VIDEO_URL) VALUES (6, 1, 6, 'Cevizleri de ilave edip ocağın altını kapatın. Maydanozları ekleyip son kez karıştırıp soğuması için başka bir kaba alın.', '', '' ) on conflict (ID) do nothing;
INSERT INTO public.COOKING_INSTRUCTIONS (ID, RECIPE_ID, ORDER_ID, DESCRIPTION, IMAGE_URL, VIDEO_URL) VALUES (7, 1, 7, 'Şişmeye bıraktığımız bulhurlu karışımı bir tepsiye (varsa çiğ köfte tepsisi) veya bir kaba çıkarın ve üzerine yağ, salça, un, yumurta, pul biber ve 2 çay kaşığı tuzu ekleyip güzelce yoğurun', '', '' ) on conflict (ID) do nothing;
INSERT INTO public.COOKING_INSTRUCTIONS (ID, RECIPE_ID, ORDER_ID, DESCRIPTION, IMAGE_URL, VIDEO_URL) VALUES (8, 1, 8, 'Yaklaşık 15 dakika yoğurduktan sonra bütün hamuru bir araya getirin ve üzerini nemli bir bezle örtün. Yaklaşık 30 dakika dinlendirin.', '', '' ) on conflict (ID) do nothing;
INSERT INTO public.COOKING_INSTRUCTIONS (ID, RECIPE_ID, ORDER_ID, DESCRIPTION, IMAGE_URL, VIDEO_URL) VALUES (9, 1, 9, 'Hamurunuzdan cevizden büyük yumurtadan küçük parçalar alıp yuvarlayın. Baş veya işaret parmağınızla açıp içine harç doldurup kapatın.', '', '' ) on conflict (ID) do nothing;
INSERT INTO public.COOKING_INSTRUCTIONS (ID, RECIPE_ID, ORDER_ID, DESCRIPTION, IMAGE_URL, VIDEO_URL) VALUES (10, 1, 10, 'Eğer elinizde açamıyorsanız hamuru yağlı kağıdın arasına koyup büyükçe açarak kalıplar halinde çıkardığınız parçalara poğaça gibi iç harç koyup kapatabilirsiniz.', '', '' ) on conflict (ID) do nothing;
INSERT INTO public.COOKING_INSTRUCTIONS (ID, RECIPE_ID, ORDER_ID, DESCRIPTION, IMAGE_URL, VIDEO_URL) VALUES (11, 1, 11, 'Kızgın yağda kızartın. Dilerseniz suda da haşlayabilirsiniz.', '', '' ) on conflict (ID) do nothing;

INSERT INTO public.COOKING_INSTRUCTIONS (ID, RECIPE_ID, ORDER_ID, DESCRIPTION, IMAGE_URL, VIDEO_URL) VALUES (12, 2, 1, 'Tavuklar ince kesilir. Salça, yoğurt, sıvı yağ ve baharatlar karıştırılır. Tavuklar içine koyulup marine edilir.', '', '' ) on conflict (ID) do nothing;
INSERT INTO public.COOKING_INSTRUCTIONS (ID, RECIPE_ID, ORDER_ID, DESCRIPTION, IMAGE_URL, VIDEO_URL) VALUES (13, 2, 2, 'Dolapta 2 saat bekletilir. Tavuklar ızgaraya dizilir. Közde dilediğiniz ayarda pişirilir. Afiyet olsun.', '', '' ) on conflict (ID) do nothing;

INSERT INTO public.COOKING_INSTRUCTIONS (ID, RECIPE_ID, ORDER_ID, DESCRIPTION, IMAGE_URL, VIDEO_URL) VALUES (14, 3, 1, '4 yemek kaşığı sıvı yağ ve 2 yemek kaşığı tereyağını derin bir tencereye aktarın ve kızdırın.1 adet yemeklik doğranmış kuru soğanı da ekleyin ve pembeleşinceye kadar kavurun.', 'https://cdn.yemek.com/mncrop/600/315/uploads/2015/11/kuru-fasulye-adim-1.jpg', '' ) on conflict (ID) do nothing;
INSERT INTO public.COOKING_INSTRUCTIONS (ID, RECIPE_ID, ORDER_ID, DESCRIPTION, IMAGE_URL, VIDEO_URL) VALUES (15, 3, 2, 'Soğanlar kavrulduktan sonra 1 yemek kaşığı domates salçasını ekleyin ve kokusu çıkana kadar kavurun.', 'https://cdn.yemek.com/mncrop/600/315/uploads/2015/11/kuru-fasulye-adim-2.jpg', '' ) on conflict (ID) do nothing;
INSERT INTO public.COOKING_INSTRUCTIONS (ID, RECIPE_ID, ORDER_ID, DESCRIPTION, IMAGE_URL, VIDEO_URL) VALUES (16, 3, 3, 'Son olarak bir gece önceden suda beklettiğiniz kuru fasulyeleri de tencereye ekleyin.', 'https://cdn.yemek.com/mncrop/600/315/uploads/2015/11/kuru-fasulye-adim-3.jpg', '' ) on conflict (ID) do nothing;
INSERT INTO public.COOKING_INSTRUCTIONS (ID, RECIPE_ID, ORDER_ID, DESCRIPTION, IMAGE_URL, VIDEO_URL) VALUES (17, 3, 4, 'Sonra 1-2 dakika karıştırın ve 3 su bardağı su, 1 çay kaşığı kırmızı toz biber ve 1 çay kaşığı tuzu ekleyin, kapağını kapatıp fasulyeler yumuşayana kadar pişirin.', 'https://cdn.yemek.com/mncrop/600/315/uploads/2015/11/kuru-fasulye-adim-4.jpg', '' ) on conflict (ID) do nothing;
INSERT INTO public.COOKING_INSTRUCTIONS (ID, RECIPE_ID, ORDER_ID, DESCRIPTION, IMAGE_URL, VIDEO_URL) VALUES (18, 3, 5, 'Tane tane pişmiş pirinç pilavı ile servis edin, afiyet olsun!', 'https://cdn.yemek.com/mncrop/600/315/uploads/2015/11/kuru-fasulye-adim-5.jpg', '' ) on conflict (ID) do nothing;


INSERT INTO public.COOKING_INSTRUCTIONS (ID, RECIPE_ID, ORDER_ID, DESCRIPTION, IMAGE_URL, VIDEO_URL) VALUES (19, 4, 1, 'Kuru soğanları küçük küpler halinde kesin. Sarımsakları ince ince dilimleyin. Domatesleri rendeleyin. Fasulyeleri de ayıklayarak bir köşeye alın.5 yemek kaşığı zeytinyağını bir tencerede kızdırın.', 'https://cdn.yemek.com/mncrop/600/315/uploads/2015/07/taze-fasule-adim-1.jpg', '' ) on conflict (ID) do nothing;
INSERT INTO public.COOKING_INSTRUCTIONS (ID, RECIPE_ID, ORDER_ID, DESCRIPTION, IMAGE_URL, VIDEO_URL) VALUES (20, 4, 2, 'Doğradığınız 1 adet büyük boy kuru soğan ve 2 diş doğranmış sarımsakları hafif bir renk alana kadar kavurun.', 'https://cdn.yemek.com/mncrop/600/315/uploads/2015/07/taze-fasule-adim-2.jpg', '' ) on conflict (ID) do nothing;
INSERT INTO public.COOKING_INSTRUCTIONS (ID, RECIPE_ID, ORDER_ID, DESCRIPTION, IMAGE_URL, VIDEO_URL) VALUES (21, 4, 3, '600 gram doğranmış taze fasulyeyi de ekleyerek kavurma işlemini sürdürün.', 'https://cdn.yemek.com/mncrop/600/315/uploads/2015/07/taze-fasule-adim-3.jpg', '' ) on conflict (ID) do nothing;
INSERT INTO public.COOKING_INSTRUCTIONS (ID, RECIPE_ID, ORDER_ID, DESCRIPTION, IMAGE_URL, VIDEO_URL) VALUES (22, 4, 4, '2 çay kaşığı tuz, 1 tatlı kaşığı toz şeker, 3 adet rendelenmiş domates ve 1 su bardağı sıcak suyu da ekledikten sonra güzelce karıştırın.', 'https://cdn.yemek.com/mncrop/600/315/uploads/2015/07/taze-fasule-adim-4.jpg', '' ) on conflict (ID) do nothing;
INSERT INTO public.COOKING_INSTRUCTIONS (ID, RECIPE_ID, ORDER_ID, DESCRIPTION, IMAGE_URL, VIDEO_URL) VALUES (23, 4, 5, 'Kapağını kapatıp fasulyeler yumuşayana kadar kısık ateşte pişirmeye bırakın.', 'https://cdn.yemek.com/mncrop/600/315/uploads/2015/07/taze-fasule-adim-5.jpg', '' ) on conflict (ID) do nothing;
INSERT INTO public.COOKING_INSTRUCTIONS (ID, RECIPE_ID, ORDER_ID, DESCRIPTION, IMAGE_URL, VIDEO_URL) VALUES (24, 4, 6, 'Arzuya göre ılık, fakat daha da lezzetli olması için soğuk olarak servis edin. Afiyet olsun!', 'https://cdn.yemek.com/mncrop/600/315/uploads/2015/07/taze-fasule-adim-6.jpg', '' ) on conflict (ID) do nothing;

INSERT INTO public.COOKING_INSTRUCTIONS (ID, RECIPE_ID, ORDER_ID, DESCRIPTION, IMAGE_URL, VIDEO_URL) VALUES (25, 5, 1, 'Kişi sayısına göre azaltıp arttırabileceğiniz oranda sucuğu ince ince dilimleyin.', '', '' ) on conflict (ID) do nothing;
INSERT INTO public.COOKING_INSTRUCTIONS (ID, RECIPE_ID, ORDER_ID, DESCRIPTION, IMAGE_URL, VIDEO_URL) VALUES (26, 5, 2, 'Ocağa aldığınız yanmaz yapışmaz tabanlı yağsız tavaya sucuk dilimlerini yerleştirin.', '', '' ) on conflict (ID) do nothing;
INSERT INTO public.COOKING_INSTRUCTIONS (ID, RECIPE_ID, ORDER_ID, DESCRIPTION, IMAGE_URL, VIDEO_URL) VALUES (27, 5, 3, 'Aralarda ters çevirerek hafif bir renk alana ve yağını salana kadar sucukları orta ateşte pişirin.', '', '' ) on conflict (ID) do nothing;
INSERT INTO public.COOKING_INSTRUCTIONS (ID, RECIPE_ID, ORDER_ID, DESCRIPTION, IMAGE_URL, VIDEO_URL) VALUES (28, 5, 4, 'Kişi sayısına göre arttırabileceğiniz yumurtayı, sarısının dağılmaması için dikkatli bir şekilde bütün olarak sucuk dilimlerinin orta kısmına kırın.', '', '' ) on conflict (ID) do nothing;
INSERT INTO public.COOKING_INSTRUCTIONS (ID, RECIPE_ID, ORDER_ID, DESCRIPTION, IMAGE_URL, VIDEO_URL) VALUES (29, 5, 5, 'Yumurta beyazını aralarda dağıtıp, spatulanın ucuyla hafif bir şekilde karıştırarak kısık ateşte pişirin.', '', '' ) on conflict (ID) do nothing;
INSERT INTO public.COOKING_INSTRUCTIONS (ID, RECIPE_ID, ORDER_ID, DESCRIPTION, IMAGE_URL, VIDEO_URL) VALUES (30, 5, 6, 'Akı, şeffaf bir renkten beyaza doğru dönen sahanda sucuklu yumurtayı sıcak olarak bekletmeden sevdiklerinizle paylaşın.', '', '' ) on conflict (ID) do nothing;

INSERT INTO public.COOKING_INSTRUCTIONS (ID, RECIPE_ID, ORDER_ID, DESCRIPTION, IMAGE_URL, VIDEO_URL) VALUES (31, 6, 1, 'İç harcını hazırlamak için ayıklamış olduğunuz maydanoz yapraklarını incecik kıyın. Kıyılmış maydanoz, lor peyniri ve tuzu bir çatal yardımıyla ezerek karıştırın.', 'https://cdn.yemek.com/mncrop/600/315/uploads/2014/12/sigara-boregi-guncelleme-asama-1.jpg', '' ) on conflict (ID) do nothing;
INSERT INTO public.COOKING_INSTRUCTIONS (ID, RECIPE_ID, ORDER_ID, DESCRIPTION, IMAGE_URL, VIDEO_URL) VALUES (32, 6, 2, 'Yufkaları tezgaha serin ve her 1 yufkayı çeyrek ay olacak şekilde 4 eşit parçaya bölün.', 'https://cdn.yemek.com/mncrop/600/315/uploads/2014/12/sigara-boregi-guncelleme-asama-2.jpg', '' ) on conflict (ID) do nothing;
INSERT INTO public.COOKING_INSTRUCTIONS (ID, RECIPE_ID, ORDER_ID, DESCRIPTION, IMAGE_URL, VIDEO_URL) VALUES (33, 6, 3, 'Ardından bu çeyrek ay şekildeki yufkayı da 3 eşit parçaya kesin. 2 adet yufkadan toplamda 24 adet üçgen dilim yufka parçası elde etmiş olacaksınız.', 'https://cdn.yemek.com/mncrop/600/315/uploads/2014/12/sigara-boregi-guncelleme-asama-3.jpg', '' ) on conflict (ID) do nothing;
INSERT INTO public.COOKING_INSTRUCTIONS (ID, RECIPE_ID, ORDER_ID, DESCRIPTION, IMAGE_URL, VIDEO_URL) VALUES (34, 6, 4, 'Üçgen yufka parçalarından bir tanesini mutfak tezgahı üzerine alın. Geniş kısmına yayarak bir tatlı kaşığı kadar iç harçtan yerleştirin. Sağ ve sol kenarından içe doğru 1-2 cm katlayın.', 'https://cdn.yemek.com/mncrop/600/315/uploads/2014/12/sigara-boregi-guncelleme-asama-4.jpg', '' ) on conflict (ID) do nothing;
INSERT INTO public.COOKING_INSTRUCTIONS (ID, RECIPE_ID, ORDER_ID, DESCRIPTION, IMAGE_URL, VIDEO_URL) VALUES (35, 6, 5, 'Gevşek olmamasına özen göstererek yufkayı rulo halinde sarın fakat uç kısmını hafifçe açıkta bırakın.', 'https://cdn.yemek.com/mncrop/600/315/uploads/2014/12/sigara-boregi-guncelleme-asama-5.jpg', '' ) on conflict (ID) do nothing;
INSERT INTO public.COOKING_INSTRUCTIONS (ID, RECIPE_ID, ORDER_ID, DESCRIPTION, IMAGE_URL, VIDEO_URL) VALUES (36, 6, 6, 'Yufkanın uç kısmını hafifçe suya batırıp sarın, böylece yufka yağda pişerken açılmayacaktır. Sarma işlemini kalan tüm yufkalar için tekrar edin.', 'https://cdn.yemek.com/mncrop/600/315/uploads/2014/12/sigara-boregi-guncelleme-asama-6.jpg', '' ) on conflict (ID) do nothing;
INSERT INTO public.COOKING_INSTRUCTIONS (ID, RECIPE_ID, ORDER_ID, DESCRIPTION, IMAGE_URL, VIDEO_URL) VALUES (37, 6, 7, 'Ayçiçek yağını orta boyda derin bir tava veya kızartma tenceresinde iyice ısıtın. Her seferde 5-6 börek olmak üzere kızartmak için yağın içerisine alın, kızarırken hafifçe yuvarlayarak kontrol edin. Toplamda 6-7 dakika içerisinde her iki yönü de pişmiş olacaktır.', 'https://cdn.yemek.com/mncrop/600/315/uploads/2014/12/sigara-boregi-guncelleme-asama-7.jpg', '' ) on conflict (ID) do nothing;
INSERT INTO public.COOKING_INSTRUCTIONS (ID, RECIPE_ID, ORDER_ID, DESCRIPTION, IMAGE_URL, VIDEO_URL) VALUES (38, 6, 8, 'Kızaran börekleri maşayla alırken fazla yağını hafifçe silkin. Dilerseniz havlu kağıt üzerine alın ve sıcak olarak servis edin. Afiyet olsun!', 'https://cdn.yemek.com/mncrop/600/315/uploads/2014/12/sigara-boregi-guncelleme-asama-8.jpg', '' ) on conflict (ID) do nothing;

INSERT INTO public.COOKING_INSTRUCTIONS (ID, RECIPE_ID, ORDER_ID, DESCRIPTION, IMAGE_URL, VIDEO_URL) VALUES (39, 7, 1, 'Üzerlerini fırçaladığınız kızartmalık patatesleri bol suda yıkadıktan sonra kabuklarını incecik soyun.', '', '' ) on conflict (ID) do nothing;
INSERT INTO public.COOKING_INSTRUCTIONS (ID, RECIPE_ID, ORDER_ID, DESCRIPTION, IMAGE_URL, VIDEO_URL) VALUES (40, 7, 2, 'Büyük dikdörtgenler halinde kestiğiniz patatesleri, uzunlamasına doğrayıp parmak şeklinde patatesler hazırlayın.', '', '' ) on conflict (ID) do nothing;
INSERT INTO public.COOKING_INSTRUCTIONS (ID, RECIPE_ID, ORDER_ID, DESCRIPTION, IMAGE_URL, VIDEO_URL) VALUES (41, 7, 3, 'Patates dilimlerini nişastalarını bırakması için tuzlu ve soğuk (buzlu) suda bekletin.', '', '' ) on conflict (ID) do nothing;
INSERT INTO public.COOKING_INSTRUCTIONS (ID, RECIPE_ID, ORDER_ID, DESCRIPTION, IMAGE_URL, VIDEO_URL) VALUES (42, 7, 4, 'Kızartma yağını derin bir tava ya da tencereye alıp yavaş yavaş kızdırın.', '', '' ) on conflict (ID) do nothing;
INSERT INTO public.COOKING_INSTRUCTIONS (ID, RECIPE_ID, ORDER_ID, DESCRIPTION, IMAGE_URL, VIDEO_URL) VALUES (43, 7, 5, 'Suyunu süzüp kağıt havlu yardımıyla kuruladığınız patatesleri kısık ateşte, derin yağda 3-4 dakika kadar pişirin. Aralarda karıştırmayın ve kızartmayın. Pişirin.', '', '' ) on conflict (ID) do nothing;
INSERT INTO public.COOKING_INSTRUCTIONS (ID, RECIPE_ID, ORDER_ID, DESCRIPTION, IMAGE_URL, VIDEO_URL) VALUES (44, 7, 6, 'Yağını süzdürdüğünüz patatesleri kağıt havlu serili servis tabağına alın. Yağın altını kapatın. Patatesleri soğuması için bekletin. Arzuya göre bu esnada onları toz baharatlarla çeşnilendirin.', '', '' ) on conflict (ID) do nothing;
INSERT INTO public.COOKING_INSTRUCTIONS (ID, RECIPE_ID, ORDER_ID, DESCRIPTION, IMAGE_URL, VIDEO_URL) VALUES (45, 7, 7, 'Ön pişirme işleminden geçen ve soğuyan patatesleri bu sefer gerçekten kızdırdığınız sıvı yağda altın sarısı renk alana kadar kızartın. Tuz ilavesiyle son lezzetini kattıktan sonra çıtır çıtır sevdiklerinizle paylaşın.', '', '' ) on conflict (ID) do nothing;