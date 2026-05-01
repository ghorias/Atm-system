INSERT OR IGNORE INTO users (first_name, last_name, mail, address, personnummer)
VALUES ('Ali', 'Hassan', 'ali@example.com', 'Stockholm', 199001011234);


INSERT OR IGNORE INTO accounts (account_number, balance, user_id)
VALUES ('ACC1001', 5000.00, 199001011234);