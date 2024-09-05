package ui;

import enums.Color;
import exception.DataInputInterruptedException;
import exception.InvalidPasswordException;
import util.Validator;

import java.util.Scanner;

public class Input {

    static String emptyLine = "║";

    public static int getAsInt(Scanner scanner, String promptMessage, boolean canBeNegative) {
        while (true) {
            Output.prompt(promptMessage);
            try {
                String value = scanner.nextLine();

                if (value.equalsIgnoreCase("cancel")) throw new DataInputInterruptedException();

                int parsedValue = Integer.parseInt(value);

                if (parsedValue >= 0 || canBeNegative) {
                    return parsedValue;
                }
                Output.error("Não pode ser negativo!");
                System.out.println(emptyLine);
            } catch (NumberFormatException e) {
                Output.error("Valor inválido! Por favor tente novamente...");
                System.out.println(emptyLine);
            }
        }
    }

    public static double getAsDouble(Scanner scanner, String promptMessage, boolean canBeNegative) {
        while (true) {
            Output.prompt(promptMessage);
            try {
                String value = scanner.nextLine();

                if (value.equalsIgnoreCase("cancel")) throw new DataInputInterruptedException();

                double parsedValue = Double.parseDouble(value);
                if (parsedValue >= 0 || canBeNegative) {
                    return parsedValue;
                }
                Output.error("Não pode ser negativo!");
                System.out.println(emptyLine);
            } catch (NumberFormatException e) {
                Output.error("Valor inválido! Por favor tente novamente...");
                System.out.println(emptyLine);
            }
        }
    }

    public static long getAsLong(Scanner scanner, String promptMessage, boolean canBeNegative) {
        while (true) {
            Output.prompt(promptMessage);
            try {
                String value = scanner.nextLine();

                if (value.equalsIgnoreCase("cancel")) throw new DataInputInterruptedException();

                long parsedValue = Long.parseLong(value);

                if (parsedValue >= 0 || canBeNegative) {
                    return parsedValue;
                }
                Output.error("Não pode ser negativo!");
                System.out.println(emptyLine);
            } catch (NumberFormatException e) {
                Output.error("Valor inválido! Por favor, tente novamente...");
                System.out.println(emptyLine);
            }
        }
    }

    public static String getAsString(Scanner scanner, String promptMessage, boolean canBeEmpty, boolean hidden) {
        while (true) {
            Output.prompt(promptMessage);
            if (hidden) System.out.print(Color.WHITE.getCode() + Color.BG_WHITE.getCode());
            String value = scanner.nextLine().trim();
            if (hidden) System.out.print(Color.RESET.getCode());

            if (value.equalsIgnoreCase("cancel")) throw new DataInputInterruptedException();

            if (!value.isEmpty() || canBeEmpty) {
                return value;
            }
            Output.error("A entrada não pode estar vazia.");
            System.out.println(emptyLine);
        }
    }

    public static String getAsCPF(Scanner scanner, String promptMessage, boolean canBeEmpty) {
        while (true) {
            Output.prompt(promptMessage);
            String value = scanner.nextLine().trim();

            if (value.equalsIgnoreCase("cancel")) throw new DataInputInterruptedException();

            if (value.isEmpty() && canBeEmpty) return value;

            if (Validator.isValidCPF(value)) return value;

            Output.error("CPF inválido! Por favor, tente novamente...");
            System.out.println(emptyLine);
        }
    }

    public static String getAsPassword(Scanner scanner, String promptMessage, boolean canBeEmpty, boolean hidden) {
        while (true) {
            Output.prompt(promptMessage);
            try {
                if (hidden) System.out.print(Color.WHITE.getCode() + Color.BG_WHITE.getCode());
                String value = scanner.nextLine().trim();
                if (hidden) System.out.print(Color.RESET.getCode());

                if (value.equalsIgnoreCase("cancel")) throw new DataInputInterruptedException();

                if (value.isEmpty() && canBeEmpty) return value;

                if (Validator.isValidPassword(value)) return value;

            } catch (InvalidPasswordException e) {
                Output.error(e.getMessage());
                System.out.println(emptyLine);
            }
        }
    }

}
