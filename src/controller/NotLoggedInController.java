package controller;

import enums.NotLoggedInOption;
import enums.State;
import exception.DataInputInterruptedException;
import exception.DuplicateCPFException;
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
                new MenuUtils.MenuOption(NotLoggedInOption.LOGIN.getText(), NotLoggedInOption.LOGIN.getLetter()),
                new MenuUtils.MenuOption(NotLoggedInOption.REGISTER.getText(), NotLoggedInOption.REGISTER.getLetter()),
                new MenuUtils.MenuOption(NotLoggedInOption.EXIT.getText(), NotLoggedInOption.EXIT.getLetter())
        );

        while (appState.getCurrentState() == State.NOT_LOGGED_IN) {
            NotLoggedInOption selectedOption = null;

            Screen.clear();
            Header.show(appState.getLoggedInUserName());
            MenuUtils.showMenu(notLoggedMenuOptions, "Entre ou Registre-se");

            String userInput = Input.getAsString(scanner, "Opção: ", false);

            selectedOption = NotLoggedInOption.fromUserInput(userInput);

            if (selectedOption != null) {
                switch (selectedOption) {
                    case LOGIN -> loginUser();
                    case REGISTER -> registerNewUser();
                    case EXIT -> appState.setCurrentState(State.EXIT);
                }
            } else {
                System.out.println("║ Opção inválida!");
            }
        }
    }

    public void registerNewUser() {
        System.out.println("║>>> Registrar Usuário");
        System.out.println("║>>> Digite 'cancel' para retornar...");

        try {
            String cpf = Input.getAsCPF(scanner, "CPF: ", false);
            if (bankRepository.getUser(cpf) != null) throw new DuplicateCPFException();

            String name = Input.getAsString(scanner, "Digite o nome: ", false);
            String password = Input.getAsPassword(scanner, "Digite a senha: ", false);

            String passwordConfirm = Input.getAsString(scanner, "Confirme a senha: ", true);

            if (!password.equals(passwordConfirm)) {
                System.out.println("║>>> As senhas não são iguais. Tente novamente.");
            } else {
                User newUser = new User(cpf, name, password);

                boolean created = bankRepository.createUser(newUser);

                String createdMessage = created ? "Usuário registrado!" : "Falha ao criar usuário!";

                System.out.printf("%s Enter para continuar...%n", createdMessage);
                scanner.nextLine();
            }
        } catch (DataInputInterruptedException e) {
            System.out.println("║>>> Operação cancelada!");
        } catch (DuplicateCPFException e) {
            System.out.println("║>>> O CPF informado já está cadastrado. Por favor, faça login.");
            scanner.nextLine();
        }
    }

    public void loginUser() {
        System.out.println("║>>> Entrar");
        System.out.println("║>>> Digite 'cancel' para retornar...");
        try {
            String cpf = Input.getAsCPF(scanner, "Digite o CPF: ", false);
            String password = Input.getAsString(scanner, "Digite a senha: ", false);

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
        } catch (DataInputInterruptedException e) {
            System.out.println("║>>> Operação cancelada!");
        }
    }
}
