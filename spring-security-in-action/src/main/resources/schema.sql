-- H2 호환 + Spring Security 표준 스키마
CREATE TABLE IF NOT EXISTS users (
                                     username VARCHAR(50) PRIMARY KEY,
                                     password VARCHAR(100) NOT NULL,
                                     enabled BOOLEAN NOT NULL
);

CREATE TABLE IF NOT EXISTS authorities (
                                           username VARCHAR(50) NOT NULL,
                                           authority VARCHAR(50) NOT NULL,
                                           CONSTRAINT fk_auth_username FOREIGN KEY (username) REFERENCES users(username),
                                           CONSTRAINT pk_auth PRIMARY KEY (username, authority)
);