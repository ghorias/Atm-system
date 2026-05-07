package com.example.demo.repository;

import com.example.demo.model.Account;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Ansvarar för att mappa en databasrad från tabellen 'accounts'
 * till ett Account-objekt i Java.
 *
 * Används av JdbcTemplate vid alla SELECT-frågor mot accounts-tabellen.
 * Varje kolumn i ResultSet mappas mot motsvarande fält i Account.
 */
public class AccountRowMapper implements RowMapper<Account> {

    /**
     * Mappar en rad i ResultSet till ett Account-objekt.
     *
     * @param rs     ResultSet från JDBC-frågan, pekar på aktuell rad
     * @param rowNum radnummer (används av JdbcTemplate internt)
     * @return ett Account-objekt populerat med data från databasen
     * @throws SQLException om ett databasfel uppstår vid läsning
     */
    @Override
    public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
        Account account = new Account();

        // Mappar kontonummer från kolumnen 'account_number'
        account.setAccountNumber(rs.getString("account_number"));

        // Mappar saldo från kolumnen 'balance' — BigDecimal för exakt decimalhantering
        account.setBalance(rs.getBigDecimal("balance"));

        // Mappar den BCrypt-hashade PIN-koden från kolumnen 'pin_hash'
        // Hashen används i AccountService för verifiering via encoder.matches()
        account.setPinHash(rs.getString("pin_hash"));

        return account;
    }
}

