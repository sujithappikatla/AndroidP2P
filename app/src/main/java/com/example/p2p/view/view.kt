package com.example.p2p.view



import android.R
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.p2p.viewmodel.MyViewModel


@Composable
fun ShowList(viewModel: MyViewModel?)
{

    if(viewModel!=null)
    {
        val deviceList by viewModel!!.getDeviceListLiveData().observeAsState()

        LazyColumn(modifier = Modifier.fillMaxHeight()) {
            if(deviceList!= null)
            {
                items(deviceList!!.toList()) { item ->
                    Box(modifier = Modifier.fillMaxWidth().clickable {
                        Log.d("P2P_APP","Clicked " )

                        if(item.deviceConnectionStatus == 1)
                        {
                            viewModel.disconnectToDevice()
                        }else
                        {
                            viewModel.connectToDevice(item.deviceAddress)
                        }
                    })
                    {
                        Column() {
                            Text(text = item.deviceName)
                            Text(text = item.deviceAddress)
                            Text(text = item.deviceConnectionStatus.toString())
                        }

                    }

                }
            }

        }
    }

}