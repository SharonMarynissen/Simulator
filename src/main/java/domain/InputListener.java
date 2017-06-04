package domain;

/**
 * Call back interface for incoming messages
 */
public interface InputListener {
    void start();

    void stop();

    /**
     * Methode called every time a {@link Message} is received
     * @param message
     */
    void onReceive(Message message);
}
