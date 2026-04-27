package com.example.demo.service;
import java.math.BigDecimal;

import com.example.demo.model.Account;
import com.example.demo.repository.AccountRepository;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    //variable name could change "findByAccountNumber"
    public BigDecimal deposit(String accountNumber, BigDecimal amount){
        //This gives us acces to the information of the account, stored in variable "account"
        Account account = accountRepository.findByAccountNumber(accountNumber);

        //Here is where the calculation is happening, getting the balance from set account doing the transaction and adding
        //the deposit amount to the account balance. Everything is stored in the newBalance Variable
        BigDecimal newBalance = account.getBalance().add(amount);

        account.setBalance(newBalance);

        //Here we save the new balance.
        accountRepository.update(account);

       return newBalance;
    }


    public BigDecimal withdraw(String accountNumber, BigDecimal amount){
        Account account = accountRepository.findByAccountNumber(accountNumber);
        BigDecimal newBalance;
        BigDecimal balance = accountRepository.findByAccountNumber(accountNumber).getBalance();


        if(amount.compareTo(balance) > 0){
            throw new IllegalArgumentException("Insufficient balance");
        }
        newBalance = balance.subtract(amount);

        accountRepository.update(account);

        return newBalance;

    }

    public BigDecimal showBalance(String Account){
        return accountRepository.findByAccountNumber(accountNumber).getBalance();


    }

}
