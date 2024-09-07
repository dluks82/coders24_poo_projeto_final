package controller;

import enums.LoggedInOption;
import enums.State;
import enums.AccountTypeOption;
import exception.DataInputInterruptedException;
import exception.DuplicateCPFException;
import model.Account;
import repository.BankRepository;
import state.AppState;
import ui.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class LoggedInController {
    private final AppState appState;
    private final BankRepository bankRepository;
    private final Scanner scanner;

    public LoggedInController(AppState appState, BankRepository bankRepository) {

        this.appState = appState;
        this.bankRepository = bankRepository;
        this.scanner = new Scanner(System.in);
    }

    public void run() {

        List<MenuUtils.MenuOption> loggedInMenuOptions = Arrays.asList(
                new MenuUtils.MenuOption(LoggedInOption.OPEN_ACCOUNT.getText(), LoggedInOption.OPEN_ACCOUNT.getLetter(), null),
                new MenuUtils.MenuOption(LoggedInOption.ACCESS_ACCOUNT.getText(), LoggedInOption.ACCESS_ACCOUNT.getLetter(), null),
                new MenuUtils.MenuOption(LoggedInOption.UPDATE_INFO.getText(), LoggedInOption.UPDATE_INFO.getLetter(), null),
                new MenuUtils.MenuOption(LoggedInOption.CLOSE_ACCOUNT.getText(), LoggedInOption.CLOSE_ACCOUNT.getLetter(), null),
                new MenuUtils.MenuOption(LoggedInOption.EXIT.getText(), LoggedInOption.EXIT.getLetter(), null)
        );

        while (appState.getCurrentState() == State.LOGGED_IN) {
            LoggedInOption selectedOption;

            Screen.clear();
            Header.show(appState.getLoggedInUserName());
            MenuUtils.showMenu(loggedInMenuOptions, "O que deseja fazer?", null);

            try {
                String userInput = Input.getAsString(scanner, "Opção: ", false, false);

                selectedOption = LoggedInOption.fromUserInput(userInput);

                if (selectedOption != null) {
                    switch (selectedOption) {
                        case OPEN_ACCOUNT -> openNewAccount();
                        case ACCESS_ACCOUNT -> showAccountList();
                        case UPDATE_INFO -> appState.setCurrentState(State.UPDATE_INFO);
                        case CLOSE_ACCOUNT -> System.out.println("Implementar...");
                        case EXIT -> {
                            appState.setCurrentState(State.NOT_LOGGED_IN);
                            appState.setLoggedInUser(null);
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

    private void openNewAccount() {

        AccountTypeOption selectedOption;
        List<MenuUtils.MenuOption> accountTypesMenuOptions = Arrays.asList(
                new MenuUtils.MenuOption(
                        AccountTypeOption.CURRENT_ACCOUNT.getText(),
                        AccountTypeOption.CURRENT_ACCOUNT.getLetter(),
                        null),
                new MenuUtils.MenuOption(
                        AccountTypeOption.SAVINGS_ACCOUNT.getText(),
                        AccountTypeOption.SAVINGS_ACCOUNT.getLetter(),
                        null)
        );

        // Implementar
        Output.info("Abrir conta");

        MenuUtils.showMenu(accountTypesMenuOptions, "Qual tipo de conta você deseja?",null);
        String userInput = Input.getAsString(scanner, "Opção: ", false, false);
        selectedOption = AccountTypeOption.fromUserInput(userInput);

        Output.message("Digite 'cancel' para retornar...");
        Output.info("Senha deve ter 4 números...");

        try {
            String password = Input.getAsAccountPassword(scanner, "Digite a senha: ", false, true);
            String passwordConfirm = Input.getAsString(scanner, "Confirme a senha: ", true, true);

            if (!password.equals(passwordConfirm)) {
                Output.error("As senhas não são iguais! Por favor, tente novamente...");
                scanner.nextLine();
            } else {
                String userCPF = appState.getLoggedUserId();
                boolean statusCreateAccount = bankRepository.createAccount(userCPF, password, selectedOption);
                if (statusCreateAccount) {
                    Output.info("Conta criada!");
                } else {
                    Output.info("Erro ao criar conta!");
                }
                scanner.nextLine();
            }
        } catch (DataInputInterruptedException e) {
            Output.info("Operação cancelada!");
            scanner.nextLine();
        }
    }

    private void showAccountList() {
        String userCPF = appState.getLoggedUserId();
        List<Account> accountList = bankRepository.getUserAccountList(userCPF);

        List<MenuUtils.MenuOption> accountOptions = new ArrayList<>();

        for (int i = 0; i < accountList.size(); i++) {
            char optionChar = (char) ('1' + i % 10); // Limite a 10 dígitos

            String description = String.format("[%s] - %s", optionChar, accountList.get(i).getNumber());
            accountOptions.add(new MenuUtils.MenuOption(description, optionChar, accountList.get(i).getNumber()));
        }
        accountOptions.add(new MenuUtils.MenuOption("[9] - Voltar", '9', null));

        MenuUtils.showMenu(accountOptions, "Qual conta deseja acessar?", null);

        String userInput = Input.getAsString(scanner, "Opção: ", true, false);

        MenuUtils.MenuOption selectedOption = null;

        for (MenuUtils.MenuOption option : accountOptions) {
            if (userInput.length() == 1 && option.getOptionChar() == userInput.charAt(0)) {
                selectedOption = option;
                break;
            }
        }

        if (selectedOption != null) {
            if (selectedOption.getOptionChar() == '9') return;

            accessAccount(selectedOption.getValue().toString());
        } else {
            System.out.println("Opção inválida! Tente novamente.");
            scanner.nextLine();
        }
    }

    private void accessAccount(String accountNumber) {
        Output.info("Acessar Conta");
        Output.message("Digite 'cancel' para retornar...");

        try {
            Output.info("Acessando a conta: " + accountNumber);

            String accountPassword =
                    Input.getAsAccountPassword(scanner,
                            "Digite a senha da conta: ",
                            false, true);

            Account account = bankRepository.getAccount(accountNumber);

            if (account == null || !account.validPassword(accountPassword)) {
                Output.error("Conta ou senha incorreta!");
                scanner.nextLine();
                return;
            }

            appState.setLoggedInAccount(account);
            appState.setCurrentState(State.ACCOUNT_MANAGEMENT);

        } catch (DataInputInterruptedException e) {
            Output.info("Operação cancelada!");
            scanner.nextLine();
        }
    }
}
