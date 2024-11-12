CREATE SEQUENCE product_sales_id_seq;

CREATE TABLE product_sales
(
    id              INTEGER        NOT NULL DEFAULT nextval('product_sales_id_seq') PRIMARY KEY,
    document_name   VARCHAR(255)   NOT NULL,
    product_id      INTEGER        NOT NULL REFERENCES products(id) ON DELETE CASCADE,
    quantity        INTEGER        NOT NULL CHECK (quantity > 0),
    purchase_price  DECIMAL(10, 2) NOT NULL CHECK (purchase_price >= 0)
);

ALTER SEQUENCE product_sales_id_seq RESTART WITH 1;