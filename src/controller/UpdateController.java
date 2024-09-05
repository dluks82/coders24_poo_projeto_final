package controller;

import enums.State;
import enums.UpdateUserOption;
import exception.DataInputInterruptedException;
import model.User;
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
                new MenuUtils.MenuOption(UpdateUserOption.NAME.getText(), UpdateUserOption.NAME.getLetter()),
                new MenuUtils.MenuOption(UpdateUserOption.PASSWORD.getText(), UpdateUserOption.PASSWORD.getLetter()),
                new MenuUtils.MenuOption(UpdateUserOption.BACK.getText(), UpdateUserOption.BACK.getLetter())
        );

        while (appState.getCurrentState() == State.UPDATE_INFO) {
            UpdateUserOption selectedOption = null;

            Screen.clear();
            Header.show(appState.getLoggedInUserName());
            MenuUtils.showMenu(updateMenuOptions, "Dados do usuário");

            showCurrentUserData();

            try {
                String userInput = Input.getAsString(scanner, "Opção: ", false, false);

                selectedOption = UpdateUserOption.fromUserInput(userInput);

                if (selectedOption != null) {
                    switch (selectedOption) {
                        case NAME -> updateUserName();
                        case PASSWORD -> updateUserPassword();
                        case BACK -> appState.setCurrentState(State.LOGGED_IN);
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

    private void showCurrentUserData() {
        User loggedInUser = appState.getLoggedInUser();
        if (loggedInUser != null) {
            Output.info("Dados do Usuário:");
            Output.message("");
            Output.message("Nome: " + loggedInUser.getName());
            Output.message("CPF: " + loggedInUser.getCpf());
            Output.message("");
        } else {
            Output.info("Nenhum usuário logado.");
            System.out.println("║");
        }
    }

    private void updateUserName() {
        Output.info("Atualizar Nome");
        Output.message("Digite 'cancel' para retornar...");

        try {
            String name = Input.getAsString(scanner, "Digite o novo nome: ", true, false);
            if (!name.isEmpty()) {
                User user = appState.getLoggedInUser();
                user.setName(name);
                bankRepository.updateUser(user);

                appState.setLoggedInUser(user);

                Output.info("Nome atualizado!");
                scanner.nextLine();
            }
        } catch (DataInputInterruptedException e) {
            Output.info("Operação cancelada!");
            scanner.nextLine();
        }
    }

    private void updateUserPassword() {
        Output.info("Trocar Senha");
        Output.message("Digite 'cancel' para retornar...");

        try {
            String password = Input.getAsPassword(scanner, "Digite a nova senha: ", true, true);
            if (!password.isEmpty()) {
                String passwordConfirm = Input.getAsString(scanner, "Confirme a nova senha: ", true, true);

                if (!password.equals(passwordConfirm)) {
                    Output.error("As senhas não são iguais! Por favor, tente novamente...");
                    scanner.nextLine();
                } else {
                    User user = appState.getLoggedInUser();
                    user.setPassword(password);
                    bankRepository.updateUser(user);

                    appState.setLoggedInUser(user);

                    Output.info("Senha atualizada!");
                    scanner.nextLine();
                }
            }
        } catch (DataInputInterruptedException e) {
            Output.info("Operação cancelada!");
            scanner.nextLine();
        }
    }
}
