package enums;

public enum NotLoggedInOption {
    LOGIN("1", 'E', "Entrar"),
    REGISTER("2", 'R', "Registrar"),
    EXIT("9", 'A', "Encerrar App");

    private final String number;
    private final char letter;
    private final String description;

    NotLoggedInOption(String number, char letter, String description) {
        this.number = number;
        this.letter = letter;
        this.description = description;
    }

    public String getNumber() {
        return number;
    }

    public char getLetter() {
        return letter;
    }

    public String getDescription() {
        return description;
    }

    public String getText() {
        return String.format("[%s] - %s", number, description);
    }

    public static NotLoggedInOption fromUserInput(String input) {
        for (NotLoggedInOption option : values()) {
            if (option.number.equalsIgnoreCase(input) || option.letter == input.toUpperCase().charAt(0)) {
                return option;
            }
        }
        return null;
    }
}
