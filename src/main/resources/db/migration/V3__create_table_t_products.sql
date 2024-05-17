CREATE SEQUENCE product_sequence;

CREATE TABLE t_products (
    id INTEGER PRIMARY KEY,
    name VARCHAR NOT NULL ,
    sku VARCHAR NOT NULL ,
    price DECIMAL NOT NULL,
    category_id INTEGER REFERENCES t_categories(id),
    brand_id INTEGER REFERENCES t_brands(id)
);