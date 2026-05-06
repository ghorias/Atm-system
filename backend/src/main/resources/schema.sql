-- Aktivera foreign key-stöd i SQLite (måste göras per anslutning)
PRAGMA foreign_keys = ON;
 
-- Skapar tabellen för användare om den inte redan existerar
CREATE TABLE IF NOT EXISTS users (
                                     user_id       INTEGER PRIMARY KEY AUTOINCREMENT,
                                     first_name    TEXT    NOT NULL,
                                     last_name     TEXT    NOT NULL,
                                     mail          TEXT    UNIQUE NOT NULL,
                                     address       TEXT,
                                     personnummer  INTEGER UNIQUE NOT NULL
);

-- Skapar tabellen för konton om den inte redan existerar
-- pin_hash lagrar en BCrypt-hashad PIN-kod (aldrig klartextpin)
-- BCrypt-hashar är alltid 60 tecken, TEXT i SQLite är tillräckligt
CREATE TABLE IF NOT EXISTS accounts (
                                        account_number TEXT    PRIMARY KEY,
                                        balance        DECIMAL NOT NULL,
                                        user_id        INTEGER NOT NULL,
                                        pin_hash       TEXT    NOT NULL,
                                        FOREIGN KEY (user_id) REFERENCES users(user_id)
    );

-- Skapar tabellen för transaktioner om den inte redan existerar
-- type är antingen 'DEPOSIT' eller 'WITHDRAWAL' (matchar enum i Transaction.java)
CREATE TABLE IF NOT EXISTS transactions (
                                            transaction_id INTEGER PRIMARY KEY AUTOINCREMENT,
                                            account_number TEXT    NOT NULL,
                                            amount         DECIMAL NOT NULL,
                                            timestamp      TEXT    NOT NULL,
                                            type           TEXT    NOT NULL,
                                            FOREIGN KEY (account_number) REFERENCES accounts(account_number)
    );
