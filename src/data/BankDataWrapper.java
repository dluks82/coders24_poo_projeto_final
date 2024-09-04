package data;

import model.Account;
import model.User;

import java.util.List;

public record BankDataWrapper(List<User> userList, List<Account> accountList) {
}
