package repository;

import data.BankDataWrapper;
import data.DataPersistence;
import model.Account;
import model.User;

import java.util.List;

public class BankRepository {
    private final List<User> userList;
    private final List<Account> accountList;

    public BankRepository() {
        BankDataWrapper bankDataWrapper = DataPersistence.load();
        this.userList = bankDataWrapper.userList();
        this.accountList = bankDataWrapper.accountList();
    }

    private void saveData() {
        DataPersistence.save(new BankDataWrapper(userList, accountList));
    }

    public boolean createUser(User user) {
        userList.add(user);
        saveData();
        return true;
    }

    public User getUser(String cpf) {
        for (User user : userList) {
            if (user.getCpf().equals(cpf)) return user;
        }
        return null;
    }

    public void createAccount(Account account) {
        accountList.add(account);
    }

}
