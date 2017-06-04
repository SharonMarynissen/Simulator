package domain;

/**
 * Abstraction for the conversion of incoming message to {@link MessageDTO's}
 */
public interface MessageFormatter {
    String format(MessageDTO messageDTO) throws MessageException;
}
