package ui;

import java.util.Scanner;

public class Input {

    static String emptyLine = "║";

    public static int getAsInt(Scanner scanner, String promptMessage, boolean canBeNegative) {
        while (true) {
//            System.out.println(emptyLine);
            System.out.print("║   -> " + promptMessage);
            try {
                int value = Integer.parseInt(scanner.nextLine());
                if (value >= 0 || canBeNegative) {
                    return value;
                }
            } catch (NumberFormatException e) {
                System.err.print("║   -> " + "Invalid value!");
                scanner.nextLine(); // TODO: improve later
            }
        }
    }

    public static double getAsDouble(Scanner scanner, String promptMessage, boolean canBeNegative) {
        while (true) {
//            System.out.println(emptyLine);
            System.out.print("║   -> " + promptMessage);
            try {
                double value = Double.parseDouble(scanner.nextLine());
                if (value >= 0 || canBeNegative) {
                    return value;
                }
                System.err.print("║   -> " + "Cannot be negative!");
                scanner.nextLine(); // TODO: improve later

            } catch (NumberFormatException e) {
                System.err.print("║   -> " + "Invalid value!");
                scanner.nextLine(); // TODO: improve later
            }
        }
    }

    public static long getAsLong(Scanner scanner, String promptMessage, boolean canBeNegative) {
        while (true) {
//            System.out.println(emptyLine);
            System.out.print("║   -> " + promptMessage);
            try {
                long value = Long.parseLong(scanner.nextLine());
                if (value >= 0 || canBeNegative) {
                    return value;
                }
                System.err.print("║   -> " + "Cannot be negative!");
                scanner.nextLine(); // TODO: improve later

            } catch (NumberFormatException e) {
                System.err.print("║   -> " + "Invalid value!");
                scanner.nextLine(); // TODO: improve later
            }
        }
    }

    public static String getAsString(Scanner scanner, String promptMessage, boolean canBeEmpty) {
        while (true) {
//            System.out.println(emptyLine);
            System.out.print("║   -> " + promptMessage);
            String value = scanner.nextLine().trim();
            if (!value.isEmpty() || canBeEmpty) {
                return value;
            }
            System.err.print("║   -> " + "Input cannot be empty!");
            scanner.nextLine(); // TODO: improve later
        }
    }

    public static int getInt(Scanner scanner, String promptMessage, boolean canBeNegative) {
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

    public static String getString(Scanner scanner, String promptMessage, boolean canBeEmpty) {
        while (true) {
            System.out.print(promptMessage);
            String value = scanner.nextLine().trim();
            if (!value.isEmpty() || canBeEmpty) return value;

            System.out.println("Cannot be empty!");
        }
    }
}
