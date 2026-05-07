package com.example.demo.repository;

import com.example.demo.model.Account;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

/**
 * Databaslagret för konton (accounts-tabellen).
 *
 * Hanterar all SQL mot accounts-tabellen via JdbcTemplate.
 * Ingen affärslogik ska finnas här, det hör hemma i AccountService.
 */
@Repository
public class AccountRepository {

    /** JdbcTemplate används för att exekvera SQL-frågor mot databasen. */
    private final JdbcTemplate jdbcTemplate;

    /** RowMapper som omvandlar databasrader till Account-objekt. */
    private final AccountRowMapper rowMapper = new AccountRowMapper();

    /**
     * Konstruktorinjicering av JdbcTemplate (rekommenderat framför @Autowired).
     * @param jdbcTemplate Spring's JdbcTemplate
     */
    public AccountRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Hämtar ett konto baserat på kontonummer.
     *
     * @param accountNumber kontonumret att söka efter
     * @return Account-objekt om det hittas, annars null
     */
    public Account findByAccountNumber(String accountNumber) {
        String sql = "SELECT * FROM accounts WHERE account_number = ?";
        List<Account> result = jdbcTemplate.query(sql, rowMapper, accountNumber);
        return result.isEmpty() ? null : result.get(0);
    }

    /**
     * Uppdaterar saldot för ett konto i databasen.
     *
     * OBS: Tidigare version hade fel SQL (SELECT istället för UPDATE).
     * Korrigerat till en riktig UPDATE-sats.
     *
     * @param accountNumber kontonumret för det konto som ska uppdateras
     * @param balance       det nya saldot
     * @return antal påverkade rader (ska vara 1 vid lyckad uppdatering)
     */
    public int updateBalance(String accountNumber, BigDecimal balance) {
        String sql = "UPDATE accounts SET balance = ? WHERE account_number = ?";
        return jdbcTemplate.update(sql, balance, accountNumber);
    }

    /**
     * Skapar ett nytt konto i databasen.
     *
     * PIN-koden ska redan vara hashad med BCrypt när denna metod anropas —
     * det är AccountService som ansvarar för hashningen.
     *
     * @param accountNumber unikt kontonummer
     * @param balance       startbalans
     * @param userId        ägarens user_id (foreign key till users-tabellen)
     * @param pinHash       BCrypt-hashad PIN-kod
     * @return antal påverkade rader (ska vara 1 vid lyckad insättning)
     */
    public int createAccount(String accountNumber, BigDecimal balance, long userId, String pinHash) {
        String sql = "INSERT INTO accounts (account_number, balance, user_id, pin_hash) VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(sql, accountNumber, balance, userId, pinHash);
    }

    /**
     * Hämtar alla konton från databasen.
     * Används primärt för administratörsvy.
     *
     * @return lista med alla Account-objekt
     */
    public List<Account> findAll() {
        String sql = "SELECT * FROM accounts";
        return jdbcTemplate.query(sql, rowMapper);
    }
}
