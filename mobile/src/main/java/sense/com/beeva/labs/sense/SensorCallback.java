package sense.com.beeva.labs.sense;

/**
 * Created by marianclaudiu on 3/11/15.
 */
public interface SensorCallback {
    void onMeasure(Thing thing, SuperMeasure superMeasure);
}
