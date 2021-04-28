package course.springdata.introlab.exceptions;

public class NonExistingEntityException extends RuntimeException {
    public NonExistingEntityException() {
    }

    public NonExistingEntityException(String message) {
        super(message);
    }

    public NonExistingEntityException(String message, Throwable cause) {
        super(message, cause);
    }

    public NonExistingEntityException(Throwable cause) {
        super(cause);
    }
}
