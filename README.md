This repository provides an Android app with reusable code for running a socket
client over Bluetooth.  The code derives from the term projects of four
students in Washington and Lee's Spring 2018 course 
[CSCI 251: Android App Development](http://home.wlu.edu/~levys/courses/csci251s2018/).
The students originally wrote code to support controlling a servo on a RaspberryPi 3.
Eventually I was able to factor this code into three reusable classes 
* [DeviceListActivity](https://github.com/simondlevy/BluetoothClient/blob/master/app/src/main/java/levy/cs/wlu/edu/bluetoothclient/DeviceListActivity.java):
the main (first) activity of your app. It lists the Bluetooth devices with which your Android device is paired and connecting to the device you select
* [CommunicationsTask](https://github.com/simondlevy/BluetoothClient/blob/master/app/src/main/java/levy/cs/wlu/edu/bluetoothclient/CommunicationsTask.java):
an asynchronous task (i.e., thread) for managing communications with the device to which you've connected. 
* [CommunicationsActivity](https://github.com/simondlevy/BluetoothClient/blob/master/app/src/main/java/levy/cs/wlu/edu/bluetoothclient/CommunicationsActivity.java):
supports reading from and writing to the server.  You can subclass this class to do the
