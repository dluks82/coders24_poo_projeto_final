import model.Contact;
import repository.AgendaRepository;
import ui.Input;
import ui.Screen;

import java.util.List;
import java.util.Scanner;

public class AgendaApp {

    static private AgendaRepository data;

    static final int COLUNA_ID_WIDTH = 2;
    static final int COLUNA_NOME_WIDTH = 30;
    static final int COLUNA_TELEFONE_WIDTH = 20;
    static final int COLUNA_EMAIL_WIDTH = 30;

    public static void main(String[] args) {

        data = new AgendaRepository();

        Scanner input = new Scanner(System.in);

        while (true) {

            Screen.exibirMenu(data.size());

            int opcao = Input.obterInt(input, "Informe a operação desejada: ", false);

            if (opcao == 9) break;

            switch (opcao) {
                case 1:
                    // Adicionar
                    System.out.println("═════════════ Adicionar novo contato ═══════════════");
                    String nome = Input.obterString(input, "Nome: ", false);
                    String telefone = Input.obterString(input, "Telefone: ", false);
                    String email = Input.obterString(input, "Email: ", false);

                    Contact newContact = new Contact(nome, telefone, email);

                    boolean adicionado = data.create(newContact);

                    String AdicionarMensagem = adicionado ? "Contato adicionado!" : "Falha ao adicionar contato!";

                    System.out.printf("%s Enter para continuar...%n", AdicionarMensagem);
                    input.nextLine();
                    break;
                case 2:
                    // Remover
                    System.out.println("═════════════════ Remover contato ══════════════════");
                    String idParaRemover =
                            Input.obterString(input, "Id [0 para cancelar]: ", false);

                    if (idParaRemover.equals("0")) break;

                    boolean removido = data.remove(idParaRemover);

                    String mensagem = removido ? "Contato removido!" : "Id não encontrado!";

                    System.out.printf("%s Enter para continuar...", mensagem);
                    input.nextLine();
                    break;
                case 3:
                    // Detalhar
                    System.out.println("════════════════ Detalhar contato ══════════════════");
                    String idParaListar =
                            Input.obterString(input, "Id [0 para cancelar]: ", false);

                    if (idParaListar.equals("0")) break;

                    detalhar(idParaListar);

                    System.out.println("Enter para continuar...");
                    input.nextLine();
                    break;
                case 4:
                    // Editar
                    System.out.println("═════════════════ Editar contato ═══════════════════");
                    String idParaEditar =
                            Input.obterString(input, "Id [0 para cancelar]: ", false);

                    if (idParaEditar.equals("0")) break;

                    String novoNome = Input.obterString(input, "Novo Nome: ", true);
                    String novoTelefone = Input.obterString(input, "Novo Telefone: ", true);
                    String novoEmail = Input.obterString(input, "Novo Email: ", true);

                    Contact editedContact = new Contact(idParaEditar, novoNome, novoTelefone, novoEmail);

                    boolean editado = data.update(editedContact);

                    String editMensagem = editado ? "Contato editado!" : "Id não encontrado!";

                    System.out.printf("%s Enter para continuar...", editMensagem);
                    input.nextLine();
                    break;
                case 5:
                    // Listar
                    System.out.println("═════════════════ Listar contatos ══════════════════");
                    listar();

                    System.out.println("Enter para continuar...");
                    input.nextLine();
                    break;
                case 6:
                    // Sobre
                    Screen.exibirSobre();

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

    static void listar() {
        List<Contact> contatos = data.list();

        String format = "| %-" + COLUNA_ID_WIDTH + "s | %-" + COLUNA_NOME_WIDTH + "s |%n";

        System.out.printf(format, "ID", "Nome");
        System.out.printf("+-%s-+-%s-+%n",
                "-".repeat(COLUNA_ID_WIDTH),
                "-".repeat(COLUNA_NOME_WIDTH));

        for (Contact contact : contatos) {
            System.out.printf(format, contact.getId(), contact.getNome());
        }
    }

    static void detalhar(String idParaListar) {

        Contact contact = data.read(idParaListar);

        if (contact != null) {
            String format = "| %-" + COLUNA_ID_WIDTH + "s | %-" + COLUNA_NOME_WIDTH + "s | %-" + COLUNA_TELEFONE_WIDTH + "s | %-" + COLUNA_EMAIL_WIDTH + "s %n";

            System.out.printf(format, "ID", "Nome", "Telefone", "Email");
            System.out.printf("+-%s-+-%s-+-%s-+-%s-+%n",
                    "-".repeat(COLUNA_ID_WIDTH),
                    "-".repeat(COLUNA_NOME_WIDTH),
                    "-".repeat(COLUNA_TELEFONE_WIDTH),
                    "-".repeat(COLUNA_EMAIL_WIDTH));

            System.out.printf(format,
                    contact.getId(),
                    contact.getNome(),
                    contact.getTelefone(),
                    contact.getEmail());
        } else {
            System.out.println("Contact not found!");
        }
    }
}
