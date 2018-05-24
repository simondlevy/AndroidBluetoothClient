# Android Bluetooth Client
<img src="logo.png" width=400>
This repository provides an Android app with reusable code for running a socket
client over Bluetooth.  The code derives from the term projects of four
students in Washington and Lee's Spring 2018 course 
<a href="https://home.wlu.edu/~levys/courses/csci251s2018">CSCI 251: Android App Development</a>.
The students originally wrote code to control a servo connected to a RaspberryPi 3, which acted as the server.
Eventually I was able to factor this code into three reusable classes 

* [DeviceListActivity](https://github.com/simondlevy/AndroidBluetoothClient/blob/master/BluetoothClient/app/src/main/java/levy/cs/wlu/edu/bluetoothclient/DeviceListActivity.java):
the main (first) activity of your app. It lists the Bluetooth devices with which your Android device is paired and connects to the device you select.

* [CommunicationsTask](https://github.com/simondlevy/AndroidBluetoothClient/blob/master/BluetoothClient/app/src/main/java/levy/cs/wlu/edu/bluetoothclient/CommunicationsTask.java):
an asynchronous task (i.e., thread) for managing communications with the device to which you've connected. 

* [CommunicationsActivity](https://github.com/simondlevy/AndroidBluetoothClient/blob/master/BluetoothClient/app/src/main/java/levy/cs/wlu/edu/bluetoothclient/CommunicationsActivity.java):
an abstract class that supports reading from and writing to the server via its <tt>mBluetoothConnection</tt> object.  

An example subclass, 
[MyCommunicationsActivity](https://github.com/simondlevy/AndroidBluetoothClient/blob/master/BluetoothClient/app/src/main/java/levy/cs/wlu/edu/bluetoothclient/MyCommunicationsActivity.java),
provides a seek-bar (slider) and text widget to send and receive values from your server.

## Python server

The easiest way to try out this app is with the [Python Bluetooth server](https://github.com/simondlevy/PythonBluetoothServer)
that we developed to work with it.  This server uses the same simple protocol (period-delimited messages) as the code in the
<b>MyCommunicationsActivity</b> class.
