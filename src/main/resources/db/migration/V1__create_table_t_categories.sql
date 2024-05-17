CREATE SEQUENCE category_sequence;

CREATE TABLE t_categories (
    id BIGINT PRIMARY KEY,
    name VARCHAR NOT NULL ,
    description VARCHAR NOT NULL ,
    active BOOLEAN NOT NULL DEFAULT true
);