package domain;

/**
 * Service for placing a {@link MessageDTO} on to a messaging queue
 */
public interface OutputService {
    void putMessage(MessageDTO messageDTO) throws MessageException;
    void shutdown() throws MessageException;
}
