package com.example.demo.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Account;

@Repository
public class AccountRepository {

    private final JdbcTemplate jdbcTemplate;
    private final AccountRowMapper rowMapper = new AccountRowMapper();

    public AccountRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
// This method retrieves an Account object from the database based on the provided account number (kontonummer).
      public Account findByKontonummer(String kontonummer) {
        String sql = "SELECT * FROM accounts WHERE kontonummer = ?";
        List<Account> result = jdbcTemplate.query(sql, rowMapper, kontonummer);
        return result.isEmpty() ? null : result.get(0);
    }
// This method updates the balance (saldo) of an account in the database based on the provided account number (kontonummer) and new balance (saldo).
    public int updateSaldo(String kontonummer, BigDecimal saldo) {
        String sql = "UPDATE accounts SET saldo = ? WHERE kontonummer = ?";
        return jdbcTemplate.update(sql, saldo, kontonummer);
    }
// This method retrieves a list of all Account objects from the database.
    public List<Account> findAll() {
        String sql = "SELECT * FROM accounts";
        return jdbcTemplate.query(sql, rowMapper);
    }
}