package adapters;

import domain.*;
import org.apache.log4j.Logger;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.util.List;

/**
 * Adapts a filereader to an {@link InputService}
 * Can be used to simulate violations
 */
public class InputFileReader implements InputService {
    private Logger logger = Logger.getLogger(InputFileReader.class);
    private String path;

    public InputFileReader(String path) {
        this.path = path;
    }

    @Override
    public void initialize(InputListener listener) throws MessageException {
        if (listener != null) {
            try {
                logger.info("Using path '" + path + "'");

                List<String> allLines = Files.readAllLines(Paths.get(path));

                for(String line : allLines){
                    String parts[] = line.split(";");
                    int cameraId = Integer.parseInt(parts[0]);
                    LocalTime timestamp = LocalTime.parse(parts[1]);
                    LincensePlate licensePlate = new LincensePlate(parts[2]);

                    listener.onReceive(new Message(cameraId, timestamp, licensePlate));

                    logger.info("Delivered message to listener");
                }
            } catch (Exception e) {
                logger.error("Exception during callback to listener", e);
            }
        }
    }

    @Override
    public void shutdown() {
        //do nothing
    }

    public String getPath() { return path; }
    public void setPath(String path) { this.path = path; }
}
