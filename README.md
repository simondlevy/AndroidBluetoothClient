This repository provides an Android app with reusable code for running a socket client over Bluetooth.
The code derives from the term projects of four students in Washington and Lee's Spring 2018 course
[CSCI 251: Android App Development](http://home.wlu.edu/~levys/courses/csci251s2018/).  The students originally
wrote code to support controlling a servo on a RaspberryPi 3. Eventually I was able to factor this code into
two reusable classes: an
[Activity](https://github.com/simondlevy/BluetoothClient/blob/master/app/src/main/java/levy/cs/wlu/edu/bluetoothclient/BluetoothDeviceListActivity.java),
for listing the Bluetooth devices with which your Android device is paired and connecting to the device you select;
and an [AsyncTask](https://github.com/simondlevy/BluetoothClient/blob/master/app/src/main/java/levy/cs/wlu/edu/bluetoothclient/BluetoothCommunicationsTask.java),
(i.e., thread)for managing communications with the device to which you've connected.

As a simple protocol, we use text messages delimited by a period ('.' character).
For those without a Raspberry Pi or servo, a simple &ldquo;call and response&rdquo;
[example](https://github.com/simondlevy/PythonBluetoothServer/blob/master/lowhigh_server.py)
allows you to try out the code on an ordinary computer: your client sends period-delimited
messages containing strings representing the values 0 through 100 ('0.', '1.',
'2.', ..., '99.', '100.'), and the server sends back 'LOW.' for values below 50,
and 'HIGH.' for values above.


