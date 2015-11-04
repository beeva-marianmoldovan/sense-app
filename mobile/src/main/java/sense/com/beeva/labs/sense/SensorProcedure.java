package sense.com.beeva.labs.sense;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.beeva.ubiqlibrary.privat.PrefsManager;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import java.util.HashMap;
import java.util.Map;

import sense.com.beeva.labs.sense.dto.ContextPojo;
import sense.com.beeva.labs.sense.dto.ThingPojo;
import sense.com.beeva.labs.sense.dto.iot.SensorItemsPojo;
/**
 * Created by marianclaudiu on 3/11/15.
 */
public class SensorProcedure {

    private Context context;
    private SensorCallback sensorCallback;
    private ThingPojo thing;

    final static Map<String, String> thingMap = new HashMap<String, String>();

    static {
        // Stand
        thingMap.put("5637f7860c38b2ca1c4d98ff", "b0b448bf4186");
        // Puerta
        thingMap.put("5637f7a09b63ddb262693391", "b0b448bf3285");
        // Central
        thingMap.put("5637f7909b63ddb262693390", "b0b448bf3506");
        // Pasillo
        thingMap.put("5637f7980c38b2ca1c4d9900", "b0b448d05d01");
    }

    public void execute(@NonNull Context context, @NonNull SensorCallback sensorCallback) {
        this.context = context;
        this.sensorCallback = sensorCallback;
        doSomething();
    }

    private void doSomething(){
        final String userId = PrefsManager.getStringValue(context, "user_id");

        Ion.with(context).load(Keys.UBIQONS_HOST + Keys.API_CONTEXT)
                .addQuery("user_id", userId)
                .setHeader("Authorization", "Bearer " + Keys.ADMIN_TOKEN)
                .as(ContextPojo.class)
                .setCallback(new FutureCallback<ContextPojo>() {
                    @Override
                    public void onCompleted(Exception e, ContextPojo result) {
                        if (e == null && result != null && result.getThings() != null && result.getThings().size() > 0) {
                            for (ThingPojo thingAux : result.getThings()) {
                                if ("spot".equals(thingAux.getContextLevel())) {
                                    thing = thingAux;
                                    getSensorData(thingAux.getThing());
                                    return;
                                }
                            }
                            getSensorData(null);
                        } else getSensorData(null);
                    }
                });
    }

    private void getSensorData(String device){
        JsonObject json = new JsonObject();
        json.addProperty("Limit", 1);
        json.addProperty("device", (device == null) ? thingMap.get("5637f7909b63ddb262693390") : thingMap.get(device));

        Log.wtf("XXX", json.toString());
        Ion.with(context).load("https://l8mqs9arfh.execute-api.eu-west-1.amazonaws.com/prod/sense-api")
                .setJsonObjectBody(json)
                .as(new TypeToken<SensorItemsPojo>(){})
                .setCallback(new FutureCallback<SensorItemsPojo>() {
                    @Override
                    public void onCompleted(Exception e, SensorItemsPojo result) {
                        if (result != null && result.getItems().size() > 0)
                            sensorCallback.onMeasure(thing, result.getItems().get(0));
                    }
                });

    }

}
