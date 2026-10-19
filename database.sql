CREATE DATABASE IF NOT EXISTS product_management CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE product_management;
CREATE TABLE IF NOT EXISTS products (
 id INT PRIMARY KEY AUTO_INCREMENT,
 name VARCHAR(255) NOT NULL,
 price DECIMAL(12,2) NOT NULL,
 quantity INT NOT NULL,
 CONSTRAINT chk_price CHECK (price >= 0),
 CONSTRAINT chk_quantity CHECK (quantity >= 0)
);
INSERT INTO products(name,price,quantity) VALUES
('Laptop Acer Nitro 5',18500000.00,5),
('Chuot khong day',250000.00,20),
('Ban phim co',850000.00,12);