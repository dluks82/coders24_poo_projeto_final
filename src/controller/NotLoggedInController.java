package controller;

import enums.NotLoggedInOption;
import enums.State;
import model.User;
import repository.BankRepository;
import state.AppState;
import ui.*;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class NotLoggedInController {
    private final AppState appState;
    private final BankRepository bankRepository;
    private final Scanner scanner;

    public NotLoggedInController(AppState appState, BankRepository bankRepository) {
        this.appState = appState;
        this.bankRepository = bankRepository;
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        List<MenuUtils.MenuOption> notLoggedMenuOptions = Arrays.asList(
                new MenuUtils.MenuOption("[1] - Entrar", 'E'),
                new MenuUtils.MenuOption("[2] - Registrar", 'R'),
                new MenuUtils.MenuOption("[9] - Encerrar App", 'A')
        );

        while (appState.getCurrentState() == State.NOT_LOGGED_IN) {
            NotLoggedInOption selectedOption = null;

            Screen.clear();
            Header.show(appState.getLoggedInUserName());
            MenuUtils.showMenu(notLoggedMenuOptions, "Entre ou Registre-se");
//            NotLoggedInMenu.show();

            String userInput = Input.getAsString(scanner, "Opção: ", false);
            switch (userInput) {
                case "1":
                case "E":
                case "e":
                    selectedOption = NotLoggedInOption.LOGIN;
                    break;
                case "2":
                case "R":
                case "r":
                    selectedOption = NotLoggedInOption.REGISTER;
                    break;
                case "9":
                case "A":
                case "a":
                    selectedOption = NotLoggedInOption.EXIT;
                    break;
                default:
                    System.out.println("║ Opção inválida!");
            }

            if (selectedOption != null) {
                switch (selectedOption) {
                    case LOGIN -> loginUser();
                    case REGISTER -> registerNewUser();
                    case EXIT -> appState.setCurrentState(State.EXIT);
                }
            }
        }
    }

    public void registerNewUser() {
        System.out.println("║>>> Registrar Usuário");
        String cpf = Input.getAsString(scanner, "CPF: ", false);
        String name = Input.getAsString(scanner, "Nome: ", false);
        String password = Input.getAsString(scanner, "Senha: ", false);

        User newUser = new User(cpf, name, password);

        boolean created = bankRepository.createUser(newUser);

        String createdMessage = created ? "Usuário registrado!" : "Falha ao criar usuário!";

        System.out.printf("%s Enter para continuar...%n", createdMessage);
        scanner.nextLine();
    }

    public void loginUser() {
        System.out.println("║>>> Entrar");
        String cpf = Input.getAsString(scanner, "CPF: ", false);
        String password = Input.getAsString(scanner, "Senha: ", false);

        User user = bankRepository.getUser(cpf);

        if (user == null) {
            System.out.println("║ Usuário ou senha inválidos!");
            return;
        }

        if (user.validPassword(password)) {
            appState.setCurrentState(State.LOGGED_IN);
            appState.setLoggedInUser(user);
        } else {
            System.out.println("║ Usuário ou senha inválidos!");
        }
    }
}
