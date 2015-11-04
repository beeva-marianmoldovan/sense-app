package sense.com.beeva.labs.sense;

import sense.com.beeva.labs.sense.dto.ThingPojo;
import sense.com.beeva.labs.sense.dto.iot.ItemPojo;

/**
 * Created by marianclaudiu on 3/11/15.
 */
public interface SensorCallback {
    void onMeasure(ThingPojo thing, ItemPojo superMeasure);
}
