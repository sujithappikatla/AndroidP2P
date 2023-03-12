package com.example.p2p.view



import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ControlComponent()
{
    val windowInfo = rememberWindowInfo()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        DeviceInfoComponent(windowInfo)
        DeviceControlComponent(windowInfo)
    }
}

@Composable
fun DeviceInfoComponent(windowInfo: WindowInfo) {
    Card(
        elevation = 2.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, top = 10.dp, end = 10.dp, bottom = 10.dp)
    ) {
        if (windowInfo.screenWidthInfo is WindowInfo.WindowType.Compact)
        {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp)
            ) {
                DeviceInfoNameComponent()
                DeviceInfoDisconnectComponent()
            }
        }
        else
        {
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp)
            ) {
                DeviceInfoNameComponent()
                DeviceInfoDisconnectComponent()
            }
        }
    }

}

@Composable
fun DeviceInfoNameComponent()
{
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(10.dp)

    ) {
        Text(
            text="Example Device",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text="fe-48-d6-78-de-25",
            fontSize = 16.sp,
            color = Color.Gray
        )
    }
}

@Composable
fun DeviceInfoDisconnectComponent()
{
    OutlinedButton(
        onClick = { },
        border = BorderStroke(1.dp, Color.Red),
        shape = RoundedCornerShape(5), // = 50% percent
        // or shape = CircleShape
        colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Red)
    ){
        Icon(Icons.Filled.Logout, "disconnect icon")
        Text( text = "DisConnect",
            modifier = Modifier.padding(start = 10.dp))
    }
}


@Composable
fun DeviceControlComponent(windowInfo: WindowInfo)
{
    Card(
        elevation = 1.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            LazyColumn( ){
                item {
                    PowerControlComponent(windowInfo)
                    Divider(
                        color = Color.LightGray.copy(alpha = 0.3f),
                        modifier = Modifier
                            .fillMaxWidth()  //fill the max height
                            .width(1.dp)

                    )

                }
                item {
                    MuteControlComponent(windowInfo)
                    Divider(
                        color = Color.LightGray.copy(alpha = 0.3f),
                        modifier = Modifier
                            .fillMaxWidth()  //fill the max height
                            .width(1.dp)

                    )
                }
                item {
                    VolumeControlComponent(windowInfo)
                    Divider(
                        color = Color.LightGray.copy(alpha = 0.3f),
                        modifier = Modifier
                            .fillMaxWidth()  //fill the max height
                            .width(1.dp)

                    )
                }
                item {
                    SourceControlComponent(windowInfo)
                }
            }
        }
    }

}

@Composable
fun PowerControlComponent(windowInfo: WindowInfo)
{
    if (windowInfo.screenWidthInfo is WindowInfo.WindowType.Compact)
    {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
        ) {
            PowerOffComponent(windowInfo)
            PowerOnComponent(windowInfo)
        }
    }
    else
    {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
        ) {
            PowerOffComponent(windowInfo)
            PowerOnComponent(windowInfo)
        }
    }
}

@Composable
fun PowerOffComponent(windowInfo: WindowInfo)
{
    OutlinedButton(
        onClick = { },
        border = BorderStroke(1.dp, Color.Red),
        shape = RoundedCornerShape(5), // = 50% percent
        // or shape = CircleShape
        colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Black),
        modifier = when(windowInfo.screenWidthInfo) {
            WindowInfo.WindowType.Compact -> Modifier
                .fillMaxWidth()
                .padding(top = 5.dp)
            else -> Modifier.width(250.dp)
        }
    ){
        Icon(Icons.Filled.PowerSettingsNew, "power icon",  modifier = Modifier.size(30.dp))
        Text( text = "Power Off",
            fontSize = 16.sp,
            modifier = Modifier.padding(start = 10.dp)
        )
    }
}

@Composable
fun PowerOnComponent(windowInfo: WindowInfo)
{
    OutlinedButton(
        onClick = { },
        border = BorderStroke(1.dp, Color.Red),
        shape = RoundedCornerShape(5), // = 50% percent
        // or shape = CircleShape
        colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Black),
        modifier = when(windowInfo.screenWidthInfo) {
            WindowInfo.WindowType.Compact -> Modifier
                .fillMaxWidth()
                .padding(top = 5.dp)
            else -> Modifier.width(250.dp)
        }
    ) {
        Icon(Icons.Filled.PowerSettingsNew, "power icon", modifier = Modifier.size(30.dp))
        Text(
            text = "Power On",
            fontSize = 16.sp,
            modifier = Modifier.padding(start = 10.dp)
        )
    }

}


@Composable
fun MuteControlComponent(windowInfo: WindowInfo)
{
    if (windowInfo.screenWidthInfo is WindowInfo.WindowType.Compact)
    {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
        ) {
            MuteOffComponent(windowInfo)
            MuteOnComponent(windowInfo)
        }
    }
    else
    {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
        ) {
            MuteOffComponent(windowInfo)
            MuteOnComponent(windowInfo)
        }
    }
}


@Composable
fun MuteOffComponent(windowInfo: WindowInfo)
{
    OutlinedButton(
        onClick = { },
        border = BorderStroke(1.dp, Color.Black),
        shape = RoundedCornerShape(5), // = 50% percent
        // or shape = CircleShape
        colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Black),
        modifier = when(windowInfo.screenWidthInfo) {
            WindowInfo.WindowType.Compact -> Modifier
                .fillMaxWidth()
                .padding(top = 5.dp)
            else -> Modifier.width(250.dp)
        }
    ){
        Icon(Icons.Filled.VolumeUp, "unmute icon", modifier = Modifier.size(30.dp))
        Text( text = "UnMute",
            fontSize = 16.sp,
            modifier = Modifier.padding(start = 10.dp)
        )
    }
}

@Composable
fun MuteOnComponent(windowInfo: WindowInfo)
{
    OutlinedButton(
        onClick = { },
        border = BorderStroke(1.dp, Color.Black),
        shape = RoundedCornerShape(5), // = 50% percent
        // or shape = CircleShape
        colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Black),
        modifier = when(windowInfo.screenWidthInfo) {
            WindowInfo.WindowType.Compact -> Modifier
                .fillMaxWidth()
                .padding(top = 5.dp)
            else -> Modifier.width(250.dp)
        }
    ){
        Icon(Icons.Filled.VolumeOff, "mute icon", modifier = Modifier.size(30.dp))
        Text( text = "Mute",
            fontSize = 16.sp,
            modifier = Modifier.padding(start = 10.dp)
        )
    }

}


@Composable
fun VolumeControlComponent(windowInfo: WindowInfo)
{
    if (windowInfo.screenWidthInfo is WindowInfo.WindowType.Compact)
    {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
        ) {
            VolumeDownComponent(windowInfo)
            VolumeUpComponent(windowInfo)
        }
    }
    else
    {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
        ) {
            VolumeDownComponent(windowInfo)
            VolumeUpComponent(windowInfo)
        }
    }
}


@Composable
fun VolumeDownComponent(windowInfo: WindowInfo)
{
    OutlinedButton(
        onClick = { },
        border = BorderStroke(1.dp, Color.Black),
        shape = RoundedCornerShape(5), // = 50% percent
        // or shape = CircleShape
        colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Black),
        modifier = when(windowInfo.screenWidthInfo) {
            WindowInfo.WindowType.Compact -> Modifier
                .fillMaxWidth()
                .padding(top = 5.dp)
            else -> Modifier.width(250.dp)
        }
    ){
        Icon(Icons.Filled.Remove, "volume down icon", modifier = Modifier.size(30.dp))
        Text( text = "Volume Down",
            fontSize = 16.sp,
            modifier = Modifier.padding(start = 10.dp)
        )
    }
}

@Composable
fun VolumeUpComponent(windowInfo: WindowInfo)
{
    OutlinedButton(
        onClick = { },
        border = BorderStroke(1.dp, Color.Black),
        shape = RoundedCornerShape(5), // = 50% percent
        // or shape = CircleShape
        colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Black),
        modifier = when(windowInfo.screenWidthInfo) {
            WindowInfo.WindowType.Compact -> Modifier
                .fillMaxWidth()
                .padding(top = 5.dp)
            else -> Modifier.width(250.dp)
        }
    ){
        Icon(Icons.Filled.Add, "volume up icon", modifier = Modifier.size(30.dp))
        Text( text = "Volume Up",
            fontSize = 16.sp,
            modifier = Modifier.padding(start = 10.dp)
        )
    }
}


@Composable
fun SourceControlComponent(windowInfo: WindowInfo)
{
    if (windowInfo.screenWidthInfo is WindowInfo.WindowType.Compact)
    {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
        ) {
            SourceSelectComponent(windowInfo)
            SourceSetComponent(windowInfo)
        }
    }
    else
    {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
        ) {
            SourceSelectComponent(windowInfo)
            SourceSetComponent(windowInfo)
        }
    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SourceSelectComponent(windowInfo: WindowInfo)
{
    val listItems = arrayOf("Favorites", "Options", "Settings", "Share")

    var expanded by remember {
        mutableStateOf(false)
    }

    var selectedItem by remember {
        mutableStateOf(listItems[0])
    }


    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        },
        modifier = when(windowInfo.screenWidthInfo) {
            WindowInfo.WindowType.Compact -> Modifier
                .fillMaxWidth()
            else -> Modifier.width(250.dp)
        }
    ) {
        // text field
        TextField(
            value = selectedItem,
            onValueChange = {},
            readOnly = true,
            label = { Text(text = "Select Source") },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = expanded
                )
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors(),
            modifier = when(windowInfo.screenWidthInfo) {
                WindowInfo.WindowType.Compact -> Modifier
                    .fillMaxWidth()
                else -> Modifier.width(250.dp)
            }
        )

        // menu
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = when(windowInfo.screenWidthInfo) {
                WindowInfo.WindowType.Compact -> Modifier
                else -> Modifier.width(250.dp)
            }
        ) {
            // this is a column scope
            // all the items are added vertically
            listItems.forEach { selectedOption ->
                // menu item
                DropdownMenuItem(onClick = {
                    selectedItem = selectedOption
                    expanded = false
                }) {
                    Text(text = selectedOption)
                }
            }
        }
    }
}

@Composable
fun SourceSetComponent(windowInfo: WindowInfo)
{
    OutlinedButton(
        onClick = { },
        border = BorderStroke(1.dp, Color.Black),
        shape = RoundedCornerShape(5), // = 50% percent
        // or shape = CircleShape
        colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Black),
        modifier = when(windowInfo.screenWidthInfo) {
            WindowInfo.WindowType.Compact -> Modifier
                .fillMaxWidth()
                .padding(top = 5.dp)
            else -> Modifier.width(250.dp)
        }
    ){
        Icon(Icons.Filled.ArrowForward, "Set Source icon", modifier = Modifier.size(30.dp))
        Text( text = "Set Source",
            fontSize = 16.sp,
            modifier = Modifier.padding(start = 10.dp)
        )
    }
}