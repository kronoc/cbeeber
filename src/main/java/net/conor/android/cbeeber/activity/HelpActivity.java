package net.conor.android.cbeeber.activity;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import net.conor.android.cbeeber.R;
import net.conor.android.cbeeber.view.HelpFragment;

/**
 * Created by keegac01 on 28/08/2014.
 */
public class HelpActivity extends Activity implements SensorEventListener {

    private HelpFragment helpFragment;
    private SensorManager sensorManager;
    private Sensor sensor;
    private float reading;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.help);
        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.getMenuInflater().inflate(R.menu.help, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.help_menu_back:
                this.finish();
                break;
            case R.id.help_menu_sensor:
                InfoBox.showInfo(this,"Current sensor reading in lux units (m/s2):"+reading);
                break;
        }
        return true;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        reading = event.values[0];
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        //TODO
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

}