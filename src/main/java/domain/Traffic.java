package domain;

import java.time.LocalTime;

/**
 * Class responsible for the calculation of the amount of traffic: normal trafic or rush hour.
 */
public class Traffic {
    private LocalTime startRushHour = LocalTime.of(16, 00, 00);
    private LocalTime stopRushHour = LocalTime.of(19, 30, 00);
    private long sleepTime = 1000;

    public long checkTraffic() {
        LocalTime now = LocalTime.now();

        if (now.isBefore(startRushHour) || now.isAfter(stopRushHour))
            sleepTime = 3000;
        else
            sleepTime = 1000;

        return sleepTime;
    }

    public void setStartRushHour(LocalTime startRushHour)   { this.startRushHour = startRushHour; }
    public void setStopRushHour(LocalTime stopRushHour)     { this.stopRushHour = stopRushHour; }
    public LocalTime getStartRushHour()     { return startRushHour; }
    public LocalTime getStopRushHour()      { return stopRushHour; }
}
