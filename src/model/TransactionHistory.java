package model;

import enums.AccountOption;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionHistory {

    private int accountNumber;
    private String transactionType;
    private BigDecimal amount;
    private boolean status;
    private LocalDateTime dateTime;

    public TransactionHistory(int accountNumber, AccountOption transactionType, BigDecimal amount, boolean status) {
        this.accountNumber = accountNumber;
        this.transactionType = transactionType.getDescription();
        this.amount = amount;
        this.status = status;
        this.dateTime = LocalDateTime.now();
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public boolean isStatus() {
        return status;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

}
