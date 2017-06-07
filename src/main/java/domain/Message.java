package domain;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * Entity representing a camera message
 * Final class: once a message is created it doesn't need to be possible to change the message
 */
public final class Message{
    private int cameraId;
    private LocalTime timestamp;
    private LicensePlate licensePlate;

    public Message(int cameraId, LocalTime timestamp, LicensePlate licensePlate){
        this.cameraId = cameraId;
        this.timestamp = timestamp;
        this.licensePlate = licensePlate;
    }

    public int getCameraId()                { return cameraId; }
    public LocalTime getTimestamp()         { return timestamp; }
    public LicensePlate getLicensePlate()  { return licensePlate; }

    @Override
    public String toString() {
        return String.format("Camera %-5d %-12s %s", cameraId, licensePlate,
                DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM).format(timestamp));
    }
}
