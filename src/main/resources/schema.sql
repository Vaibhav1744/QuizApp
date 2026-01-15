CREATE TABLE quiz_question (
    id INT AUTO_INCREMENT PRIMARY KEY,
    question VARCHAR(255),
    option_a VARCHAR(100),
    option_b VARCHAR(100),
    option_c VARCHAR(100),
    option_d VARCHAR(100),
    correct_option CHAR(1)
);

CREATE TABLE quiz_result (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(100),
    score INT,
    total INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
