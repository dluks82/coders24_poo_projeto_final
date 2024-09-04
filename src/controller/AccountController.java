package controller;

import enums.AccountOption;
import enums.State;
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
                new MenuUtils.MenuOption("[1] - Depósito", 'D'),
                new MenuUtils.MenuOption("[2] - Saque", 'S'),
                new MenuUtils.MenuOption("[3] - Transferência", 'T'),
                new MenuUtils.MenuOption("[4] - Consultar Saldo", 'C'),
                new MenuUtils.MenuOption("[9] - Voltar", 'V')
        );
        while (appState.getCurrentState() == State.ACCOUNT_MANAGEMENT) {
            AccountOption selectedOption = null;

            Screen.clear();
            Header.show(appState.getLoggedInUserName());
            MenuUtils.showMenu(accountMenuOptions, "Operações de conta");
//            AccountMenu.show();

            String userInput = Input.getAsString(scanner, "Opção: ", false);

            switch (userInput) {
                case "1":
                case "D":
                case "d":
                    selectedOption = AccountOption.DEPOSIT;
                    break;
                case "2":
                case "S":
                case "s":
                    selectedOption = AccountOption.WITHDRAW;
                    break;
                case "3":
                case "T":
                case "t":
                    selectedOption = AccountOption.TRANSFER;
                    break;
                case "4":
                case "C":
                case "c":
                    selectedOption = AccountOption.CHECK_BALANCE;
                    break;
                case "9":
                case "V":
                case "v":
                    selectedOption = AccountOption.EXIT;
                    break;
                default:
                    System.out.println("Opção inválida!");
            }

            if (selectedOption != null) {
                switch (selectedOption) {
                    case DEPOSIT -> System.out.println("Implementar...");
                    case WITHDRAW -> System.out.println("Implementar...");
                    case TRANSFER -> System.out.println("Implementar...");
                    case CHECK_BALANCE -> System.out.println("Implementar...");
                    case EXIT -> appState.setCurrentState(State.LOGGED_IN);

                }
            }
        }
    }
}
