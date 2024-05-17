CREATE SEQUENCE brand_sequence;

CREATE TABLE t_brands (
    id BIGINT PRIMARY KEY,
    name VARCHAR NOT NULL ,
    description VARCHAR NOT NULL ,
    active BOOLEAN NOT NULL DEFAULT true
);