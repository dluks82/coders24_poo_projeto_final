package repository;

import data.DataWrapper;
import data.DataPersistence;
import model.Account;
import model.User;

import java.util.List;

public class BankRepository {
    private final List<User> userList;
    private final List<Account> accountList;

    public BankRepository() {
        DataWrapper dataWrapper = DataPersistence.load();
        this.userList = dataWrapper.userList();
        this.accountList = dataWrapper.accountList();
    }

    private void saveData() {
        DataPersistence.save(new DataWrapper(userList, accountList));
    }

    public boolean createUser(User user) {
        userList.add(user);
        saveData();
        return true;
    }

    public boolean updateUser(User user) {
        int userIndex = getUserIndex(user.getCpf());
        if (userIndex != -1) {
            userList.set(userIndex, user);
            saveData();
        }
        return true;
    }

    public int getUserIndex(String cpf) {
        for (int i = 0; i < userList.size(); i++) {
            User user = userList.get(i);
            if (user.getCpf().equals(cpf)) return i;
        }
        return -1;
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
