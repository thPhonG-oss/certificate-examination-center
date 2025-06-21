CREATE TABLE [user] (
    id INT PRIMARY KEY IDENTITY(1,1),
    username VARCHAR(50),
    password VARCHAR(255) NOT NULL,
    email NVARCHAR(100) NOT NULL UNIQUE,
    phone_number NVARCHAR(15),
    full_name NVARCHAR(100),
    gender VARCHAR(1) CHECK ( gender IN ('M', 'F') ),
    dob DATE,
    address NVARCHAR(255),
    enabled BIT NOT NULL DEFAULT 1
);

CREATE TABLE role (
    id INT PRIMARY KEY IDENTITY(1,1),
    name NVARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE user_role (
    user_id INT NOT NULL,
    role_id INT NOT NULL,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES [user](id) ON DELETE CASCADE,
    FOREIGN KEY (role_id) REFERENCES role(id) ON DELETE CASCADE
);