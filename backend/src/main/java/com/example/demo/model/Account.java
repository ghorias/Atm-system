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

    private BigDecimal getSaldo() {

        return balance;
    }

    public void setSaldo(BigDecimal saldo) {
        this.balance = saldo;
    }

    public void setKontonummer(String kontonummer) {
        this.accountNumber = kontonummer;
    }
}
