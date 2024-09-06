package repository;

import data.DataWrapper;
import data.DataPersistence;
import enums.AccountTypeOption;
import model.Account;
import model.User;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class BankRepository {
    private final List<User> userList;
    private final List<Account> accountList;
    private int lastAccountNumber;

    public BankRepository() {
        DataWrapper dataWrapper = DataPersistence.load();
        this.userList = dataWrapper.userList();
        this.accountList = dataWrapper.accountList();
        this.lastAccountNumber = dataWrapper.lastAccountNumber();
    }

    private void saveData() {
        DataPersistence.save(new DataWrapper(userList, accountList, lastAccountNumber));
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

    public boolean createAccount(String OwnerId, String password, AccountTypeOption accountType) {

        if (accountType == null) return false;

        Account newAccount = switch (accountType) {
            case CURRENT_ACCOUNT -> new Account(accountNumberGenerator(), BigDecimal.ZERO, OwnerId, password);
            case SAVINGS_ACCOUNT -> new Account(accountNumberGenerator(), BigDecimal.ZERO, OwnerId, password);
        };

        accountList.add(newAccount);
        saveData();

        return true;
    }

    public Account getAccount(String accountNumber) {
        for (Account account : accountList) {
            if (account.getNumber().equals(accountNumber)) {
                return account;
            }
        }
        return null;
    }

    public List<Account> getUserAccountList(String ownerId) {
        List<Account> list = new ArrayList<>();

        for (Account account : accountList) {
            if (account.getOwnerId().equals(ownerId)) {
                list.add(account);
            }
        }
        return list;
    }


    public String accountNumberGenerator() {
        int nextAccountNumber = (int) (lastAccountNumber + Math.ceil(Math.random() * 10));
        int checkDigit = calculateCheckDigit(nextAccountNumber);

        lastAccountNumber = nextAccountNumber;

        return String.format("%d-%d", nextAccountNumber, checkDigit);
    }

    private int calculateCheckDigit(int nextAccountNumber) {
        int sum = 0;
        while (nextAccountNumber > 0) {
            sum += nextAccountNumber % 10;
            nextAccountNumber /= 10;
        }
        return sum % 10;
    }

}
