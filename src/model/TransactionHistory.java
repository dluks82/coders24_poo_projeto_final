package model;

import enums.AccountOption;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionHistory implements Serializable {

    private String accountNumber;
    private String transactionType;
    private BigDecimal amount;
    private boolean status;
    private LocalDateTime dateTime;

    public TransactionHistory(String accountNumber, AccountOption transactionType, BigDecimal amount, boolean status) {
        this.accountNumber = accountNumber;
        this.transactionType = transactionType.getDescription();
        this.amount = amount;
        this.status = status;
        this.dateTime = LocalDateTime.now();
    }

    public String getAccountNumber() {
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
