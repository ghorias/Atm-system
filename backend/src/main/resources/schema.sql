PRAGMA foreign_keys = ON;

CREATE TABLE IF NOT EXISTS users (
                       user_id INTEGER PRIMARY KEY AUTOINCREMENT,
                       first_name TEXT NOT NULL,
                       last_name TEXT NOT NULL,
                       mail TEXT UNIQUE NOT NULL,
                       address TEXT,
                       personnummer INTEGER UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS accounts (
                          account_number TEXT PRIMARY KEY,
                          balance DECIMAL NOT NULL,
                          user_id INTEGER NOT NULL,
                          FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE IF NOT EXISTS transactions (
                              transaction_id INTEGER PRIMARY KEY AUTOINCREMENT,
                              account_number TEXT NOT NULL,
                              amount DECIMAL NOT NULL,
                              timestamp TEXT NOT NULL,
                              type TEXT NOT NULL,
                              FOREIGN KEY (account_number) REFERENCES accounts(account_number)
);