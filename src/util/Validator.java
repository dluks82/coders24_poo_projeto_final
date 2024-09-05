package util;

import exception.InvalidPasswordException;

public class Validator {
    public static boolean isValidCPF(String cpf) {
        // Remove qualquer coisa que não seja número
        cpf = cpf.replaceAll("\\D", "");

        // Verifica se o CPF tem 11 dígitos ou é uma sequência repetida (invalida)
        if (cpf.length() != 11 || cpf.matches("(\\d)\\1{10}")) {
            return false;
        }

        // Validação dos dois dígitos verificadores
        int[] cpfDigits = new int[11];
        for (int i = 0; i < 11; i++) {
            cpfDigits[i] = Character.getNumericValue(cpf.charAt(i));
        }

        // Valida o primeiro dígito verificador
        int sum = 0;
        for (int i = 0; i < 9; i++) {
            sum += cpfDigits[i] * (10 - i);
        }
        int firstVerifier = 11 - (sum % 11);
        firstVerifier = (firstVerifier >= 10) ? 0 : firstVerifier;

        if (cpfDigits[9] != firstVerifier) {
            return false;
        }

        // Valida o segundo dígito verificador
        sum = 0;
        for (int i = 0; i < 10; i++) {
            sum += cpfDigits[i] * (11 - i);
        }
        int secondVerifier = 11 - (sum % 11);
        secondVerifier = (secondVerifier >= 10) ? 0 : secondVerifier;

        return cpfDigits[10] == secondVerifier;
    }

    public static boolean isValidPassword(String password) {
        if (password.length() < 8) {
            throw new InvalidPasswordException("A senha deve ter pelo menos 8 caracteres.");
        }

        if (!password.matches(".*[A-Z].*")) {
            throw new InvalidPasswordException("A senha deve conter pelo menos uma letra maiúscula.");
        }

        if (!password.matches(".*[a-z].*")) {
            throw new InvalidPasswordException("A senha deve conter pelo menos uma letra minúscula.");
        }

        if (!password.matches(".*\\d.*")) {
            throw new InvalidPasswordException("A senha deve conter pelo menos um número.");
        }

        if (!password.matches(".*[!@#$%^&*()-+=].*")) {
            throw new InvalidPasswordException("A senha deve conter pelo menos um caractere especial (!@#$%^&*()-+=).");
        }

        return true;
    }
}
