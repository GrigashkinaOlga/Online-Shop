INSERT INTO categories (name) VALUES ('Face'), ('Body'), ('Hair'), ('Other');

insert into products (category_id, name, price, stock, description, image_file)
values (1, 'Помада', 350.00, 80, 'Лучший вау эффект', 'pomada.png'),
       (1, 'Тени', 420.00, 70, 'Роскошный взгляд', 'teny.png'),
       (1, 'Пудра', 620.00, 3, 'Для идеальной тебя', 'pudra.png'),
       (1, 'Тушь', 600.00, 4, 'Дополнительная изюминка', 'tush.png'),
       (2, 'Гель для тела', 340.50, 0, 'Невероятный аромат для нее', 'gel.png'),
       (2, 'Крем для тела', 620.00, 22, 'Почувствуй на себе кожу младенца', 'krem.png'),
       (2, 'Скраб для тела', 230.10, 2, 'Скрой свои недостатки', 'scrab.png'),
       (3, 'Шампунь для волос', 820.00, 6, 'Полный объем', 'shampuny.png'),
       (3, 'Бальзам для волос', 920.00, 4, 'Шикарные волосы', 'balzam.png'),
       (3, 'Лак для волос', 400.00, 11, 'Великолепная укладка', 'lak.png'),
       (3, 'Сухой шампунь', 580.00, 3, 'Сэкономь свое время', 'suhsham.png'),
       (4, 'Воск', 200.00, 3, 'Для депиляции', 'vosk.png'),
       (4, 'Салфетки', 30.00, 1, 'Чистые руки', 'salf.png');
