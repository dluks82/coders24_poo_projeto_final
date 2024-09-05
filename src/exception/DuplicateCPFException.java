package exception;

public class DuplicateCPFException extends RuntimeException{
    public DuplicateCPFException() {
        super("CPF is already registered.");
    }
}
