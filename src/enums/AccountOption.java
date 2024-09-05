package enums;

public enum AccountOption {
    DEPOSIT("1", 'D', "Depositar"),
    WITHDRAW("2", 'S', "Sacar"),
    TRANSFER("3", 'T', "Transferir"),
    CHECK_BALANCE("4", 'C', "Consultar Saldo"),
    EXIT("9", 'V', "Voltar");

    private final String number;
    private final char letter;
    private final String description;

    AccountOption(String number, char letter, String description) {
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

    public static AccountOption fromUserInput(String input) {
        for (AccountOption option : values()) {
            if (option.number.equalsIgnoreCase(input) || option.letter == input.toUpperCase().charAt(0)) {
                return option;
            }
        }
        return null;
    }
}
