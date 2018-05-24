/*
Bluetooth device list activity

Lists Bluetooth devices paired with your device, and attempts to connect to the device you select.

Copyright 2018  Gunnar Bowman, Emily Boyes, Trip Calihan, Simon D. Levy, Shepherd Sims

MIT License
 */

package levy.cs.wlu.edu.bluetoothclient;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;

public class DeviceListActivity extends AppCompatActivity {

    ListView mDeviceList;
    private BluetoothAdapter mBluetoothAdapter = null;
    private Set <BluetoothDevice> mPairedDevices;

    public static String EXTRA_ADDRESS = "device_address";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_list);

        mDeviceList = (ListView)findViewById(R.id.listView);

        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if(mBluetoothAdapter == null) {
            Toast.makeText(getApplicationContext(), "Bluetooth Device Not Available", Toast.LENGTH_LONG).show();
            finish();
        }
        else {
            if (mBluetoothAdapter.isEnabled()) {
                listPairedDevices();
            }
            else {
                //Ask to the user turn the bluetooth on
                Intent turnBTon = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(turnBTon,1);
            }
        }
    }

    private void listPairedDevices() {
        mPairedDevices = mBluetoothAdapter.getBondedDevices();
        ArrayList list = new ArrayList();

        if (mPairedDevices.size()>0)
        {
            for(BluetoothDevice bt : mPairedDevices)
            {
                list.add(bt.getName() + "\n" + bt.getAddress());
            }
        }
        else
        {
            Toast.makeText(getApplicationContext(), "No Paired Bluetooth Devices Found.", Toast.LENGTH_LONG).show();
        }

        final ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, list);
        mDeviceList.setAdapter(adapter);
        mDeviceList.setOnItemClickListener(myListClickListener); //Method called when the device from the list is clicked

    }

    private AdapterView.OnItemClickListener myListClickListener = new AdapterView.OnItemClickListener()
    {
        public void onItemClick (AdapterView av, View v, int arg2, long arg3)
        {
            // Get the device MAC address, the last 17 chars in the View
            String info = ((TextView) v).getText().toString();
            String address = info.substring(info.length() - 17);
            // Make an intent to start next activity.
            Intent i = new Intent(DeviceListActivity.this, MyCommunicationsActivity.class);
            //Change the activity.
            i.putExtra(EXTRA_ADDRESS, address); //this will be received at CommunicationsActivity
            startActivity(i);
        }
    };
}
