package com.example.p2p.model


import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.wifi.p2p.WifiP2pConfig
import android.net.wifi.p2p.WifiP2pDeviceList
import android.net.wifi.p2p.WifiP2pManager
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.p2p.MainActivity


class DeviceInfo {
    var deviceName : String = ""
    var deviceAddress : String = ""
    var deviceConnectionStatus : Int = 3
}


class P2PController(
    private val manager: WifiP2pManager,
    private val channel: WifiP2pManager.Channel,
    private val activity: MainActivity
) : BroadcastReceiver() {

    //    val deviceList: Collection<WifiP2pDevice> = ArrayList<WifiP2pDevice>()
//    var flag:Boolean = false
    public val deviceList: MutableLiveData<MutableList<DeviceInfo>> by lazy {
        MutableLiveData<MutableList<DeviceInfo>>()
    }

    inner class PeerListenerCallback : WifiP2pManager.PeerListListener
    {
        override fun onPeersAvailable(_deviceList: WifiP2pDeviceList?) {
            Log.d("P2P_APP","deviceList size : "+ _deviceList?.deviceList?.size )

            val _dlist:MutableList<DeviceInfo> = mutableListOf()
            deviceList.value?.clear()

            _deviceList?.deviceList?.map {  device ->

                val d:DeviceInfo = DeviceInfo()
                d.deviceAddress =  device.deviceAddress
                d.deviceName = device.deviceName
                d.deviceConnectionStatus = device.status

                _dlist.add(d)

                Log.d("P2P_APP","Device Info : "+ device.toString() )
                Log.d("P2P_APP","############################" )
            }
            deviceList.postValue(null)
            if(_dlist.size > 0)
            {
                deviceList.postValue(_dlist)
            }
        }

    }

    inner class DeviceConnectCallback : WifiP2pManager.ActionListener
    {
        override fun onSuccess() {
            Log.d("P2P_APP"," Device Connection Success" )
        }

        override fun onFailure(errorCode: Int) {
            Log.d("P2P_APP"," Device Connection Failure : "+errorCode )
        }
    }

    inner class DeviceDisConnectCallback : WifiP2pManager.ActionListener
    {
        override fun onSuccess() {
            Log.d("P2P_APP"," Device DisConnect Success" )
        }

        override fun onFailure(errorCode: Int) {
            Log.d("P2P_APP"," Device DisConnection Failure : "+errorCode )
        }
    }

    fun connectDevice(deviceMAC : String) {
        Log.d("P2P_APP"," Connecting to : "+deviceMAC )
        val config: WifiP2pConfig = WifiP2pConfig()
        config.deviceAddress = deviceMAC
        channel.also { channel ->
            manager.connect(channel, config, DeviceConnectCallback())
        }
    }

    fun disconnectDevice() {
        Log.d("P2P_APP"," DisConnecting ")
        channel.also { channel ->
            manager.cancelConnect(channel,DeviceDisConnectCallback())
        }
    }

    override fun onReceive(context: Context, intent: Intent) {
        val action: String? = intent.action
        when (action) {
            WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION -> {
                // Check to see if Wi-Fi is enabled and notify appropriate activity
                val state = intent.getIntExtra(WifiP2pManager.EXTRA_WIFI_STATE, -1)
                when (state) {
                    WifiP2pManager.WIFI_P2P_STATE_ENABLED -> {
                        Log.d("P2P_APP","Wifi p2p enabled" )
                        manager.discoverPeers(channel, object : WifiP2pManager.ActionListener {
                            override fun onSuccess() {
                                Log.d("P2P_APP","discoverPeers Success" )
                            }

                            override fun onFailure(reasonCode: Int) {
                                Log.d("P2P_APP"," discoverPeersFailed" )
                            }
                        })
                    }
                    else -> {
                        Log.d("P2P_APP","Wifi p2p NOT enabled" )
                    }
                }
            }
            WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION -> {
                // Call WifiP2pManager.requestPeers() to get a list of current peers
                Log.d("P2P_APP","WIFI_P2P_PEERS_CHANGED_ACTION" )
                manager.requestPeers(channel, PeerListenerCallback());
            }
            WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION -> {
                // Respond to new connection or disconnections
                Log.d("P2P_APP","WIFI_P2P_CONNECTION_CHANGED_ACTION" )

            }
            WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION -> {
                // Respond to this device's wifi state changing
                Log.d("P2P_APP","WIFI_P2P_THIS_DEVICE_CHANGED_ACTION" )
            }
        }
    }
}