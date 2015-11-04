package sense.com.beeva.labs.sense.dto;

import com.google.gson.annotations.SerializedName;


public class ThingPojo {

    private String thing;
    @SerializedName("context_level")
    private String contextLevel;
    private String name;

    public String getThing() {
        return thing;
    }

    public void setThing(String thing) {
        this.thing = thing;
    }

    public String getContextLevel() {
        return contextLevel;
    }

    public void setContextLevel(String contextLevel) {
        this.contextLevel = contextLevel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ThingPojo thing1 = (ThingPojo) o;

        return !(thing != null ? !thing.equals(thing1.thing) : thing1.thing != null);

    }

    @Override
    public int hashCode() {
        return thing != null ? thing.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "ThingPojo{" +
                "name='" + name + '\'' +
                '}';
    }
}
