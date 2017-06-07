package adapters;

import domain.*;
import org.apache.log4j.Logger;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Implementation of an {@link InputService}
 * Generates new camera {@link Message} objects and notify it's listener
 */
public class Generator implements InputService {
    private Random random = new Random();
    private int maxValue = 50;
    private List<LicensePlate> licensePlates = getLicensePlates();
    private Logger logger = Logger.getLogger(Generator.class);
    private boolean stop = false;
    private Traffic traffic = new Traffic();

    @Override
    public void initialize(InputListener listener) throws CommunicationException {
        do {
            if (listener != null) {
                try {
                    listener.onReceive(new Message(random.nextInt(maxValue), LocalTime.now(), licensePlates.get(random.nextInt(10))));
                    Thread.sleep(traffic.checkTraffic());
                } catch (Exception e) {
                    logger.error("Exception during callback to listener", e);
                }
            }
        }
        while (!stop);
    }

    @Override
    public void shutdown() {
        stop = true;
    }

    /**
     * Setting the maxValue of a camera id
     * @param maxValue representing the maximum camera id to be generated
     */
    public void setMaxValue(int maxValue) { this.maxValue = maxValue; }
    public void setStartRushHour(LocalTime startRushHour) { traffic.setStartRushHour(startRushHour); }
    public void setStopRushHour(LocalTime stopRushHour) { traffic.setStopRushHour(stopRushHour); }
    public boolean isActive() { return !stop; }

    private List<LicensePlate> getLicensePlates() {
        List<LicensePlate> plates = new ArrayList<>();
        plates.add(new LicensePlate("1-AAA-111"));
        plates.add(new LicensePlate("1-ECG-987"));
        plates.add(new LicensePlate("5-ABC-897"));
        plates.add(new LicensePlate("4-DDD-321"));
        plates.add(new LicensePlate("9-PDE-195"));
        plates.add(new LicensePlate("8-DIO-927"));
        plates.add(new LicensePlate("9-CJI-975"));
        plates.add(new LicensePlate("3-JJJ-365"));
        plates.add(new LicensePlate("4-PPP-879"));
        plates.add(new LicensePlate("1-BBB-123"));

        return plates;
    }
}
