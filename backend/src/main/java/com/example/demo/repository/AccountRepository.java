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
// This method retrieves an Account object from the database based on the provided account number (accountNumber).
      public Account findByAccountNumber(String accountNumber) {
        String sql = "SELECT * FROM accounts WHERE kontonummer = ?";
        List<Account> result = jdbcTemplate.query(sql, rowMapper, accountNumber);
        return result.isEmpty() ? null : result.get(0);
    }
// This method updates the balance (balance) of an account in the database based on the provided account number (kontonummer) and new balance (balance).
    public int updateBalance(String accountNumber, BigDecimal balance) {
        String sql = "UPDATE accounts SET balance = ? WHERE accountNumber = ?";
        return jdbcTemplate.update(sql, balance, accountNumber);
    }
// This method retrieves a list of all Account objects from the database.
    public List<Account> findAll() {
        String sql = "SELECT * FROM accounts";
        return jdbcTemplate.query(sql, rowMapper);
    }
}