package com.example.demo.model;
import java.math.BigDecimal;




public class Account {
    //field

    private String accountNumber;//affärsidentitet är kontonummer
    private BigDecimal balance;


    //Boilerplate
    //getters
    public String getAccountNumber() {

        return accountNumber;
    }

    private BigDecimal getBalance() {

        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public void setKontonummer(String kontonummer) {
        this.accountNumber = kontonummer;
    }
}
