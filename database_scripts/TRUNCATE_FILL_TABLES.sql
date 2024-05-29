ALTER SEQUENCE products_id_seq RESTART WITH 1;
ALTER SEQUENCE product_list_id_seq RESTART WITH 1;
ALTER SEQUENCE comments_id_seq RESTART WITH 1;
ALTER SEQUENCE child_category_list_id_seq RESTART WITH 1;
ALTER SEQUENCE categories_id_seq RESTART WITH 1;
ALTER SEQUENCE order_status_id_seq RESTART WITH 1;
ALTER SEQUENCE permissions_id_seq RESTART WITH 1;

TRUNCATE TABLE products CASCADE;
TRUNCATE TABLE product_list CASCADE;
TRUNCATE TABLE comments CASCADE;
TRUNCATE TABLE child_category_list CASCADE;
TRUNCATE TABLE categories CASCADE;
TRUNCATE TABLE orders CASCADE;
TRUNCATE TABLE order_status CASCADE;
TRUNCATE TABLE users CASCADE;
TRUNCATE TABLE permissions CASCADE;

-- Заповнення таблиці permission
INSERT INTO permissions (permission) VALUES
('admin'),
('user');

-- Заповнення таблиці users
INSERT INTO users (username, lastname, firstname, middlename, phone, email, password, permission_id, create_date) VALUES
('ALexus', 'Куликов', 'Олексій', 'Олександрович', '1234567890', 'admin@gmail.com', '$2a$10$037zn8WwEkNfKTvZflMFm.2tbwGQRQew26MYTQe0pH015Z6St9Ksi', 1, default),
('User', 'UserLastname', 'UserFirstname', 'UserMiddlename', '0987654321', 'user@gmail.com', '$2a$10$ZpU9H1eYIaScWOSYyK7frOxhv.fy7FzkTVVi.fF3V3BHiU7E7EIFS', 2, default);

-- Заповнення таблиці order_status
INSERT INTO order_status (title) VALUES
('Опрацювання'),
('Відправлено'),
('Доставлено');

-- Заповнення таблиці categories
INSERT INTO categories (title, create_date) VALUES
('Комп`ютери і ноутбуки', DEFAULT), 	--1
	('Комп`ютери', DEFAULT),			--2
	('Ноутбуки', DEFAULT),				--3
('Телефони і планшети', DEFAULT),		--4
	('Телефони', DEFAULT),				--5
	('Планшети', DEFAULT),				--6
('Побутова техніка', DEFAULT),			--7
	('Пральні машини', DEFAULT),		--8
	('Мікрохвильові печі', DEFAULT),	--9
('Комплектуючі до ПК', DEFAULT),		--10
	('Відеокарти', DEFAULT),			--11
	('Процесори', DEFAULT),		    	--12
('Аксесуари', DEFAULT),			    	--13
	('Навушники', DEFAULT),		    	--14
	('Годинники', DEFAULT);		    	--15

-- Заповнення таблиці child_category_list
INSERT INTO child_category_list (parent_category_id, child_category_id) VALUES
(1, 2),
(1, 3),
(4, 5),
(4, 6),
(7, 8),
(7, 9),
(10, 11),
(10, 12),
(13, 14),
(13, 15);

-- Заповнення таблиці products
INSERT INTO products (category_id, title, price, producer, discount, amount, description, img_path, create_date) VALUES
(5, 'Смартфон', 700, 'IPhone', 10, 100, 'IPhone 15 Pro Max', 'smartphone.png', DEFAULT),
(6, 'Планшет', 500, 'IPad', 85, 100, 'Lenovo tablet 3. Має дефект - на боковій кромці подряпина', 'tablet.png', DEFAULT),
(3, 'Ноутбук', 1000, 'Lenovo', 0, 200, 'Notebook Lenovo Ideapad 5', 'notebook.png', DEFAULT),
(2, 'Комп`ютер', 1900, 'HyperX', 20, 150, 'HyperPC Last Generation', 'pc.png', DEFAULT),
(8, 'Пральна машина', 650, 'Bosch', 5, 13, 'Bosch XN1340. Пральна машина з прямим приводом', 'washing-machine.png', DEFAULT),
(9, 'Мікрохвильова піч', 400, 'LG', 0, 20, 'LG microwave oven', 'microwave.png', DEFAULT),
(11, 'Відеокарта', 420, 'Nvidia', 0, 20, 'Graphics card. RTX3060TI', 'graphics-card.png', DEFAULT),
(12, 'Процесор', 312, 'AMD', 0, 20, 'AMD Ryzen5 5800X', 'processor.png', DEFAULT),
(14, 'Навушники', 50, 'Sony', 0, 20, 'Sony micro headset', 'headset.png', DEFAULT),
(15, 'Годинники', 130, 'Xiaomi', 9, 20, 'Xiaomi watch', 'watch.png', DEFAULT);

-- Заповнення таблиці orders
INSERT INTO orders (lastname, firstname, middlename, phone, email, comment, order_status_id, create_date) VALUES
('Doe', 'John', 'A', '1234567890', 'jdoe@example.com', 'Please deliver after 5 PM', 1, DEFAULT),
('Smith', 'Alice', 'B', '0987654321', 'asmith@example.com', 'Leave at the front desk', 2, DEFAULT);

-- Заповнення таблиці product_list
INSERT INTO product_list (order_id, product_id) VALUES
(1, 1),
(1, 2),
(2, 3);

-- Заповнення таблиці comments
INSERT INTO comments (title, comment, rating, user_id, product_id, create_date) VALUES
('Great Phone', 'This smartphone is amazing!', 5, 1, 7, DEFAULT),
('Interesting Read', 'I enjoyed this book a lot.', 4, 2, 4, DEFAULT),
('Good Quality', 'The T-shirt is very comfortable.', 4, 1, 8, DEFAULT);
