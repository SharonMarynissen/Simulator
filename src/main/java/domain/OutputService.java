package domain;

/**
 * Service for sending a {@link MessageDTO}
 */
public interface OutputService {
    void putMessage(MessageDTO messageDTO) throws MessageException;
    void shutdown() throws MessageException;
}
