package com.example.demo.repository;
import com.example.demo.model.Account;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
// This class is responsible for mapping the result set from the database to an Account object.
public class AccountRowMapper implements RowMapper<Account> {

    @Override
    public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
        Account account = new Account(); // Create a new Account object

<<<<<<< HEAD
        account.setaccountnumber(rs.getString("accountnumber")); // Set the account number from the result set
        account.setBalance(rs.getBigDecimal("balance")); // Set the balance from the result set
=======
        account.setKontonummer(rs.getString("kontonummer")); // Set the account number from the result set
        account.setBalance(rs.getBigDecimal("saldo")); // Set the balance from the result set
>>>>>>> fac97bd80d502894dcfda10beb77587c854de2b2

        return account;
    }
}