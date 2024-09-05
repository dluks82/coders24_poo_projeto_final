package model;

import util.Validator;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class User {
    private String cpf;
    private String name;
    private String password;

    public User(String cpf, String name, String password) {
        this.cpf = cpf;
        this.name = name;
        setPassword(password);
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        if (Validator.isValidPassword(password))
            this.password = hashPassword(password);
    }

    public boolean validPassword(String password) {
        return this.password.equals(hashPassword(password));
    }

    @Override
    public String toString() {
        return "User{" +
                "cpf='" + cpf + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    private static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Algoritmo de hash não suportado", e);
        }
    }
}
