package sense.com.beeva.labs.sense;

import com.google.gson.annotations.SerializedName;

/**
 * Created by marianclaudiu on 3/11/15.
 */
public class SensorMeasure {

    String humidity, pressure;
    @SerializedName("object_temperature")
    String objectTemperature;
    String lux, temperature;
    @SerializedName("ambient_temperature")
    String ambientTemperature;

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getObjectTemperature() {
        return objectTemperature;
    }

    public void setObjectTemperature(String objectTemperature) {
        this.objectTemperature = objectTemperature;
    }

    public String getLux() {
        return lux;
    }

    public void setLux(String lux) {
        this.lux = lux;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getAmbientTemperature() {
        return ambientTemperature;
    }

    public void setAmbientTemperature(String ambientTemperature) {
        this.ambientTemperature = ambientTemperature;
    }

    @Override
    public String toString() {
        return "SensorMeasure{" +
                "humidity='" + humidity + '\'' +
                ", pressure='" + pressure + '\'' +
                ", objectTemperature='" + objectTemperature + '\'' +
                ", lux='" + lux + '\'' +
                ", temperature='" + temperature + '\'' +
                ", ambientTemperature='" + ambientTemperature + '\'' +
                '}';
    }
}
