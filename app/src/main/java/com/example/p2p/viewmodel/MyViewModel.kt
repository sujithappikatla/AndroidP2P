package com.example.p2p.viewmodel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.p2p.model.DeviceInfo
import com.example.p2p.model.P2PController


class MyViewModel(val p2pController: P2PController?) : ViewModel() {

    public val _list = listOf(
        "A", "B", "C", "D"
    )

    public fun getDeviceListLiveData(): MutableLiveData<MutableList<DeviceInfo>> {

        return p2pController?.deviceList ?: MutableLiveData<MutableList<DeviceInfo>>()
    }

    public fun connectToDevice(deviceMac:String)
    {
        p2pController?.connectDevice(deviceMac)
    }

    public fun disconnectToDevice()
    {
        p2pController?.disconnectDevice()
    }

}

class MyViewModelFactory(private val p2pController: P2PController?) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MyViewModel(p2pController) as T
    }
}