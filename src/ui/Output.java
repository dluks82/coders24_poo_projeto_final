package ui;

import enums.Color;
import model.TransactionHistory;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class Output {
    public static void extract(List<TransactionHistory> transactionList) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        int larguraFixa = 50;

        if (transactionList == null || transactionList.isEmpty()) {
            System.out.println("╔" + "═".repeat(larguraFixa) + "╗");
            System.out.printf("║ %-48s ║%n", "Nenhuma transação encontrada.");
            System.out.println("╚" + "═".repeat(larguraFixa) + "╝");
            return;
        }

        String accountNumber = transactionList.get(0).getAccountNumber();

        System.out.println("╔" + "═".repeat(larguraFixa) + "╗");
        System.out.printf("║ Conta: %-50s ║%n", Color.GREEN.apply(accountNumber));
        System.out.println("╠" + "═".repeat(larguraFixa) + "╣");

        transactionList.forEach(transaction -> {
            System.out.printf("║ Data/Hora: %-37s ║%n", transaction.getDateTime().format(formatter));
            System.out.printf("║ Tipo: %-42s ║%n", transaction.getTransactionType());
            System.out.printf("║ Valor: R$ %-38.2f ║%n", transaction.getAmount());  // Formata o valor com duas casas decimais
            System.out.printf("║ Status: %-40s ║%n", (transaction.isStatus() ? "Sucesso" : "Falha"));
            System.out.println("╠" + "═".repeat(larguraFixa) + "╣");
        });

        System.out.println("╚" + "═".repeat(larguraFixa) + "╝");
    }

    public static void message(String message) {
        System.out.println("║ " + message);
    }

    public static void prompt(String message) {
        System.out.print("║   -> " + Color.GREEN.apply(message));
    }

    public static void error(String message) {
        System.out.println("║>>> " + Color.RED.apply(message));
    }

    public static void info(String message) {
        System.out.println("║>>> " + Color.YELLOW.apply(message));
    }
}
