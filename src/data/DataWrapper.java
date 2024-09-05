package data;

import model.Account;
import model.User;

import java.util.List;

public record DataWrapper(List<User> userList, List<Account> accountList) {
}
