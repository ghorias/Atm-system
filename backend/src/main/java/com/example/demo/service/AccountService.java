package com.example.demo.service;

import com.example.demo.model.Account;
import com.example.demo.model.Transaction;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.TransactionRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Affärslogik för konton och transaktioner.
 *
 * Denna klass hanterar all logik kring insättningar, uttag, saldokontroll
 * samt PIN-verifiering och kontoregistrering med BCrypt-hashning.
 *
 * Designprincip: ServiceLagret känner till affärsreglerna (t.ex. "saldo får
 * inte bli negativt"), medan Repository-lagret bara pratar med databasen.
 */
@Service
public class AccountService {

    /** Repository för databasoperationer mot accounts-tabellen. */
    private final AccountRepository accountRepository;

    /** Repository för databasoperationer mot transactions-tabellen. */
    private final TransactionRepository transactionRepository;

    /**
     * BCryptPasswordEncoder används för att hasha och verifiera PIN-koder.
     * Instansen injiceras från SecurityConfig som en Spring-bean.
     * BCrypt är en envägsfunktion — en PIN kan hashas men aldrig dekrypteras.
     */
    private final BCryptPasswordEncoder passwordEncoder;

    /**
     * Konstruktorinjicering av beroenden (rekommenderat framför @Autowired).
     *
     * @param accountRepository     repository för konton
     * @param transactionRepository repository för transaktioner
     * @param passwordEncoder       BCrypt-encoder för PIN-hashning
     */
    public AccountService(AccountRepository accountRepository,
                          TransactionRepository transactionRepository,
                          BCryptPasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // =========================================================================
    // PIN-hantering
    // =========================================================================

    /**
     * Registrerar ett nytt konto med en BCrypt-hashad PIN-kod.
     *
     * PIN-koden hashas här i servicen innan den skickas vidare till
     * repositoryt — aldrig tvärtom. Klartext-PIN lagras aldrig.
     *
     * @param accountNumber unikt kontonummer för det nya kontot
     * @param balance       startbalans för kontot
     * @param userId        ägarens user_id (måste finnas i users-tabellen)
     * @param rawPin        PIN-koden i klartext — hashas omedelbart
     * @throws IllegalArgumentException om kontonumret redan existerar
     */
    public void registerAccount(String accountNumber, BigDecimal balance, long userId, String rawPin) {
        // Kontrollera att kontonumret inte redan är taget
        if (accountRepository.findByAccountNumber(accountNumber) != null) {
            throw new IllegalArgumentException("Kontonummer redan registrerat: " + accountNumber);
        }

        // Hasha PIN-koden med BCrypt innan den sparas i databasen
        // encode() lägger automatiskt till ett unikt salt — samma PIN ger alltid olika hash
        String pinHash = passwordEncoder.encode(rawPin);

        // Spara det nya kontot med den hashade PIN-koden
        accountRepository.createAccount(accountNumber, balance, userId, pinHash);
    }

    /**
     * Verifierar att en angiven PIN-kod stämmer för ett givet konto.
     *
     * Använder BCrypt's matches()-metod som jämför klartext-PIN med
     * den lagrade hashen utan att dekryptera — BCrypt är en envägsfunktion.
     *
     * @param accountNumber kontonumret att verifiera PIN för
     * @param rawPin        PIN-koden i klartext att jämföra
     * @return true om PIN stämmer, false om fel PIN eller kontot inte finns
     */
    public boolean verifyPin(String accountNumber, String rawPin) {
        Account account = accountRepository.findByAccountNumber(accountNumber);

        // Returnerar false om kontot inte hittas, istället för att kasta exception
        // — undviker att avslöja om kontonumret existerar (säkerhetsskäl)
        if (account == null) {
            return false;
        }

        // matches() jämför klartext-PIN med lagrad BCrypt-hash
        // Returnerar true om de matchar, annars false
        return passwordEncoder.matches(rawPin, account.getPinHash());
    }

    // =========================================================================
    // Transaktioner
    // =========================================================================

    /**
     * Genomför en insättning på ett konto och loggar transaktionen.
     *
     * @param accountNumber kontonumret att sätta in på
     * @param amount        beloppet att sätta in (måste vara positivt)
     * @return det nya saldot efter insättningen
     * @throws IllegalArgumentException om kontot inte hittas
     */
    public BigDecimal deposit(String accountNumber, BigDecimal amount) {
        Account account = accountRepository.findByAccountNumber(accountNumber);

        if (account == null) {
            throw new IllegalArgumentException("Kontot hittades inte: " + accountNumber);
        }

        // Beräkna nytt saldo och uppdatera i databasen
        BigDecimal newBalance = account.getBalance().add(amount);
        accountRepository.updateBalance(accountNumber, newBalance);

        // Logga transaktionen i transactions-tabellen
        Transaction transaction = new Transaction();
        transaction.setAccountNumber(accountNumber);
        transaction.setAmount(amount);
        transaction.setTimeStamp(LocalDateTime.now());
        transaction.setType(Transaction.TransactionType.DEPOSIT);
        transactionRepository.save(transaction);

        return newBalance;
    }

    /**
     * Genomför ett uttag från ett konto om saldot är tillräckligt.
     *
     * @param accountNumber kontonumret att ta ut från
     * @param amount        beloppet att ta ut (måste vara positivt)
     * @return det nya saldot efter uttaget
     * @throws IllegalArgumentException om kontot inte hittas eller saldo är otillräckligt
     */
    public BigDecimal withdraw(String accountNumber, BigDecimal amount) {
        Account account = accountRepository.findByAccountNumber(accountNumber);

        if (account == null) {
            throw new IllegalArgumentException("Kontot hittades inte: " + accountNumber);
        }

        BigDecimal currentBalance = account.getBalance();

        // Kontrollera att det finns tillräckligt med pengar för uttaget
        if (amount.compareTo(currentBalance) > 0) {
            throw new IllegalArgumentException("Otillräckligt saldo. Tillgängligt: " + currentBalance);
        }

        // Beräkna nytt saldo och uppdatera i databasen
        BigDecimal newBalance = currentBalance.subtract(amount);
        accountRepository.updateBalance(accountNumber, newBalance);

        // Logga transaktionen i transactions-tabellen
        Transaction transaction = new Transaction();
        transaction.setAccountNumber(accountNumber);
        transaction.setAmount(amount);
        transaction.setTimeStamp(LocalDateTime.now());
        transaction.setType(Transaction.TransactionType.WITHDRAWAL);
        transactionRepository.save(transaction);

        return newBalance;
    }

    /**
     * Hämtar aktuellt saldo för ett konto.
     *
     * @param accountNumber kontonumret att hämta saldo för
     * @return saldo som BigDecimal
     * @throws IllegalArgumentException om kontot inte hittas
     */
    public BigDecimal showBalance(String accountNumber) {
        Account account = accountRepository.findByAccountNumber(accountNumber);

        if (account == null) {
            throw new IllegalArgumentException("Kontot hittades inte: " + accountNumber);
        }

        return account.getBalance();
    }
}

