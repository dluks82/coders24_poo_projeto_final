package ui;

import exception.DataInputInterruptedException;
import exception.InvalidPasswordException;
import util.Validator;

import java.util.Scanner;

public class Input {

    static String emptyLine = "║";

    public static int getAsInt(Scanner scanner, String promptMessage, boolean canBeNegative) {
        while (true) {
            System.out.print("║   -> " + promptMessage);
            try {
                String value = scanner.nextLine();

                if (value.equalsIgnoreCase("cancel")) throw new DataInputInterruptedException();

                int parsedValue = Integer.parseInt(value);

                if (parsedValue >= 0 || canBeNegative) {
                    return parsedValue;
                }
            } catch (NumberFormatException e) {
                System.out.print("║   -> " + "Invalid value!");
                System.out.println(emptyLine);
            }
        }
    }

    public static double getAsDouble(Scanner scanner, String promptMessage, boolean canBeNegative) {
        while (true) {
            System.out.print("║   -> " + promptMessage);
            try {
                String value = scanner.nextLine();

                if (value.equalsIgnoreCase("cancel")) throw new DataInputInterruptedException();

                double parsedValue = Double.parseDouble(value);
                if (parsedValue >= 0 || canBeNegative) {
                    return parsedValue;
                }
                System.out.print("║   -> " + "Cannot be negative!");
                System.out.println(emptyLine);
            } catch (NumberFormatException e) {
                System.out.print("║   -> " + "Invalid value!");
                System.out.println(emptyLine);
            }
        }
    }

    public static long getAsLong(Scanner scanner, String promptMessage, boolean canBeNegative) {
        while (true) {
            System.out.print("║   -> " + promptMessage);
            try {
                String value = scanner.nextLine();

                if (value.equalsIgnoreCase("cancel")) throw new DataInputInterruptedException();

                long parsedValue = Long.parseLong(value);

                if (parsedValue >= 0 || canBeNegative) {
                    return parsedValue;
                }

                System.out.print("║   -> " + "Cannot be negative!");
                System.out.println(emptyLine);
            } catch (NumberFormatException e) {
                System.out.print("║   -> " + "Invalid value!");
                System.out.println(emptyLine);
            }
        }
    }

    public static String getAsString(Scanner scanner, String promptMessage, boolean canBeEmpty) {
        while (true) {
            System.out.print("║   -> " + promptMessage);
            String value = scanner.nextLine().trim();

            if (value.equalsIgnoreCase("cancel")) throw new DataInputInterruptedException();

            if (!value.isEmpty() || canBeEmpty) {
                return value;
            }

            System.out.print("║   -> " + "Input cannot be empty!");
            System.out.println(emptyLine);
        }
    }

    public static String getAsCPF(Scanner scanner, String promptMessage, boolean canBeEmpty) {
        while (true) {
            System.out.print("║   -> " + promptMessage);
            String value = scanner.nextLine().trim();

            if (value.equalsIgnoreCase("cancel")) throw new DataInputInterruptedException();

            if (value.isEmpty() && canBeEmpty) return value;

            if (Validator.isValidCPF(value)) return value;

            System.out.println("║   -> CPF is not valid! Please try again.");
            System.out.println(emptyLine);
        }
    }

    public static String getAsPassword(Scanner scanner, String promptMessage, boolean canBeEmpty) {
        while (true) {
            System.out.print("║   -> " + promptMessage);
            try {
                String value = scanner.nextLine().trim();

                if (value.equalsIgnoreCase("cancel")) throw new DataInputInterruptedException();

                if (value.isEmpty() && canBeEmpty) return value;

                if (Validator.isValidPassword(value)) return value;

            } catch (InvalidPasswordException e) {
                System.out.println("║   -> Erro: " + e.getMessage());
                System.out.println(emptyLine);
            }
        }
    }

}
