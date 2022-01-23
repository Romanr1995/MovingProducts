CREATE SEQUENCE prod_id AS integer INCREMENT 2 MINVALUE 1 MAXVALUE 100 NO CYCLE;
CREATE TABLE warehouses(id serial PRIMARY KEY, name varchar(50) NOT NULL UNIQUE);

CREATE TABLE products(id integer PRIMARY KEY DEFAULT nextval('prod_id'),
					  name varchar(50) NOT NULL,
					  weight numeric(5, 2) NOT NULL,
					  price numeric(5, 2) NOT NULL,
					  id_warehouse integer NOT NULL,
					  FOREIGN KEY (id_warehouse) REFERENCES warehouses(id) );