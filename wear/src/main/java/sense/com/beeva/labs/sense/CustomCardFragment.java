package sense.com.beeva.labs.sense;

import android.os.Bundle;
import android.support.wearable.view.CardFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.wearable.DataApi;
import com.google.android.gms.wearable.DataEvent;
import com.google.android.gms.wearable.DataEventBuffer;
import com.google.android.gms.wearable.DataItem;
import com.google.android.gms.wearable.DataMap;
import com.google.android.gms.wearable.DataMapItem;
import com.google.android.gms.wearable.MessageApi;
import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.Node;
import com.google.android.gms.wearable.NodeApi;
import com.google.android.gms.wearable.Wearable;
import com.ocpsoft.pretty.time.PrettyTime;

import java.util.Date;
import java.util.Locale;


public class CustomCardFragment extends CardFragment implements GoogleApiClient.ConnectionCallbacks, DataApi.DataListener{

    private GoogleApiClient mApiClient;

    private View dataView, progressBar;
    private TextView temperature, pressure, light, humidity, thing, along;

    @Override
    public View onCreateContentView(LayoutInflater inflater,
                                    ViewGroup container,
                                    Bundle savedInstanceState) {
        initGoogleApiClient();
        View mainView = inflater.inflate(R.layout.custom_layout, container, false);
        dataView = mainView.findViewById(R.id.dataLayout);
        progressBar = mainView.findViewById(R.id.progressBar);
        setContentPadding(0,0,0,0);
        setCardMargins(0, 0, 0, 0);
        return mainView;
    }

    private void initGoogleApiClient() {
        mApiClient = new GoogleApiClient.Builder(getActivity())
                .addApi( Wearable.API )
                .addConnectionCallbacks(this)
                .build();

        if( mApiClient != null && !( mApiClient.isConnected() || mApiClient.isConnecting() ) )
            mApiClient.connect();
    }

    @Override
    public void onDestroy() {
        Wearable.DataApi.removeListener(mApiClient, this);
        if( mApiClient != null )
            mApiClient.unregisterConnectionCallbacks(this);

        super.onDestroy();
    }

    private void sendMessage() {
        new Thread( new Runnable() {
            @Override
            public void run() {
                NodeApi.GetConnectedNodesResult nodes = Wearable.NodeApi.getConnectedNodes( mApiClient ).await();
                for(Node node : nodes.getNodes()) {
                    MessageApi.SendMessageResult result = Wearable.MessageApi.sendMessage(
                            mApiClient, node.getId(), "/getdata", "get".getBytes()).await();
                }
            }
        }).start();
    }

    @Override
    public void onConnected(Bundle bundle) {
        sendMessage();
        Wearable.DataApi.addListener(mApiClient, this);
    }


    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onDataChanged(DataEventBuffer dataEvents) {
        for (DataEvent event : dataEvents) {
            if (event.getType() == DataEvent.TYPE_CHANGED) {
                // DataItem changed
                DataItem item = event.getDataItem();
                if (item.getUri().getPath().equals("/sensor")) {
                    DataMap dataMap = DataMapItem.fromDataItem(item).getDataMap();
                    Log.wtf("map", dataMap.toString());
                    showData(dataMap);
                }
            } else if (event.getType() == DataEvent.TYPE_DELETED) {
                // DataItem deleted
            }
        }
    }

    private void showData(DataMap dataMap) {
        progressBar.setVisibility(View.GONE);
        dataView.setVisibility(View.VISIBLE);

        humidity = (TextView) dataView.findViewById(R.id.humidi);
        humidity.setText(dataMap.getString("humidity") + "%");

        temperature = (TextView) dataView.findViewById(R.id.temperature);
        temperature.setText("" + (int)Double.parseDouble(dataMap.getString("temperature")) + "ºC");
        pressure = (TextView) dataView.findViewById(R.id.pressure);
        pressure.setText(dataMap.getString("pressure") + "mb");
        light = (TextView) dataView.findViewById(R.id.light);
        light.setText(dataMap.getString("light") + "lux");

        thing = (TextView) dataView.findViewById(R.id.thing);
        along = (TextView) dataView.findViewById(R.id.along);
        thing.setText(dataMap.getString("thing"));

        Date date = new Date();
        date.setTime(Long.parseLong(dataMap.getString("timestamp")));
        PrettyTime prettyTime = new PrettyTime(new Locale("es"));
        along.setText(prettyTime.format(date));
    }
}