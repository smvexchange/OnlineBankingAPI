CREATE TABLE clients(
    id identity primary key,
    balance numeric not null
);

INSERT INTO clients VALUES (1, 5000.34);
INSERT INTO clients VALUES (2, 0.00);
INSERT INTO clients VALUES (3, 4500.54);
INSERT INTO clients VALUES (4, 2345.87);
INSERT INTO clients VALUES (5, 11.123);
