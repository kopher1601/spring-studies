-- H2 호환 + Spring Security 표준 스키마
CREATE TABLE IF NOT EXISTS users
(
    id        INT         NOT NULL AUTO_INCREMENT PRIMARY KEY,
    username  VARCHAR(45) NOT NULL,
    password  TEXT        NOT NULL,
    algorithm VARCHAR(45) NOT NULL
);

CREATE TABLE IF NOT EXISTS authority
(
    id    INT         NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name  VARCHAR(45) NOT NULL,
    users INT         NOT NULL
);

CREATE TABLE IF NOT EXISTS product
(
    id       INT         NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name     VARCHAR(45) NOT NULL,
    price    VARCHAR(45) NOT NULL,
    currency VARCHAR(45) NOT NULL
);
