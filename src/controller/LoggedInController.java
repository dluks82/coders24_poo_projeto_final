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
                new MenuUtils.MenuOption(LoggedInOption.OPEN_ACCOUNT.getText(), LoggedInOption.OPEN_ACCOUNT.getLetter()),
                new MenuUtils.MenuOption(LoggedInOption.ACCESS_ACCOUNT.getText(), LoggedInOption.ACCESS_ACCOUNT.getLetter()),
                new MenuUtils.MenuOption(LoggedInOption.UPDATE_INFO.getText(), LoggedInOption.UPDATE_INFO.getLetter()),
                new MenuUtils.MenuOption(LoggedInOption.CLOSE_ACCOUNT.getText(), LoggedInOption.CLOSE_ACCOUNT.getLetter()),
                new MenuUtils.MenuOption(LoggedInOption.EXIT.getText(), LoggedInOption.EXIT.getLetter())
        );

        while (appState.getCurrentState() == State.LOGGED_IN) {
            LoggedInOption selectedOption = null;

            Screen.clear();
            Header.show(appState.getLoggedInUserName());
            MenuUtils.showMenu(loggedInMenuOptions, "O que deseja fazer?");

            String userInput = Input.getAsString(scanner, "Opção: ", false);

            selectedOption = LoggedInOption.fromUserInput(userInput);

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
            } else {
                System.out.println("║ Opção inválida!");
            }
        }
    }
}
