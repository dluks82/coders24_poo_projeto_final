package ui;

public class Screen {

    static final int ID_COLUMN_WIDTH = 2;
    static final int NAME_COLUMN_WIDTH = 30;
    static final int PHONE_COLUMN_WIDTH = 20;
    static final int EMAIL_COLUMN_WIDTH = 30;

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

    public static void clear() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            for (int i = 0; i < 50; i++) {
                System.out.println();
            }
        }
    }
}
