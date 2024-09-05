package ui;

import enums.Color;

import java.util.List;

public class MenuUtils {

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
        String highLightColor = Color.GREEN.getCode();
        String resetColor = Color.RESET.getCode();

        String textWithHighlightedLetter = option.getText().replaceFirst(
                Character.toString(option.getHighlightLetter()),
                highLightColor + option.getHighlightLetter() + resetColor
        );

        return String.format("║   %-70.70s ║", textWithHighlightedLetter);
    }

    private static String prepareTitleLine(String title, int maxLineLength) {
        return String.format("║>> %-" + (maxLineLength - 4) + "s ║", title);
    }

    public static class MenuOption {
        private final String text;
        private final char highlightLetter;

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
