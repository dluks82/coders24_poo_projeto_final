package data;

import model.Account;
import model.TransactionHistory;
import model.User;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public record DataWrapper(
        List<User> users,
        List<Account> accounts,
        List<TransactionHistory> transactions,
        Map<String, Object> config) implements Serializable {
    private static final long serialVersionUID = 1L;

}
