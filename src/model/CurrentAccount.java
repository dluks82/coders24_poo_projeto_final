package model;

import java.math.BigDecimal;

public class CurrentAccount extends Account {

    public CurrentAccount(String number, BigDecimal balance, String ownerId, String password) {
        super(number, balance, ownerId, password);
    }

}
