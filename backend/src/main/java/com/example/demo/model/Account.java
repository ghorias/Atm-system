package com.example.demo.model;

import java.math.BigDecimal;

/**
 * Representerar ett bankkonto i systemet.
 *
 * Ett konto tillhör en användare (via user_id i databasen) och har
 * ett unikt kontonummer som affärsidentitet, ett saldo samt en
 * BCrypt-hashad PIN-kod för autentisering.
 */
public class Account {

    /** Unikt kontonummer — primärnyckel och affärsidentitet för kontot. */
    private String accountNumber;

    /** Kontobalans i kronor. Använder BigDecimal för exakt decimalhantering. */
    private BigDecimal balance;

    /**
     * BCrypt-hashad PIN-kod för kontot.
     * Sparas aldrig i klartext — varken i minnet längre än nödvändigt
     * eller i databasen. Jämförs alltid via encoder.matches().
     */
    private String pinHash;

    // -------------------------------------------------------------------------
    // Getters och Setters
    // -------------------------------------------------------------------------

    /**
     * Returnerar kontonumret.
     * @return kontonummer som sträng
     */
    public String getAccountNumber() {
        return accountNumber;
    }

    /**
     * Sätter kontonumret.
     * @param accountNumber kontonummer att tilldela
     */
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    /**
     * Returnerar det aktuella saldot.
     * @return saldo som BigDecimal
     */
    public BigDecimal getBalance() {
        return balance;
    }

    /**
     * Sätter saldot för kontot.
     * @param balance nytt saldo
     */
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    /**
     * Returnerar den BCrypt-hashade PIN-koden.
     * Används endast internt för verifiering — exponeras ej i API-svar.
     * @return hashad PIN som sträng
     */
    public String getPinHash() {
        return pinHash;
    }

    /**
     * Sätter den hashade PIN-koden.
     * OBS: Denna metod förväntar sig en redan hashad sträng.
     * Hasha PIN-koden i AccountService innan denna metod anropas.
     * @param pinHash BCrypt-hashad PIN
     */
    public void setPinHash(String pinHash) {
        this.pinHash = pinHash;
    }
}
