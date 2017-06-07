package domain;

/**
 * Abstraction of a service for sending a {@link MessageDTO}
 */
public interface OutputService {
    /**
     * Method to send the message to the output service
     * @param messageDTO needed to be send
     * @throws CommunicationException when something went wrong during sending sending
     */
    void sendMessage(MessageDTO messageDTO) throws CommunicationException;

    /**
     * Closing the connection with the output service
     * @throws CommunicationException when something went wrong during closing the connection with the output service
     */
    void shutdown() throws CommunicationException;
}
