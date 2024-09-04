package model;

public class User {
    private String cpf;
    private String name;
    private String passwordHash;

    public User(String cpf, String name, String password) {
        this.cpf = cpf;
        this.name = name;
        this.passwordHash = "hashed" + password;
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

    public boolean validPassword(String password) {
        return passwordHash.equals("hashed" + password);
    }

    @Override
    public String toString() {
        return "User{" +
                "cpf='" + cpf + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
