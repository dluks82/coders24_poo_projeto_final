package enums;

public enum LoggedInOption {
    OPEN_ACCOUNT("1", 'A', "Abrir Conta"),
    ACCESS_ACCOUNT("2", 'C', "Acessar Conta"),
    UPDATE_INFO("3", 'D', "Atualizar Dados Cadastrais"),
    CLOSE_ACCOUNT("4", 'E', "Encerrar Conta"),
    EXIT("9", 'S', "Sair");

    private final String number;
    private final char letter;
    private final String description;

    LoggedInOption(String number, char letter, String description) {
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

    public static LoggedInOption fromUserInput(String input) {
        for (LoggedInOption option : values()) {
            if (option.number.equalsIgnoreCase(input) || option.letter == input.toUpperCase().charAt(0)) {
                return option;
            }
        }
        return null;
    }
}
