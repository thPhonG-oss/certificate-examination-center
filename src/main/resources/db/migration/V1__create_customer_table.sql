CREATE TABLE customer (
    id INT IDENTITY(1,1) PRIMARY KEY,
    customer_name NVARCHAR(100) NOT NULL,
    customer_email NVARCHAR(100) NOT NULL UNIQUE,
    customer_phone VARCHAR(100) NOT NULL,
    customer_address NVARCHAR(255),
    customer_citizen_id VARCHAR(12) NOT NULL UNIQUE,
    customer_dob DATE NOT NULL,
    customer_type VARCHAR(20) NOT NULL CHECK (customer_type IN ('individual', 'business'))
)