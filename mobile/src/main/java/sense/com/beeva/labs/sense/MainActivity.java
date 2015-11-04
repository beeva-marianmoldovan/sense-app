package sense.com.beeva.labs.sense;

import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.beeva.ubiqlibrary.UbiqonsManager;

import sense.com.beeva.labs.sense.dto.ThingPojo;
import sense.com.beeva.labs.sense.dto.iot.ItemPojo;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String deviceId = Settings.Secure.getString(this.getContentResolver(), Settings.Secure.ANDROID_ID);
        UbiqonsManager.register(this, Keys.UUID, Keys.TOKEN, "sense", deviceId);

        new SensorProcedure().execute(this, new SensorCallback() {
            @Override
            public void onMeasure(ThingPojo thing, ItemPojo superMeasure) {
                Log.wtf("Hey", superMeasure.getPayload().getReported().toString());
            }
        });
    }



}