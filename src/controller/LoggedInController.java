package controller;

import enums.LoggedInOption;
import enums.State;
import repository.BankRepository;
import state.AppState;
import ui.*;

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
                new MenuUtils.MenuOption("[1] - Abrir Conta", 'A'),
                new MenuUtils.MenuOption("[2] - Acessar Conta", 'c'),
                new MenuUtils.MenuOption("[3] - Atualizar Dados Cadastrais", 't'),
                new MenuUtils.MenuOption("[4] - Encerrar Conta", 'E'),
                new MenuUtils.MenuOption("[9] - Sair", 'S')
        );
        while (appState.getCurrentState() == State.LOGGED_IN) {
            LoggedInOption selectedOption = null;

            Screen.clear();
            Header.show(appState.getLoggedInUserName());
            MenuUtils.showMenu(loggedInMenuOptions, "O que deseja fazer?");

//            LoggedInMenu.show();

            String userInput = Input.getAsString(scanner, "Opção: ", false);

            switch (userInput) {
                case "1":
                case "A":
                case "a":
                    selectedOption = LoggedInOption.OPEN_ACCOUNT;
                    break;
                case "2":
                case "C":
                case "c":
                    selectedOption = LoggedInOption.ACCESS_ACCOUNT;
                    break;
                case "3":
                case "T":
                case "t":
                    selectedOption = LoggedInOption.UPDATE_INFO;
                    break;
                case "4":
                case "F":
                case "f":
                    selectedOption = LoggedInOption.CLOSE_ACCOUNT;
                    break;
                case "9":
                case "S":
                case "s":
                    selectedOption = LoggedInOption.EXIT;
                    break;
                default:
                    System.out.println("Opção inválida!");
            }

            if (selectedOption != null) {
                switch (selectedOption) {
                    case OPEN_ACCOUNT -> System.out.println("Implementar...");
                    case ACCESS_ACCOUNT -> System.out.println("Implementar...");
                    case UPDATE_INFO -> appState.setCurrentState(State.UPDATE_INFO);
                    case CLOSE_ACCOUNT -> System.out.println("Implementar...");
                    case EXIT -> {
                        appState.setCurrentState(State.NOT_LOGGED_IN);
                        appState.setLoggedInUser(null);
                    }
                }
            }
        }
    }
}
