-- Drop tables in reverse order of dependencies to avoid foreign key constraints issues

DROP TABLE IF EXISTS product_list;
DROP TABLE IF EXISTS comments;
DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS child_category_list;
DROP TABLE IF EXISTS categories;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS order_status;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS permissions;


CREATE TABLE categories (
    id SERIAL PRIMARY KEY,
    title VARCHAR(50) UNIQUE NOT NULL,
    create_date DATE DEFAULT (CURRENT_DATE)
);

CREATE TABLE permissions (
    id SERIAL PRIMARY KEY,
    permission VARCHAR(20)
);

CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(20),
    lastname VARCHAR(50),
    firstname VARCHAR(50),
    middlename VARCHAR(50),
    phone VARCHAR(15),
    email VARCHAR(50),
    password VARCHAR(255),
    permission_id INTEGER DEFAULT 2,
    create_date DATE DEFAULT (CURRENT_DATE),
    FOREIGN KEY (permission_id) REFERENCES permissions(id)
);

CREATE TABLE products (
    id SERIAL PRIMARY KEY,
    category_id INTEGER,
    title VARCHAR(100),
    price NUMERIC(11, 2),
    producer VARCHAR(50),
    discount SMALLINT,
    amount SMALLINT,
    description VARCHAR(255),
    img_path VARCHAR(255),
	create_date DATE DEFAULT (CURRENT_DATE),
    FOREIGN KEY (category_id) REFERENCES categories(id)
);

CREATE TABLE order_status (
    id SERIAL PRIMARY KEY,
    title VARCHAR(50)
);

CREATE TABLE orders (
    id SERIAL PRIMARY KEY,
    lastname VARCHAR(50),
    firstname VARCHAR(50),
    middlename VARCHAR(50),
    phone VARCHAR(15),
    email VARCHAR(50),
    comment TEXT,
    order_status_id INTEGER,
    create_date DATE DEFAULT (CURRENT_DATE),
    FOREIGN KEY (order_status_id) REFERENCES order_status(id)
);

CREATE TABLE product_list (
    id SERIAL PRIMARY KEY,
    order_id INTEGER,
    product_id INTEGER,
    FOREIGN KEY (order_id) REFERENCES orders(id),
    FOREIGN KEY (product_id) REFERENCES products(id)
);

CREATE TABLE comments (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255),
    comment VARCHAR(255),
    rating SMALLINT,
    user_id INTEGER,
    product_id INTEGER,
    create_date DATE DEFAULT (CURRENT_DATE),
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (product_id) REFERENCES products(id)
);

CREATE TABLE child_category_list (
    id SERIAL PRIMARY KEY,
    parent_category_id INTEGER,
    child_category_id INTEGER,
    FOREIGN KEY (parent_category_id) REFERENCES categories(id),
    FOREIGN KEY (child_category_id) REFERENCES categories(id)
);