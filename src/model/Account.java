package model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public abstract class Account implements Serializable {
    private static final long serialVersionUID = 1L;

    private String number;
    private BigDecimal balance;
    private String ownerId;
    private String password;

    public Account(String number, BigDecimal balance, String ownerId, String password) {
        this.number = number;
        this.balance = balance;
        this.ownerId = ownerId;
        setPassword(password);
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

    public void setPassword(String password) {
        this.password = hashPassword(password);
    }

    public boolean deposit(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Valor não pode ser negativo!");
        }

        this.balance = this.balance.add(amount);
        return true;
    }

    public boolean withdrawal(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Valor não pode ser negativo!");
        }
        if (amount.compareTo(this.balance) <= 0) {
            this.balance = this.balance.subtract(amount);
            return true;
        } else {
            // lançar uma exceção
            return false;
        }
    }


    @Override
    public String toString() {
        return "Account{" +
                "number='" + number + '\'' +
                ", balance=" + balance +
                ", ownerId='" + ownerId + '\'' +
                '}';
    }

    public boolean validPassword(String password) {
        return this.password.equals(hashPassword(password));
    }

    private static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Algoritmo de hash não suportado", e);
        }
    }
}
