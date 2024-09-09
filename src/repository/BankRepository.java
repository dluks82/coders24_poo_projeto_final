package repository;

import data.DataWrapper;
import data.DataPersistence;
import enums.AccountOption;
import enums.AccountTypeOption;
import exception.InsufficientBalanceException;
import model.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BankRepository {
    private final List<User> userList;
    private final List<Account> accountList;
    private final List<TransactionHistory> transactionHistoryList;
    private int lastAccountNumber;

    public BankRepository() {
        DataWrapper dataWrapper = DataPersistence.load();

        this.userList = dataWrapper.users();
        this.accountList = dataWrapper.accounts();
        this.transactionHistoryList = dataWrapper.transactions();

        Object lastNumberObj = dataWrapper.config().get("lastAccountNumber");
        this.lastAccountNumber = (lastNumberObj instanceof Integer) ? (Integer) lastNumberObj : 1000;
    }

    private void saveData() {
        Map<String, Object> config = Map.of("lastAccountNumber", lastAccountNumber);
        DataPersistence.save(new DataWrapper(userList, accountList, transactionHistoryList, config));
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
            case CURRENT_ACCOUNT -> new CurrentAccount(accountNumberGenerator(), OwnerId, password);
            case SAVINGS_ACCOUNT -> new SavingsAccount(accountNumberGenerator(), OwnerId, password);
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

    public void createTransactionHistory(String accountNumber, AccountOption transactionType,
                                         BigDecimal amount, boolean status) {

        TransactionHistory transactionHistory = new TransactionHistory(
                accountNumber, transactionType, amount, status
        );

        transactionHistoryList.add(transactionHistory);
        saveData();
    }

    public List<TransactionHistory> getTransactionHistoryList(String accountNumber) {
        List<TransactionHistory> transactionList = new ArrayList<>();
        for (TransactionHistory transaction : transactionHistoryList) {
            if (transaction.getAccountNumber().equals(accountNumber)) {
                transactionList.add(transaction);
            }
        }
        return transactionList;
    }

    public boolean deposit(String accountNumber, BigDecimal amount) {
        Account account = getAccount(accountNumber);
        boolean result = false;

        if (account != null) {
            result = account.deposit(amount);

            saveData();
        }

        createTransactionHistory(accountNumber, AccountOption.DEPOSIT, amount, result);
        return result;
    }

    public boolean withdrawal(String accountNumber, String password, BigDecimal amount) throws InsufficientBalanceException {
        Account account = getAccount(accountNumber);
        boolean result = false;

        if (account != null && account.validPassword(password)) {
            result = account.withdrawal(amount);
            saveData();
        }

        createTransactionHistory(accountNumber, AccountOption.WITHDRAW, amount, result);
        return result;
    }

    public BigDecimal getAccountBalance(String accountNumber) {
        Account account = getAccount(accountNumber);

        if (account != null) {
            return account.getBalance();
        }

        return BigDecimal.ZERO;
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
