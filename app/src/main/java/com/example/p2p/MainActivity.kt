package com.example.p2p

import android.content.BroadcastReceiver
import android.content.Context
import android.content.IntentFilter
import android.net.wifi.p2p.WifiP2pManager
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.p2p.ui.theme.P2PTheme
import androidx.lifecycle.ViewModelProvider
import com.example.p2p.model.P2PController
import com.example.p2p.view.ShowList
import com.example.p2p.viewmodel.MyViewModel
import com.example.p2p.viewmodel.MyViewModelFactory


class MainActivity : ComponentActivity() {

    val manager: WifiP2pManager? by lazy(LazyThreadSafetyMode.NONE) {
        getSystemService(Context.WIFI_P2P_SERVICE) as WifiP2pManager?
    }

    var channel: WifiP2pManager.Channel? = null
    var receiver: BroadcastReceiver? = null
    var intentFilter:IntentFilter? = null
    var viewModel: MyViewModel? = null

    fun initializeP2P()
    {
        channel = manager?.initialize(this, mainLooper, null)
        channel?.also { channel ->
            receiver = manager?.let { P2PController(it, channel, this) }
            viewModel =
                ViewModelProvider(
                    this,
                    MyViewModelFactory(receiver as P2PController?)
                )[MyViewModel::class.java]
        }

        intentFilter = IntentFilter().apply {
            addAction(WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION)
            addAction(WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION)
            addAction(WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION)
            addAction(WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION)
        }

        Log.d("P2P_APP","P2P Initialization Done" );

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeP2P()

        setContent {
            P2PTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ShowList(viewModel)
                }
            }
        }
        Log.d("P2P_APP","onCreate" );
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
        Log.d("P2P_APP","onresume" );
        receiver?.also { receiver ->
            registerReceiver(receiver, intentFilter)
        }
    }

    override fun onPause() {
        super.onPause()
        Log.d("P2P_APP","onPause" );
        receiver?.also { receiver ->
            unregisterReceiver(receiver)
        }
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    P2PTheme {
        Greeting("Android")
    }
}