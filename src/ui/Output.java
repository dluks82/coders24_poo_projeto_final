package ui;

import enums.Color;

public class Output {
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
