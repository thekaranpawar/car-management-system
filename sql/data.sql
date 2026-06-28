-- Insert sample data into cars table

INSERT INTO cars (name, color, brand, price, model, mileage, manufacturing_year, fuel_type)
VALUES
('3 Series', 'Black', 'BMW', 8300000.0, '330Li M Sport', 16, 2024, 'Petrol'),
('A4', 'Grey', 'Audi', 5200000.0, 'Tech', 17, 2025, 'EV'),
('Creta', 'White', 'Hyundai', 1500000.0, 'SX', 18, 2023, 'Diesel'),
('Thar', 'Black', 'Mahindra', 1800000.0, 'LX', 15, 2024, 'Diesel');


INSERT INTO users (username, password, role)
VALUES
('admin', 'admin123', 'ADMIN'),
('user1', 'user123', 'USER');