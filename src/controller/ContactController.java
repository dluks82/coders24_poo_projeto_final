package controller;

import model.Contact;
import repository.AgendaRepository;
import state.AppState;
import ui.Input;
import ui.Screen;

import java.util.Scanner;

public class ContactController {
    private AppState appState;
    private final AgendaRepository agendaRepository;
    private final Scanner input;

    public ContactController(AppState appState, Scanner input, AgendaRepository agendaRepository) {
        this.appState = appState;
        this.agendaRepository = agendaRepository;
        this.input = input;
    }

    public void add() {
        System.out.println("═════════════ Adicionar novo contato ═══════════════");
        String name = Input.getString(input, "Nome: ", false);
        String phone = Input.getString(input, "Telefone: ", false);
        String email = Input.getString(input, "Email: ", false);

        Contact newContact = new Contact(name, phone, email);

        boolean created = agendaRepository.create(newContact);

        String createdMessage = created ? "Contato adicionado!" : "Falha ao adicionar contato!";

        System.out.println("Pressionar Enter deve encerrar a aplicação!!!!");
        System.out.printf("%s Enter para continuar...%n", createdMessage);
        input.nextLine();
    }

    public void remove() {
        System.out.println("═════════════════ Remover contato ══════════════════");
        String idToRemove =
                Input.getString(input, "Id [0 para cancelar]: ", false);

        if (idToRemove.equals("0")) return;

        boolean removed = agendaRepository.remove(idToRemove);

        String removedMessage = removed ? "Contato removido!" : "Id não encontrado!";

        System.out.printf("%s Enter para continuar...", removedMessage);
        input.nextLine();
    }

    public void showDetails() {
        System.out.println("════════════════ Detalhar contato ══════════════════");
        String idToDetail =
                Input.getString(input, "Id [0 para cancelar]: ", false);

        if (idToDetail.equals("0")) return;

        Contact contact = agendaRepository.read(idToDetail);

        if (contact != null) {
            Screen.showContactDetails(contact);
        } else {
            System.out.println("Contato não existe!");
        }

        System.out.println("Enter para continuar...");
        input.nextLine();
    }

    public void edit() {
        System.out.println("═════════════════ Editar contato ═══════════════════");
        String idToEdit =
                Input.getString(input, "Id [0 para cancelar]: ", false);

        if (idToEdit.equals("0")) return;

        String newName = Input.getString(input, "Novo Nome: ", true);
        String newPhone = Input.getString(input, "Novo Telefone: ", true);
        String newEmail = Input.getString(input, "Novo Email: ", true);

        Contact editedContact = new Contact(idToEdit, newName, newPhone, newEmail);

        boolean edited = agendaRepository.update(editedContact);

        String editedMessage = edited ? "Contato editado!" : "Id não encontrado!";

        System.out.printf("%s Enter para continuar...", editedMessage);
        input.nextLine();
    }

    public void showList() {
        System.out.println("═════════════════ Listar contatos ══════════════════");
        Screen.showContactList(agendaRepository.list());

        System.out.println("Enter para continuar...");
        input.nextLine();
    }


}
