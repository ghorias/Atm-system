package com.example.demo.service;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.example.demo.model.Account;
import com.example.demo.model.Transaction;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.TransactionRepository;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public AccountService(AccountRepository accountRepository, TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }
    //variable name could change "findByAccountNumber"
    public BigDecimal deposit(String accountNumber, BigDecimal amount) {
        Account account = accountRepository.findByAccountNumber(accountNumber);

        BigDecimal newBalance = account.getBalance().add(amount);
        account.setBalance(newBalance);

        accountRepository.updateBalance(account.getAccountNumber(), newBalance);

        // Logga transaktionen
        Transaction transaction = new Transaction();
        transaction.setAccountNumber(accountNumber);
        transaction.setAmount(amount);
        transaction.setTimeStamp(LocalDateTime.now());
        transaction.setType(Transaction.TransactionType.DEPOSIT);
        transactionRepository.save(transaction);

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
        account.setBalance(newBalance);
        accountRepository.updateBalance(account.getAccountNumber(), newBalance);

        return newBalance;

    }

    public BigDecimal showBalance(String accountNumber){
        return accountRepository.findByAccountNumber(accountNumber).getBalance();


    }

}
