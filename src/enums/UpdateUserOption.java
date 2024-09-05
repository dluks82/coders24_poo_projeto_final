package enums;

public enum UpdateUserOption {
    NAME("1", 'N', "Alterar Nome"),
    PASSWORD("2", 'S', "Alterar Senha"),
    BACK("9", 'V', "Voltar");

    private final String number;
    private final char letter;
    private final String description;

    UpdateUserOption(String number, char letter, String description) {
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

    public static UpdateUserOption fromUserInput(String input) {
        for (UpdateUserOption option : values()) {
            if (option.number.equalsIgnoreCase(input) || option.letter == input.toUpperCase().charAt(0)) {
                return option;
            }
        }
        return null;
    }
}
