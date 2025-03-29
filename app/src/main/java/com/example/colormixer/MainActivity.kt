package com.example.colormixer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.colormixer.ui.theme.ColorMixerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ColorMixerTheme {
                ColorMixerApp()
            }
        }
    }
}

@Composable
fun ColorMixerApp() {
    var redSlider by remember { mutableIntStateOf(0) }

    ColorMixerScreen(
        redSlider = redSlider,
        onRedChange = { redValue -> redSlider = redValue}
    )
}

@Composable
fun ColorMixerScreen(
    redSlider:Int,
    onRedChange: (Int) -> Unit
){
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ){
        Text("Color Mixer", style = MaterialTheme.typography.headlineMedium)
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Text("Red", fontSize = 20.sp)
            Text(redSlider.toString(), fontSize = 20.sp)
        }
        Slider (
            value = redSlider.toFloat(),
            onValueChange = { onRedChange(it.toInt())},
            valueRange = 0f..255f,
            steps = 253,
            colors =  SliderDefaults.colors(
                thumbColor = androidx.compose.ui.graphics.Color.Red,
                activeTrackColor = androidx.compose.ui.graphics.Color.Red,
                activeTickColor = androidx.compose.ui.graphics.Color.Red,
                inactiveTrackColor = androidx.compose.ui.graphics.Color.LightGray,
                inactiveTickColor = androidx.compose.ui.graphics.Color.LightGray,
                disabledThumbColor =androidx.compose.ui.graphics.Color.LightGray,
            ),
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ColorMixerTheme {
        ColorMixerScreen(
            redSlider = 0,
            onRedChange = {}
        )
    }
}