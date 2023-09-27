CREATE TABLE users (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    name VARCHAR(255),
    year_of_birth INT,
    phone_number VARCHAR(20) UNIQUE,
    second_phone_number VARCHAR(20),
    creation_date TIMESTAMP
);