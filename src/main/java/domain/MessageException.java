package domain;

/**
 * Exception that is thrown when something went wrong during sending camera messages
 */
public class MessageException extends Exception {
    public MessageException(String message) {
        super(message);
    }

    public MessageException(String message, Throwable cause) {
        super(message, cause);
    }
}
