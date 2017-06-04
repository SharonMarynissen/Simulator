package domain;

import java.time.LocalTime;

/**
 * Data Transfer Object of a {@link Message}
 */
public class MessageDTO {
    private int cameraId;
    private LincensePlate licensePlate;
    private LocalTime timestamp;


    public MessageDTO(Message message){
        this.cameraId = message.getCameraId();
        this.licensePlate = message.getLicensePlate();
        this.timestamp = message.getTimestamp();
    }

    public MessageDTO() {
    }

    public void setCameraId(int cameraId)                        { this.cameraId = cameraId; }
    public void setLicensePlate(LincensePlate licensePlate)    { this.licensePlate = licensePlate; }
    public void setTimestamp(LocalTime timestamp)              { this.timestamp = timestamp; }


    public int getCameraId()                      { return cameraId; }
    public LincensePlate getLicensePlate()        { return licensePlate; }
    public LocalTime getTimestamp()               { return timestamp; }

}
