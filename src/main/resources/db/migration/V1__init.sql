CREATE TABLE IF NOT EXISTS client (
    id SERIAL,
    nui VARCHAR (13) NOT NULL,
    fullname VARCHAR(100) NOT NULL,
    address VARCHAR(255),
    email VARCHAR(255),
    PRIMARY KEY (id),
    UNIQUE (nui)
    );

CREATE TABLE IF NOT EXISTS product (
    id SERIAL,
    description VARCHAR(100) NOT NULL,
    brand VARCHAR(255),
    price DECIMAL (7,2) DEFAULT 0.00,
    stock INT,
    PRIMARY KEY (id)
    );

CREATE TABLE IF NOT EXISTS invoice (
    id SERIAL,
    code VARCHAR(17) NOT NULL,
    create_at DATE,
    total DECIMAL (7,2),
    client_id INT,
    PRIMARY KEY (id),
    UNIQUE (code),
    FOREIGN KEY (client_id) REFERENCES client (id)
    );

CREATE TABLE IF NOT EXISTS detail (
    id SERIAL,
    quantity INT NOT NULL,
    price DECIMAL (7,2),
    subtotal DECIMAL (7,2) GENERATED ALWAYS AS (price*quantity) STORED,
    invoice_id INT,
    product_id INT,
    PRIMARY KEY (id),
    UNIQUE (invoice_id, product_id),
    FOREIGN KEY (invoice_id) REFERENCES invoice (id),
    FOREIGN KEY (product_id) REFERENCES product (id)
    );
