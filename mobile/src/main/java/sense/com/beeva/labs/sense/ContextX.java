package sense.com.beeva.labs.sense;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ContextX {

    @SerializedName("user_id")
    private String userId;
    private String uuid;
    private List<Thing> things;

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

    public List<Thing> getThings() {
        return things;
    }

    public void setThings(List<Thing> things) {
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
