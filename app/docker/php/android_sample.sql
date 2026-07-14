CREATE TABLE tblusers (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50),
    last_name VARCHAR(50),
    first_name VARCHAR(50),
    middle_name VARCHAR(50),
    email VARCHAR(100),
    password VARCHAR(255),
    photo VARCHAR(150),
    date_created TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);