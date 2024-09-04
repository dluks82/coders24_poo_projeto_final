package ui;

import model.Contact;

import java.util.List;

public class Screen {

    // Códigos ANSI para cores
    public static final String RESET = "\u001B[0m";
    public static final String BLACK = "\u001B[30m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";

    static final int ID_COLUMN_WIDTH = 2;
    static final int NAME_COLUMN_WIDTH = 30;
    static final int PHONE_COLUMN_WIDTH = 20;
    static final int EMAIL_COLUMN_WIDTH = 30;

    public static void showMenu(int currentSize) {
        String counter = String.format("%04d", currentSize);

        System.out.println(GREEN + """
                ╔══════════════════════════════════════════════════╗
                ║             _                    _               ║
                ║            /_\\  __ _ ___ _ _  __| |__ _          ║
                ║           / _ \\/ _` / -_) ' \\/ _` / _` |         ║
                ║          /_/ \\_\\__, \\___|_||_\\__,_\\__,_|         ║
                ║                |___/                             ║
                ║          Coders 2024       versão: 1.0.0         ║
                ╠══════════════════════════════════════════════════╣
                ║                  >>>> MENU <<<<                  ║
                ║                                                  ║
                ║        [1] - Adicionar Contato                   ║
                ║        [2] - Remover Contato                     ║
                ║        [3] - Detalhar Contato                    ║
                ║        [4] - Editar Contato                      ║
                ║        [5] - Listar Contatos                     ║
                ║        [6] - Sobre                               ║
                ║                                                  ║
                ║        [9] - Sair                                ║
                ╚══════════════════════════════ Contatos""" + " " + counter + " ═════╝" + RESET);
    }

    public static void showAbout() {
        System.out.println("""
                ╔══════════════════════════════════════════════════╗
                ║             _                    _               ║
                ║            /_\\  __ _ ___ _ _  __| |__ _          ║
                ║           / _ \\/ _` / -_) ' \\/ _` / _` |         ║
                ║          /_/ \\_\\__, \\___|_||_\\__,_\\__,_|         ║
                ║                |___/                             ║
                ║          Coders 2024       versão: 1.0.0         ║
                ╠══════════════════════════════════════════════════╣
                ║                  >>>> SOBRE <<<<                 ║
                ║                                                  ║
                ║    A aplicação Agenda foi desenvolvida como      ║
                ║  projeto de conclusão do módulo 'Lógica de       ║
                ║  Programação I', no curso 'Santander Coders      ║
                ║  2024.1 - Back-End'.                             ║
                ║                                                  ║
                ║                 COLABORADORES                    ║
                ║      -  Diogo Lucas de Oliveira                  ║
                ║      -  Eloise Helena Moraes                     ║
                ║      -  Irving Lui                               ║
                ║      -  Isaque Menezes                           ║
                ║                                                  ║
                ╚══════════════════════════════════════════════════╝""");
    }

    public static void showContactDetails(Contact contact) {
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
    }

    public static void showContactList(List<Contact> contactList) {
        String format = "| %-" + ID_COLUMN_WIDTH + "s | %-" + NAME_COLUMN_WIDTH + "s |%n";

        System.out.printf(format, "ID", "Nome");
        System.out.printf("+-%s-+-%s-+%n",
                "-".repeat(ID_COLUMN_WIDTH),
                "-".repeat(NAME_COLUMN_WIDTH));

        for (Contact contact : contactList) {
            System.out.printf(format, contact.getId(), contact.getName());
        }
    }
}
