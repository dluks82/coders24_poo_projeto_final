import model.Contact;
import repository.AgendaRepository;
import ui.Input;
import ui.Screen;

import java.util.List;
import java.util.Scanner;

public class AgendaApp {

    static private AgendaRepository data;

    static final int ID_COLUMN_WIDTH = 2;
    static final int NAME_COLUMN_WIDTH = 30;
    static final int PHONE_COLUMN_WIDTH = 20;
    static final int EMAIL_COLUMN_WIDTH = 30;

    public static void main(String[] args) {

        data = new AgendaRepository();

        Scanner input = new Scanner(System.in);

        while (true) {

            Screen.showMenu(data.size());

            int userOption = Input.getInt(input, "Informe a operação desejada: ", false);

            if (userOption == 9) break;

            switch (userOption) {
                case 1:
                    System.out.println("═════════════ Adicionar novo contato ═══════════════");
                    String name = Input.getString(input, "Nome: ", false);
                    String phone = Input.getString(input, "Telefone: ", false);
                    String email = Input.getString(input, "Email: ", false);

                    Contact newContact = new Contact(name, phone, email);

                    boolean created = data.create(newContact);

                    String createdMessage = created ? "Contato adicionado!" : "Falha ao adicionar contato!";

                    System.out.printf("%s Enter para continuar...%n", createdMessage);
                    input.nextLine();
                    break;
                case 2:
                    System.out.println("═════════════════ Remover contato ══════════════════");
                    String idToRemove =
                            Input.getString(input, "Id [0 para cancelar]: ", false);

                    if (idToRemove.equals("0")) break;

                    boolean removed = data.remove(idToRemove);

                    String removedMessage = removed ? "Contato removido!" : "Id não encontrado!";

                    System.out.printf("%s Enter para continuar...", removedMessage);
                    input.nextLine();
                    break;
                case 3:
                    System.out.println("════════════════ Detalhar contato ══════════════════");
                    String idToDetail =
                            Input.getString(input, "Id [0 para cancelar]: ", false);

                    if (idToDetail.equals("0")) break;

                    detail(idToDetail);

                    System.out.println("Enter para continuar...");
                    input.nextLine();
                    break;
                case 4:
                    System.out.println("═════════════════ Editar contato ═══════════════════");
                    String idToEdit =
                            Input.getString(input, "Id [0 para cancelar]: ", false);

                    if (idToEdit.equals("0")) break;

                    String newName = Input.getString(input, "Novo Nome: ", true);
                    String newPhone = Input.getString(input, "Novo Telefone: ", true);
                    String newEmail = Input.getString(input, "Novo Email: ", true);

                    Contact editedContact = new Contact(idToEdit, newName, newPhone, newEmail);

                    boolean edited = data.update(editedContact);

                    String editedMessage = edited ? "Contato editado!" : "Id não encontrado!";

                    System.out.printf("%s Enter para continuar...", editedMessage);
                    input.nextLine();
                    break;
                case 5:
                    System.out.println("═════════════════ Listar contatos ══════════════════");
                    list();

                    System.out.println("Enter para continuar...");
                    input.nextLine();
                    break;
                case 6:
                    Screen.showAbout();

                    System.out.println("Enter para continuar...");
                    input.nextLine();
                    break;
                default:
                    System.out.println("Opção inválida! Enter para continuar...");
                    input.nextLine();
            }
        }
        input.close();
    }

    static void list() {
        List<Contact> contactList = data.list();

        String format = "| %-" + ID_COLUMN_WIDTH + "s | %-" + NAME_COLUMN_WIDTH + "s |%n";

        System.out.printf(format, "ID", "Nome");
        System.out.printf("+-%s-+-%s-+%n",
                "-".repeat(ID_COLUMN_WIDTH),
                "-".repeat(NAME_COLUMN_WIDTH));

        for (Contact contact : contactList) {
            System.out.printf(format, contact.getId(), contact.getName());
        }
    }

    static void detail(String idParaListar) {

        Contact contact = data.read(idParaListar);

        if (contact != null) {
            String format = "| %-" + ID_COLUMN_WIDTH + "s | %-" + NAME_COLUMN_WIDTH + "s | %-" + PHONE_COLUMN_WIDTH + "s | %-" + EMAIL_COLUMN_WIDTH + "s %n";

            System.out.printf(format, "ID", "Nome", "Telefone", "Email");
            System.out.printf("+-%s-+-%s-+-%s-+-%s-+%n",
                    "-".repeat(ID_COLUMN_WIDTH),
                    "-".repeat(NAME_COLUMN_WIDTH),
                    "-".repeat(PHONE_COLUMN_WIDTH),
                    "-".repeat(EMAIL_COLUMN_WIDTH));

            System.out.printf(format,
                    contact.getId(),
                    contact.getName(),
                    contact.getPhone(),
                    contact.getEmail());
        } else {
            System.out.println("Contact not found!");
        }
    }
}
