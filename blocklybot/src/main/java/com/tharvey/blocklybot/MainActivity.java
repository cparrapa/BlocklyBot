package com.tharvey.blocklybot;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private final static String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickBLE(View v) {
        final Intent intent = new Intent(this, BLEScanActivity.class);
        startActivity(intent);
    }

    public void onClickBT(View v) {
        final Intent intent = new Intent(this, BluetoothScanActivity.class);
        startActivity(intent);
    }

    public void onClickNC(View v) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        String controller = sharedPref.getString("pref_controlType", "");
        Log.i(TAG, "Controller:" + controller);
        if (controller.equals("panel")) {
            final Intent intent = new Intent(this, RobotControlActivity.class);
            startActivity(intent);
        } else {
            final Intent intent = new Intent(this, BlocklyActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_settings:
                final Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                break;
            case R.id.menu_about:
                AboutDialog about = new AboutDialog(this);
                about.setTitle("About this app");
                about.show();
                break;
        }
        return true;
    }
}
