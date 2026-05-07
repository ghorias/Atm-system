package com.example.demo.controller;

import com.example.demo.dto.AmountRequest;
import com.example.demo.dto.LoginRequest;
import com.example.demo.service.AccountService;

import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    // POST /api/login
    @PostMapping("/login")
    public boolean login(@RequestBody LoginRequest request) {

        return accountService.verifyPin(
                request.getAccountNumber(),
                request.getPin()
        );
    }

    // GET /api/account/{accountNumber}/balance
    @GetMapping("/account/{accountNumber}/balance")
    public BigDecimal getBalance(@PathVariable String accountNumber) {

        return accountService.showBalance(accountNumber);
    }

    // POST /api/account/{accountNumber}/deposit
    @PostMapping("/account/{accountNumber}/deposit")
    public BigDecimal deposit(
            @PathVariable String accountNumber,
            @RequestBody AmountRequest request
    ) {

        return accountService.deposit(
                accountNumber,
                request.getAmount()
        );
    }

    // POST /api/account/{accountNumber}/withdraw
    @PostMapping("/account/{accountNumber}/withdraw")
    public BigDecimal withdraw(
            @PathVariable String accountNumber,
            @RequestBody AmountRequest request
    ) {

        return accountService.withdraw(
                accountNumber,
                request.getAmount()
        );
    }
}