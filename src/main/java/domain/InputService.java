package domain;

/**
 * Service for creating Camera {@link Message} objects and notify the listener of this event
 */
public interface InputService {
    /**
     * Method for creating a message and notify the listener of this message
     * @param listener listener that needs to be notified
     * @throws CommunicationException
     */
    void initialize(InputListener listener) throws CommunicationException;

    void shutdown();
}
