package enums;

public enum AccountTypeOption {
    CURRENT_ACCOUNT("1", 'C', "Corrente"),
    SAVINGS_ACCOUNT("2", 'C', "Poupança");

    private final String number;
    private final char letter;
    private final String description;

    AccountTypeOption(String number, char letter, String description) {
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

    public static AccountTypeOption fromUserInput(String input) {
        for (AccountTypeOption option : values()) {
            if (option.number.equalsIgnoreCase(input) || option.letter == input.toUpperCase().charAt(0)) {
                return option;
            }
        }
        return null;
    }

}
