package model;

import java.math.BigDecimal;

public class Account {
    private String number;
    private BigDecimal balance;
    private String ownerId;

    public Account(String number, BigDecimal balance, String ownerId) {
        this.number = number;
        this.balance = balance;
        this.ownerId = ownerId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    @Override
    public String toString() {
        return "Account{" +
                "number='" + number + '\'' +
                ", balance=" + balance +
                ", ownerId='" + ownerId + '\'' +
                '}';
    }
}
