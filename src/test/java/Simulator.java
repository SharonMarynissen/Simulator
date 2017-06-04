import adapters.Generator;
import adapters.MessageBroker;
import adapters.XMLMessageFormatter;
import domain.Manager;
import domain.MessageFormatter;
import domain.OutputService;
import java.time.LocalTime;

/**
 * Manual main test application
 */
public class Simulator {
    public static void main(String[] args) {
        String uri = "amqp://garalhym:6TYjhiUm_wXY47k0MZ8YQGuLYAtS-65U@puma.rmq.cloudamqp.com/garalhym";
        Generator inputService = new Generator();
        inputService.setStartRushHour(LocalTime.of(11,20,50));

        //InputService inputService = new InputFileReader("src\\test\\resources\\cameraMessages.txt");
        MessageFormatter formatter = new XMLMessageFormatter();
        OutputService outputService = new MessageBroker(uri, "CAMERA MESSAGES", formatter);

        Manager manager = new Manager(inputService);
        manager.setOutputService(outputService);
        manager.start();
    }
}
