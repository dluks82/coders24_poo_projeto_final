package controller;

import enums.AccountOption;
import enums.State;
import exception.DataInputInterruptedException;
import model.CurrentAccount;
import model.SavingsAccount;
import repository.BankRepository;
import state.AppState;
import ui.*;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class AccountController {
    private final AppState appState;
    private final BankRepository bankRepository;
    private final Scanner scanner;

    public AccountController(AppState appState, BankRepository bankRepository) {

        this.appState = appState;
        this.bankRepository = bankRepository;
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        List<MenuUtils.MenuOption> accountMenuOptions = Arrays.asList(
                new MenuUtils.MenuOption(AccountOption.DEPOSIT.getText(), AccountOption.DEPOSIT.getLetter(), null),
                new MenuUtils.MenuOption(AccountOption.WITHDRAW.getText(), AccountOption.WITHDRAW.getLetter(), null),
                new MenuUtils.MenuOption(AccountOption.TRANSFER.getText(), AccountOption.TRANSFER.getLetter(), null),
                new MenuUtils.MenuOption(AccountOption.CHECK_BALANCE.getText(), AccountOption.CHECK_BALANCE.getLetter(), null),
                new MenuUtils.MenuOption(AccountOption.EXIT.getText(), AccountOption.EXIT.getLetter(), null)
        );

        while (appState.getCurrentState() == State.ACCOUNT_MANAGEMENT) {
            AccountOption selectedOption;

            Screen.clear();
            Header.show(appState.getLoggedInUserName());
            String accountNumber = appState.getLoggedInAccount().getNumber();
            String accountType;
            if (appState.getLoggedInAccount() instanceof SavingsAccount) {
                accountType = "Poupança";
            } else if (appState.getLoggedInAccount() instanceof CurrentAccount) {
                accountType = "Corrente";
            } else {
                accountType = "Desconhecida";
            }

            MenuUtils.showMenu(accountMenuOptions, "Conta " + accountType, "Número: " + accountNumber);

            try {
                String userInput = Input.getAsString(scanner, "Opção: ", false, false);

                selectedOption = AccountOption.fromUserInput(userInput);

                if (selectedOption != null) {
                    switch (selectedOption) {
                        case DEPOSIT -> handleDeposit();
                        case WITHDRAW -> handleWithdraw();
                        case TRANSFER -> System.out.println("Implementar...");
                        case CHECK_BALANCE -> showResume();
                        case EXIT -> {
                            if (appState.getLoggedInUser() != null) {
                                appState.setCurrentState(State.LOGGED_IN);
                            } else {
                                appState.setCurrentState(State.NOT_LOGGED_IN);
                            }
                            appState.setLoggedInAccount(null);
                        }
                    }
                } else {
                    Output.info("Opção inválida! Por favor, informe uma opção do menu...");
                    scanner.nextLine();
                }
            } catch (DataInputInterruptedException e) {
                Output.info("Opção inválida! Por favor, informe uma opção do menu...");
                scanner.nextLine();
            }
        }
    }

    private void handleWithdraw() {
        Output.info("Sacar");
        Output.message("Digite 'cancel' para retornar...");

        try {
            String accountNumber = appState.getLoggedInAccount().getNumber();
            BigDecimal amount =
                    Input.getAsBigDecimal(scanner,
                            "Digite o valor do saque: ", false);

            String password = Input.getAsString(scanner, "Digite a senha da conta: ", false, true);

            boolean withdrawResult = bankRepository.withdrawal(accountNumber, password, amount);

            if (withdrawResult) {
                Output.info("Saque realizado.");
                scanner.nextLine();
            } else {
                Output.info("Erro ao realizar saque.");
                scanner.nextLine();
            }

        } catch (DataInputInterruptedException e) {
            Output.info("Operação cancelada!");
            scanner.nextLine();
        }
    }

    private void handleDeposit() {
        Output.info("Depositar");
        Output.message("Digite 'cancel' para retornar...");

        try {
            String accountNumber = appState.getLoggedInAccount().getNumber();
            BigDecimal amount =
                    Input.getAsBigDecimal(scanner,
                            "Digite o valor do depósito: ", false);

            boolean depositResult = bankRepository.deposit(accountNumber, amount);

            if (depositResult) {
                Output.info("Depósito realizado.");
                scanner.nextLine();
            } else {
                Output.info("Erro ao realizar depósito.");
                scanner.nextLine();
            }

        } catch (DataInputInterruptedException e) {
            Output.info("Operação cancelada!");
            scanner.nextLine();
        }
    }

    private void showResume() {
        String currentAccountNumber = appState.getLoggedInAccount().getNumber();
        BigDecimal accountBalance = bankRepository.getAccountBalance(currentAccountNumber);

        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.forLanguageTag("pt-BR"));
        String formattedBalance = currencyFormatter.format(accountBalance);

        Output.info("Saldo da Conta: " + currentAccountNumber);
        Output.message(formattedBalance);


        scanner.nextLine();
    }
}
