package sense.com.beeva.labs.sense;

/**
 * Created by marianclaudiu on 3/11/15.
 */
public class SuperMeasure {
    MetaMeasure payload;
    String timestamp, device;

    public MetaMeasure getPayload() {
        return payload;
    }

    public void setPayload(MetaMeasure payload) {
        this.payload = payload;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }
}
