package sense.com.beeva.labs.sense.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ContextPojo {

    @SerializedName("user_id")
    private String userId;
    private String uuid;
    private List<ThingPojo> things;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public List<ThingPojo> getThings() {
        return things;
    }

    public void setThings(List<ThingPojo> things) {
        this.things = things;
    }

    @Override
    public String toString() {
        return "Context{" +
                "userId='" + userId + '\'' +
                ", uuid='" + uuid + '\'' +
                ", things=" + things +
                '}';
    }
}
