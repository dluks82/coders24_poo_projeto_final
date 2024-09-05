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

            String userInput = Input.getAsString(scanner, "Opção: ", false);

            selectedOption = UpdateUserOption.fromUserInput(userInput);

            if (selectedOption != null) {
                switch (selectedOption) {
                    case NAME -> updateUserName();
                    case PASSWORD -> updateUserPassword();
                    case BACK -> appState.setCurrentState(State.LOGGED_IN);

                }
            } else {
                System.out.println("║ Opção inválida!");
            }
        }
    }

    private void showCurrentUserData() {
        User loggedInUser = appState.getLoggedInUser();
        if (loggedInUser != null) {
            System.out.println("║>>> Dados Atuais do Usuário:");
            System.out.println("║ Nome: " + loggedInUser.getName());
            System.out.println("║ CPF: " + loggedInUser.getCpf());
            System.out.println("║");
        } else {
            System.out.println("║ Nenhum usuário logado.");
            System.out.println("║");
        }
    }

    private void updateUserName() {
        System.out.println("║>>> Atualizar Nome");
        System.out.println("║>>> Digite 'cancel' para retornar...");

        try {
            String name = Input.getAsString(scanner, "Digite o novo nome: ", true);
            if (!name.isEmpty()) {
                User user = appState.getLoggedInUser();
                user.setName(name);
                bankRepository.updateUser(user);

                appState.setLoggedInUser(user);
            }
        } catch (DataInputInterruptedException e) {
            System.out.println("║>>> Operação cancelada!");
        }
    }

    private void updateUserPassword() {
        System.out.println("║>>> Trocar Senha");
        System.out.println("║>>> Digite 'cancel' para retornar...");

        try {
            String password = Input.getAsPassword(scanner, "Digite a nova senha: ", true);
            if (!password.isEmpty()) {
                String passwordConfirm = Input.getAsString(scanner, "Confirme a nova senha: ", true);

                if (!password.equals(passwordConfirm)) {
                    System.out.println("║>>> As senhas não são iguais. Tente novamente.");
                } else {
                    User user = appState.getLoggedInUser();
                    user.setPassword(password);
                    bankRepository.updateUser(user);

                    appState.setLoggedInUser(user);
                }
            }
        } catch (DataInputInterruptedException e) {
            System.out.println("║>>> Operação cancelada!");
        }
    }
}
