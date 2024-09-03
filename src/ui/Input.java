package ui;

import java.util.Scanner;

public class Input {

    public static int obterInt(Scanner scanner, String promptMessage, boolean canBeNegative) {
        while (true) {
            System.out.print(promptMessage);
            try {
                int value = Integer.parseInt(scanner.nextLine());
                if (value >= 0 || canBeNegative) return value;

                System.out.println("Cannot be negative!");

            } catch (NumberFormatException exception) {
                System.out.println("Invalid value!");
            }
        }
    }

    public static String obterString(Scanner scanner, String promptMessage, boolean canBeEmpty) {
        while (true) {
            System.out.print(promptMessage);
            String value = scanner.nextLine().trim();
            if (!value.isEmpty() || canBeEmpty) return value;

            System.out.println("Cannot be empty!");
        }
    }
}
