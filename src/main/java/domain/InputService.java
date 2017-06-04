package domain;

/**
 * Service for creating Camera {@link Message} objects and notify the listener of this event
 */
public interface InputService {
    /**
     * Method for creating a message and notify the listener of this
     * @param listener
     * @throws MessageException
     */
    void initialize(InputListener listener) throws MessageException;

    void shutdown();
}
