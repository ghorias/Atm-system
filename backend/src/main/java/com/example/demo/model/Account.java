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

    public BigDecimal getBalance() {

        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public void setAccountNumber (String accountNumber) {
        this.accountNumber = accountNumber;
    }
}
