package course.springdata.jsondemo.exception;

public class NonExcistingEntityException extends RuntimeException {
    public NonExcistingEntityException() {
    }

    public NonExcistingEntityException(String message) {
        super(message);
    }

    public NonExcistingEntityException(String message, Throwable cause) {
        super(message, cause);
    }

    public NonExcistingEntityException(Throwable cause) {
        super(cause);
    }
}
