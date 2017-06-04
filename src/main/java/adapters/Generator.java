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
    private List<LincensePlate> lincensePlates = getLincensePlates();
    private Logger logger = Logger.getLogger(Generator.class);
    private boolean stop = false;
    private Traffic traffic = new Traffic();

    @Override
    public void initialize(InputListener listener) throws MessageException {
        do {
            if (listener != null) {
                try {
                    listener.onReceive(new Message(random.nextInt(maxValue), LocalTime.now(), lincensePlates.get(random.nextInt(10))));
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

    public void setMaxValue(int maxValue) { this.maxValue = maxValue; }
    public void setStartRushHour(LocalTime startRushHour) { traffic.setStartRushHour(startRushHour); }
    public void setStopRushHour(LocalTime stopRushHour) { traffic.setStopRushHour(stopRushHour); }
    public boolean isActive() { return !stop; }

    private List<LincensePlate> getLincensePlates() {
        List<LincensePlate> plates = new ArrayList<>();
        plates.add(new LincensePlate("1-AAA-111"));
        plates.add(new LincensePlate("1-ECG-987"));
        plates.add(new LincensePlate("5-ABC-897"));
        plates.add(new LincensePlate("4-DDD-321"));
        plates.add(new LincensePlate("9-PDE-195"));
        plates.add(new LincensePlate("8-DIO-927"));
        plates.add(new LincensePlate("9-CJI-975"));
        plates.add(new LincensePlate("3-JJJ-365"));
        plates.add(new LincensePlate("4-PPP-879"));
        plates.add(new LincensePlate("1-BBB-123"));

        return plates;
    }
}
