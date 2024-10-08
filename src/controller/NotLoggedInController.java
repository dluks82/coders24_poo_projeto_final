package controller;

import enums.NotLoggedInOption;
import enums.State;
import exception.DataInputInterruptedException;
import exception.DuplicateCPFException;
import model.Account;
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
                new MenuUtils.MenuOption(NotLoggedInOption.LOGIN.getText(), NotLoggedInOption.LOGIN.getLetter(), null),
                new MenuUtils.MenuOption(NotLoggedInOption.REGISTER.getText(), NotLoggedInOption.REGISTER.getLetter(), null),
                new MenuUtils.MenuOption(NotLoggedInOption.ACCESS_ACCOUNT.getText(), NotLoggedInOption.ACCESS_ACCOUNT.getLetter(), null),
                new MenuUtils.MenuOption(NotLoggedInOption.ABOUT.getText(), NotLoggedInOption.ABOUT.getLetter(), null),
                new MenuUtils.MenuOption(NotLoggedInOption.EXIT.getText(), NotLoggedInOption.EXIT.getLetter(), null)
        );

        while (appState.getCurrentState() == State.NOT_LOGGED_IN) {
            NotLoggedInOption selectedOption;

            Screen.clear();
            Header.show(appState.getLoggedInUserName());
            MenuUtils.showMenu(notLoggedMenuOptions, "Menu Inicial", null);

            try {
                String userInput = Input.getAsString(scanner, "Opção: ", false, false);

                selectedOption = NotLoggedInOption.fromUserInput(userInput);

                if (selectedOption != null) {
                    switch (selectedOption) {
                        case LOGIN -> loginUser();
                        case REGISTER -> registerNewUser();
                        case ACCESS_ACCOUNT -> accessAccount();
                        case ABOUT -> showAbout();
                        case EXIT -> appState.setCurrentState(State.EXIT);
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

    private void showAbout() {
        Screen.clear();
        About.show();
        Output.info("Enter para continuar...");
        scanner.nextLine();
    }

    private void accessAccount() {
        Output.info("Acessar Conta");
        Output.message("Digite 'cancel' para retornar...");

        try {
            String accountNumber =
                    Input.getAsString(scanner,
                            "Digite número da conta: ",
                            false, false);
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

    public void registerNewUser() {
        Output.info("Registrar Usuário");
        Output.message("Digite 'cancel' para retornar...");

        try {
            String cpf = Input.getAsCPF(scanner, "Digite o CPF: ", false);
            if (bankRepository.getUser(cpf) != null) throw new DuplicateCPFException();

            String name = Input.getAsString(scanner, "Digite o nome: ", false, false);
            String password = Input.getAsUserPassword(scanner, "Digite a senha: ", false, true);

            String passwordConfirm = Input.getAsString(scanner, "Confirme a senha: ", true, true);

            if (!password.equals(passwordConfirm)) {
                Output.error("As senhas não são iguais! Por favor, tente novamente...");
                scanner.nextLine();
            } else {
                User newUser = new User(cpf, name, password);

                boolean created = bankRepository.createUser(newUser);

                if (created) {
                    Output.info("Usuário registrado!");
                } else {
                    Output.error("Falha ao criar usuário!");
                }
                scanner.nextLine();
            }
        } catch (DataInputInterruptedException e) {
            Output.info("Operação cancelada!");
            scanner.nextLine();
        } catch (DuplicateCPFException e) {
            Output.info("O CPF informado já está cadastrado. Por favor, faça login.");
            scanner.nextLine();
        }
    }

    public void loginUser() {
        Output.info("Login");
        Output.message("Digite 'cancel' para retornar...");
        try {
            String cpf = Input.getAsCPF(scanner, "Digite o CPF: ", false);
            String password = Input.getAsString(scanner, "Digite a senha: ", false, true);

            User user = bankRepository.getUser(cpf);

            if (user == null || !user.validPassword(password)) {
                Output.error("Usuário ou senha inválidos!");
                scanner.nextLine();
                return;
            }
            appState.setCurrentState(State.LOGGED_IN);
            appState.setLoggedInUser(user);
        } catch (DataInputInterruptedException e) {
            Output.info("Operação cancelada!");
            scanner.nextLine();
        }
    }
}
