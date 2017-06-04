package domain;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * Entity representing a camera message
 */
public final class Message{
    private int cameraId;
    private LocalTime timestamp;
    private LincensePlate licensePlate;

    public Message(int cameraId, LocalTime timestamp, LincensePlate lincensePlate){
        this.cameraId = cameraId;
        this.timestamp = timestamp;
        this.licensePlate = lincensePlate;
    }

    public int getCameraId()                { return cameraId; }
    public LocalTime getTimestamp()         { return timestamp; }
    public LincensePlate getLicensePlate()  { return licensePlate; }

    @Override
    public String toString() {
        return String.format("Camera %-5d %-12s %s", cameraId, licensePlate,
                DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM).format(timestamp));
    }
}
