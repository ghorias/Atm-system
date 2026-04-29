package com.example.demo.model;
import java.math.BigDecimal;
import java.time.LocalDateTime;



public class Transaction {

    // Unique ID for set transaction, this is the primary key in SQL
    private long transactionId;

    //This field is for the Account which made the transaction. This is the Foreign Key in SQL
    private String accountNumber;

    //Amount of money used in set transaction
    private BigDecimal amount;

    //Time and date of which the transaction was made
    private LocalDateTime timeStamp;

    //Which type of transaction was made, either a Deposit or Withdrawal
    public enum TransactionType{
        DEPOSIT, WITHDRAWAL
    }

    //Data type for transaction type.
    private TransactionType type;




    //Getters and Setters
    public long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(long transactionId) {
        this.transactionId = transactionId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }



    //Constructor
    public Transaction(long transactionId, String accountNumber, BigDecimal amount, LocalDateTime timeStamp, TransactionType type) {
        this.transactionId = transactionId;
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.timeStamp = timeStamp;
        this.type = type;
    }

    public Transaction(){

    }
}
