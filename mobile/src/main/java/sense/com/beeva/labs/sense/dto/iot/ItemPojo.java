package sense.com.beeva.labs.sense.dto.iot;

/**
 * Created by marianclaudiu on 3/11/15.
 */
public class ItemPojo {
    PayloadPojo payload;
    String timestamp, device;

    public PayloadPojo getPayload() {
        return payload;
    }

    public void setPayload(PayloadPojo payload) {
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
