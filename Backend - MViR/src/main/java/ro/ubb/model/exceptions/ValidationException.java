package ro.ubb.model.exceptions;

public class ValidationException extends RuntimeException {
    public ValidationException(String mess) {
        super(mess);
    }
}
