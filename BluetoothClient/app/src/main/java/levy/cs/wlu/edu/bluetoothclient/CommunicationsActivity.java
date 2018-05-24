/*
Bluetooth communications activity

Works with BluetoothConnection to provide simple interaction with a sever over a Bluetooth socket:
seek bar (slider) sends serialized values to server; activity checks for available responses from
server.

Copyright 2018  Gunnar Bowman, Emily Boyes, Trip Calihan, Simon D. Levy, Shepherd Sims

MIT License
*/

package levy.cs.wlu.edu.bluetoothclient;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public abstract class CommunicationsActivity extends AppCompatActivity {


    private String mDeviceAddress;
    protected CommunicationsTask mBluetoothConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_communications);

        // Retrieve the address of the bluetooth device from the BluetoothListDeviceActivity
        Intent newint = getIntent();
        mDeviceAddress = newint.getStringExtra(DeviceListActivity.EXTRA_ADDRESS);

        // Create a connection to this device
        mBluetoothConnection = new CommunicationsTask(this, mDeviceAddress);
        mBluetoothConnection.execute();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mBluetoothConnection.disconnect();
    }
}
