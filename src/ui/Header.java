package ui;

public class Header {
    public static void show(String loggedInName) {
        String loggedInMessage;
        if (loggedInName != null) {
            loggedInMessage = String.format("Logged in User: %-25.25s", loggedInName);
        } else {
            loggedInMessage = String.format("%-41.41s", "");
        }

        System.out.printf("""
                ╔═════════════════════════════════════════════════════════════════╗
                ║   Welcome to                                                    ║
                ║     ____          _                 ____              _         ║
                ║    / ___|___   __| | ___ _ __ ___  | __ )  __ _ _ __ | | __     ║
                ║   | |   / _ \\ / _` |/ _ \\ '__/ __| |  _ \\ / _` | '_ \\| |/ /     ║
                ║   | |__| (_) | (_| |  __/ |  \\__ \\ | |_) | (_| | | | |   <      ║
                ║    \\____\\___/ \\__,_|\\___|_|  |___/ |____/ \\__,_|_| |_|_|\\_\\     ║
                ║                                                                 ║
                ║   %s      Versão: 1.0.0  ║
                ╚═════════════════════════════════════════════════════════════════╝
                """, loggedInMessage);
    }
}
