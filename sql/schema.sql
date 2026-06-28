-- Cars table

CREATE TABLE cars (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    color VARCHAR(50) NOT NULL,
    brand VARCHAR(100) NOT NULL,
    price NUMERIC(10,2) NOT NULL CHECK (price > 0),
    model VARCHAR(100) NOT NULL,
    mileage INTEGER NOT NULL CHECK (mileage >= 0),
    manufacturing_year INTEGER NOT NULL
        CHECK (manufacturing_year BETWEEN 1990 AND 2026),
    fuel_type VARCHAR(20) NOT NULL
        CHECK (fuel_type IN ('Petrol', 'Diesel', 'CNG', 'EV', 'Hybrid'))
);


CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL,
    role VARCHAR(20) NOT NULL
);