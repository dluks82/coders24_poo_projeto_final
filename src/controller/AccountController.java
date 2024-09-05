package controller;

import enums.AccountOption;
import enums.LoggedInOption;
import enums.State;
import exception.DataInputInterruptedException;
import repository.BankRepository;
import state.AppState;
import ui.*;

import java.util.Arrays;
import java.util.List;
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
                new MenuUtils.MenuOption(AccountOption.DEPOSIT.getText(), AccountOption.DEPOSIT.getLetter()),
                new MenuUtils.MenuOption(AccountOption.WITHDRAW.getText(), AccountOption.WITHDRAW.getLetter()),
                new MenuUtils.MenuOption(AccountOption.TRANSFER.getText(), AccountOption.TRANSFER.getLetter()),
                new MenuUtils.MenuOption(AccountOption.CHECK_BALANCE.getText(), AccountOption.CHECK_BALANCE.getLetter()),
                new MenuUtils.MenuOption(AccountOption.EXIT.getText(), AccountOption.EXIT.getLetter())
        );

        while (appState.getCurrentState() == State.ACCOUNT_MANAGEMENT) {
            AccountOption selectedOption = null;

            Screen.clear();
            Header.show(appState.getLoggedInUserName());
            MenuUtils.showMenu(accountMenuOptions, "Operações de conta");

            try {
                String userInput = Input.getAsString(scanner, "Opção: ", false, false);

                selectedOption = AccountOption.fromUserInput(userInput);

                if (selectedOption != null) {
                    switch (selectedOption) {
                        case DEPOSIT -> System.out.println("Implementar...");
                        case WITHDRAW -> System.out.println("Implementar...");
                        case TRANSFER -> System.out.println("Implementar...");
                        case CHECK_BALANCE -> System.out.println("Implementar...");
                        case EXIT -> appState.setCurrentState(State.LOGGED_IN);
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
}
