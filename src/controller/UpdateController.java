package controller;

import enums.State;
import enums.UpdateUserOption;
import repository.BankRepository;
import state.AppState;
import ui.*;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class UpdateController {
    private final AppState appState;
    private final BankRepository bankRepository;
    private final Scanner scanner;

    public UpdateController(AppState appState, BankRepository bankRepository) {

        this.appState = appState;
        this.bankRepository = bankRepository;
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        List<MenuUtils.MenuOption> updateMenuOptions = Arrays.asList(
                new MenuUtils.MenuOption("[1] - Atualizar Nome", 'N'),
                new MenuUtils.MenuOption("[2] - Trocar Senha", 'S'),
                new MenuUtils.MenuOption("[9] - Voltar", 'V')
        );
        while (appState.getCurrentState() == State.UPDATE_INFO) {
            UpdateUserOption selectedOption = null;

            Screen.clear();
            Header.show(appState.getLoggedInUserName());
            MenuUtils.showMenu(updateMenuOptions, "Dados do usuário");
//            UpdateMenu.show();

            String userInput = Input.getAsString(scanner, "Opção: ", false);

            switch (userInput) {
                case "1":
                case "N":
                case "n":
                    selectedOption = UpdateUserOption.NAME;
                    break;
                case "2":
                case "S":
                case "s":
                    selectedOption = UpdateUserOption.PASSWORD;
                    break;

                case "9":
                case "V":
                case "v":
                    selectedOption = UpdateUserOption.BACK;
                    break;
                default:
                    System.out.println("Opção inválida!");
            }

            if (selectedOption != null) {
                switch (selectedOption) {
                    case NAME -> System.out.println("Implementar...");
                    case PASSWORD -> System.out.println("Implementar...");
                    case BACK -> {
                        appState.setCurrentState(State.LOGGED_IN);
                    }
                }
            }
        }
    }
}
