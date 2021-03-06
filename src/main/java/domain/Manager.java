package domain;

import org.apache.log4j.Logger;

/**
 * Mediator of coupling an {@link InputService} and an {@link OutputService}
 */
public class Manager implements InputListener {
    private InputService inputService;
    private OutputService outputService = null;
    private Logger logger = Logger.getLogger(Manager.class);

    public Manager(InputService inputService) {
        this.inputService = inputService;
    }

    @Override
    public void start() {
        try {
            inputService.initialize(this);
        } catch (CommunicationException e) {
            logger.fatal("Unable to initialize camera message generator", e);
        }
    }

    @Override
    public void stop(){
        inputService.shutdown();

        if(outputService != null)
            try {
                outputService.shutdown();
            } catch (CommunicationException e) {
                logger.warn("Unable to properly shut down communication channel");
            }

    }

    @Override
    public void onReceive(Message message) {
        try {
            logger.info("Message received from InputService: " + message.toString());

            MessageDTO messageDTO = new MessageDTO(message);

            if (outputService != null) {
                outputService.sendMessage(messageDTO);
            }
        } catch (CommunicationException e) {
            logger.error("Unable to post message to the queue", e);
        }
    }

    public OutputService getOutputService() { return outputService; }
    public void setOutputService(OutputService outputService) { this.outputService = outputService; }
}
