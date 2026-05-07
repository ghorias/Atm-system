
-- Testdata för utveckling och manuell testning
-- PIN-koderna är hashade med BCrypt (kostnadsfaktor 10) — sparas aldrig i klartext

-- Testanvändare 1
INSERT OR IGNORE INTO users (first_name, last_name, mail, address, personnummer)
VALUES ('Ali', 'Hassan', 'ali@example.com', 'Stockholm', 199001011234);

-- Testanvändare 2
INSERT OR IGNORE INTO users (first_name, last_name, mail, address, personnummer)
VALUES ('Sara', 'Lindgren', 'sara@example.com', 'Göteborg', 199505052345);

-- Konto för Ali — PIN: 1234
-- Hash genererad med BCrypt kostnadsfaktor 10
INSERT OR IGNORE INTO accounts (account_number, balance, user_id, pin_hash)
VALUES (
    'ACC1001',
    5000.00,
    1,
    '$2b$10$VQ/ZzoIgyDmKBQW7VF2tbOeezBK.qVXDc.lq7zLHnkZEWlVu30Mbi'
);

-- Konto för Sara — PIN: 5678
-- Hash genererad med BCrypt kostnadsfaktor 10
INSERT OR IGNORE INTO accounts (account_number, balance, user_id, pin_hash)
VALUES (
    'ACC1002',
    12000.00,
    2,
    '$2b$10$Xae77HTTTZ8UjW7gDjfCZOi/.PeFgsucASOb7nASS/lsnSwlnypi2'
);

-- Extra testkonto för Ali — PIN: 0000
-- Används för att testa att en användare kan ha flera konton
INSERT OR IGNORE INTO accounts (account_number, balance, user_id, pin_hash)
VALUES (
    'ACC1003',
    250.00,
    1,
    '$2b$10$eXm651Diw/RLQJm2vwKazOOoeJ0TjXTGrYcyE/xggASBHzCQBlvNu'
);

