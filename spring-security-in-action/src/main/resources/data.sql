-- users 먼저, 그 다음 authorities. 스키마 접두어/백틱 제거
INSERT INTO users (username, password, enabled) VALUES ('john', '12345', TRUE);
INSERT INTO authorities (username, authority) VALUES ('john', 'write');