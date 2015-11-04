package sense.com.beeva.labs.sense;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.wearable.DataApi;
import com.google.android.gms.wearable.DataMap;
import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.PutDataMapRequest;
import com.google.android.gms.wearable.PutDataRequest;
import com.google.android.gms.wearable.Wearable;
import com.google.android.gms.wearable.WearableListenerService;

import java.util.Date;

import sense.com.beeva.labs.sense.dto.ThingPojo;
import sense.com.beeva.labs.sense.dto.iot.ItemPojo;

public class ListenerService extends WearableListenerService {

    private GoogleApiClient mApiClient;

    @Override
    public void onMessageReceived(MessageEvent messageEvent) {
        Log.i("test", "onMessageReceived()");
        if(messageEvent.getPath().equals("/getdata")) {
            init();
            new SensorProcedure().execute(this, new SensorCallback() {
                @Override
                public void onMeasure(ThingPojo thing, ItemPojo superMeasure) {
                    if( mApiClient != null && mApiClient.isConnected()){
                        PutDataMapRequest putDataMapReq = PutDataMapRequest.create("/sensor");
                        DataMap dataMap = putDataMapReq.getDataMap();
                        dataMap.putLong("x", new Date().getTime());

                        dataMap.putString("thing", thing.getName());
                        dataMap.putString("device", superMeasure.getDevice());
                        dataMap.putString("timestamp", superMeasure.getTimestamp());
                        dataMap.putString("temperature", superMeasure.getPayload().getReported().getAmbientTemperature());
                        dataMap.putString("humidity", superMeasure.getPayload().getReported().getHumidity());
                        dataMap.putString("pressure", superMeasure.getPayload().getReported().getPressure());
                        dataMap.putString("light", superMeasure.getPayload().getReported().getLux());

                        PutDataRequest putDataReq = putDataMapReq.asPutDataRequest();
                        PendingResult<DataApi.DataItemResult> pendingResult =
                                Wearable.DataApi.putDataItem(mApiClient, putDataReq);
                    }
                }
            });
        } else {
            super.onMessageReceived(messageEvent);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if( mApiClient != null )
            mApiClient.disconnect();
    }

    private void init(){
        mApiClient = new GoogleApiClient.Builder(this)
                .addApi( Wearable.API )
                .build();

        if( mApiClient != null && !( mApiClient.isConnected() || mApiClient.isConnecting() ) )
            mApiClient.connect();
    }


}