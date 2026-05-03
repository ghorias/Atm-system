package com.example.demo.repository;

import com.example.demo.model.Transaction;
import com.example.demo.model.Transaction.TransactionType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class TransactionRepository {

    private final JdbcTemplate jdbcTemplate;

    public TransactionRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Transaction> rowMapper = (rs, rowNum) -> {
        Transaction t = new Transaction();
        t.setTransactionId(rs.getLong("transaction_id"));
        t.setAccountNumber(rs.getString("account_number"));
        t.setAmount(rs.getBigDecimal("amount"));
        t.setTimeStamp(LocalDateTime.parse(rs.getString("timestamp")));
        t.setType(TransactionType.valueOf(rs.getString("type")));
        return t;
    };

    // Spara en ny transaktion (kallas efter deposit/withdraw i AccountService)
    public void save(Transaction transaction) {
        String sql = "INSERT INTO transactions (account_number, amount, timestamp, type) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                transaction.getAccountNumber(),
                transaction.getAmount(),
                transaction.getTimeStamp().toString(),
                transaction.getType().name()
        );
    }

    // Hämta alla transaktioner för ett specifikt konto
    public List<Transaction> findByAccountNumber(String accountNumber) {
        String sql = "SELECT * FROM transactions WHERE account_number = ? ORDER BY timestamp DESC";
        return jdbcTemplate.query(sql, rowMapper, accountNumber);
    }

    // Hämta alla transaktioner (för admin-vy)
    public List<Transaction> findAll() {
        String sql = "SELECT * FROM transactions ORDER BY timestamp DESC";
        return jdbcTemplate.query(sql, rowMapper);
    }
}