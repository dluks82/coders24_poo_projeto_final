package ui;

import enums.Color;

import java.util.List;

public class MenuUtils {

    public static void showMenu(List<MenuOption> options, String title, String subtitle) {
        int maxLineLength = 65;
//        String topLine = "╔" + "═".repeat(maxLineLength) + "╗";
        String emptyLine = "║" + " ".repeat(maxLineLength) + "║";
        String bottomLine = "╚" + "═".repeat(maxLineLength) + "╝";

//        System.out.println(topLine);
//        System.out.println(emptyLine);

        if ((title != null && !title.isEmpty()) || (subtitle != null && !subtitle.isEmpty())) {
            if (title != null && !title.isEmpty()) {
                System.out.println(prepareTitleLine(title, maxLineLength));
            }
            if (subtitle != null && !subtitle.isEmpty()) {
                System.out.println(prepareSubtitleLine(subtitle, maxLineLength));
            }
            System.out.println(emptyLine);
        }

        for (MenuOption option : options) {
            System.out.println(prepareOptionLine(option));
        }

        System.out.println(emptyLine);
        System.out.println(bottomLine);
    }

    private static String prepareTitleLine(String title, int maxLineLength) {
        return String.format("║>> %-" + (maxLineLength - 4) + "s ║", title);
    }

    private static String prepareSubtitleLine(String subtitle, int maxLineLength) {
        return String.format("║   %-" + (maxLineLength - 4) + "s ║", subtitle);
    }

    private static String prepareOptionLine(MenuOption option) {
        String highLightColor = Color.GREEN.getCode();
        String resetColor = Color.RESET.getCode();

        String optionText = option.getDescription().replaceFirst(
                Character.toString(option.getOptionChar()),
                highLightColor + option.getOptionChar() + resetColor
        );

        return String.format("║   %-70.70s ║", optionText);
    }

    public static class MenuOption {
        private final String description;
        private final char optionChar;
        private final Object value;

        public MenuOption(String description, char optionChar, Object value) {
            this.description = description;
            this.optionChar = optionChar;
            this.value = value;
        }

        public String getDescription() {
            return description;
        }

        public char getOptionChar() {
            return optionChar;
        }

        public Object getValue() {
            return value;
        }
    }
}
