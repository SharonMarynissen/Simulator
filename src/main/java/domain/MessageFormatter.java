package domain;

/**
 * Abstraction for the conversion of an incoming message to a {@link MessageDTO's}
 */
public interface MessageFormatter {
    String format(MessageDTO messageDTO) throws CommunicationException;
}
