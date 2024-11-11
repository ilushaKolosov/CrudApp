CREATE SEQUENCE products_id_seq;

CREATE TABLE products
(
    id          INTEGER        NOT NULL DEFAULT nextval('products_id_seq') PRIMARY KEY,
    name        VARCHAR(255)   NOT NULL,
    description TEXT,
    price       DECIMAL(10, 2) NOT NULL,
    in_stock    BOOLEAN        NOT NULL
);

ALTER SEQUENCE products_id_seq RESTART WITH 1;