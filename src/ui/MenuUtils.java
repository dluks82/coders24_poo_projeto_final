package ui;

import java.util.List;

public class MenuUtils {

    // Códigos ANSI para cores
    private static final String RESET = "\033[0m";  // Resetar cor
    private static final String RED = "\033[31m";
    private static final String GREEN = "\033[32m";
    private static final String YELLOW = "\033[33m";
    private static final String BLUE = "\033[34m";

    // Mapeia letras para cores
    private static final String LETTER_COLOR_S = RED;
    private static final String LETTER_COLOR_X = GREEN;

    public static void showMenu(List<MenuOption> options, String title) {
        int maxLineLength = 65;
        String topLine = "╔" + "═".repeat(maxLineLength) + "╗";
        String emptyLine = "║" + " ".repeat(maxLineLength) + "║";
        String bottomLine = "╚" + "═".repeat(maxLineLength) + "╝";

        System.out.println(topLine);
        System.out.println(emptyLine);

        if (title != null && !title.isEmpty()) {
            System.out.println(prepareTitleLine(title, maxLineLength));
            System.out.println(emptyLine);
        }

        for (MenuOption option : options) {
            System.out.println(prepareOptionLine(option));
        }

        System.out.println(emptyLine);
        System.out.println(bottomLine);
    }

    private static String prepareOptionLine(MenuOption option) {
        String colorCode = getColorCode(option.getHighlightLetter());

        String textWithHighlightedLetter = option.getText().replaceFirst(
                Character.toString(option.getHighlightLetter()),
                colorCode + option.getHighlightLetter() + RESET
        );

        return String.format("║   %-70.70s ║", textWithHighlightedLetter);
    }

    private static String getColorCode(char letter) {
        return GREEN;
//        switch (letter) {
//            case 'S':
//                return LETTER_COLOR_S;
//            case 'X':
//                return LETTER_COLOR_X;
//            default:
//                return RESET; // Default color
//        }
    }

    private static String prepareTitleLine(String title, int maxLineLength) {
        return String.format("║>> %-" + (maxLineLength - 4) + "s ║", title);
    }

    public static class MenuOption {
        private String text;
        private char highlightLetter;

        public MenuOption(String text, char highlightLetter) {
            this.text = text;
            this.highlightLetter = highlightLetter;
        }

        public String getText() {
            return text;
        }

        public char getHighlightLetter() {
            return highlightLetter;
        }
    }
}
