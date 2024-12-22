package blog.devjay.logistics.domain.exception;

public class UserSuspendedException extends RuntimeException {
    public UserSuspendedException() {
        super();
    }

    public UserSuspendedException(String message) {
        super(message);
    }

    public UserSuspendedException(String message, Throwable cause) {
        super(message, cause);
    }
}
