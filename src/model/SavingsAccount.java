package model;

import java.math.BigDecimal;

public class SavingsAccount extends Account {

    public SavingsAccount(String number, String ownerId, String password) {
        super(number, ownerId, password);
    }

    public SavingsAccount(String number, BigDecimal balance, String ownerId, String password) {
        super(number, balance, ownerId, password);
    }

}
