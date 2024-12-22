package blog.devjay.logistics.domain.exception;

public class UserDeactivateException extends RuntimeException {
    public UserDeactivateException() {
        super();
    }

    public UserDeactivateException(String message) {
        super(message);
    }

    public UserDeactivateException(String message, Throwable cause) {
        super(message, cause);
    }
}
