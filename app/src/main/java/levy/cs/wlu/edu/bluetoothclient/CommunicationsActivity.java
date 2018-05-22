package levy.cs.wlu.edu.bluetoothclient;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.SeekBar;

public class CommunicationsActivity extends AppCompatActivity {

    SeekBar mSpeedSeekBar;
    String mDeviceAddress;
    BluetoothConnection mBluetoothConnection;
    byte [] mBytesFromServer = new byte[100];
    int mByteIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_communications);

        // Retrieve the address of the bluetooth device from the BluetoothListDeviceActivity
        Intent newint = getIntent();
        mDeviceAddress = newint.getStringExtra(DeviceListActivity.EXTRA_ADDRESS);

        // Create a connection to this device
        mBluetoothConnection = new BluetoothConnection(this, mDeviceAddress);
        mBluetoothConnection.execute();


        mSpeedSeekBar = (SeekBar)findViewById(R.id.seekBar);

        mSpeedSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser==true) {
                    for (byte b : String.valueOf(progress).getBytes()) {
                        mBluetoothConnection.write(b);
                    }
                    mBluetoothConnection.write((byte)'.');
                    /*
                    while (mBluetoothConnection.available() > 0) {
                        int c = mBluetoothConnection.read();
                        if (c == '.') {
                            if (mByteIndex > 0) {
                                mBytesFromServer[mByteIndex+1] = 0;
                                Log.d("TAG", new String(mBytesFromServer));
                            }
                            mByteIndex = 0;
                        }
                        else {
                            mBytesFromServer[mByteIndex] = (byte)c;
                            mByteIndex++;
                        }
                    }*/
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mBluetoothConnection.disconnect();
    }

}