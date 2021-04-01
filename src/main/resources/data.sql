DROP TABLE IF EXISTS customer;
CREATE TABLE IF NOT EXISTS customer (
    id INT AUTO_INCREMENT PRIMARY KEY,
    full_name VARCHAR(64) NOT NULL,
    active_days_points INT NOT NULL,
    username VARCHAR(64) NOT NULL
);

INSERT INTO customer (full_name, username, active_days_points) VALUES ('Aliko Dangote', 'alikodangote@.com', 120);
INSERT INTO customer (full_name, username, active_days_points) VALUES ('Bill Gates', 'billgates@microsoft.com',775);
INSERT INTO customer (full_name, username, active_days_points) VALUES ('Elon Musk', 'elonmusk@tesla.com',1550);
INSERT INTO customer (full_name, username, active_days_points) VALUES ('Johann Le Roux', 'johannleroux@momentum.com',45);

DROP TABLE IF EXISTS product;
CREATE TABLE IF NOT EXISTS product (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(64) NOT NULL,
    code VARCHAR(32) NOT NULL,
    cost INT NOT NULL,
    UNIQUE KEY product_code_unique_key (code)
);

INSERT INTO product (name, code, cost) VALUES ('Iron Man Suit', 'VNS7zvTStw', 500);
INSERT INTO product (name, code, cost) VALUES ('Batman Voice Changer', 'XSzPjaPhDF', 50);
INSERT INTO product (name, code, cost) VALUES ('Hammer of Thor', 'QHuWQMHKiY', 375);
INSERT INTO product (name, code, cost) VALUES ('Shoes of The Flash', 'gA6GNyC2YM', 250);
INSERT INTO product (name, code, cost) VALUES ('Kryptonite', 'WVEFkBg2UF', 1750);
INSERT INTO product (name, code, cost) VALUES ('Web of Spider-Man', 'apZokkdgrf', 10);
INSERT INTO product (name, code, cost) VALUES ('Vibranium claws', 'DR37HYkw86', 1000);