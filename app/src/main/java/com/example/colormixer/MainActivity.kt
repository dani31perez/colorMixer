package com.example.colormixer
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
    var greenSlider by remember { mutableIntStateOf(0) }
    var blueSlider by remember { mutableIntStateOf(0) }

    ColorMixerScreen(
        redSlider = redSlider,
        onRedChange = { redValue -> redSlider = redValue},
        greenSlider = greenSlider,
        onGreenChange = { greenValue -> greenSlider = greenValue},
        blueSlider = blueSlider,
        onBlueChange = { blueValue -> blueSlider = blueValue}
    )
}

@Composable
fun ColorMixerScreen(
    redSlider:Int,
    onRedChange: (Int) -> Unit,
    greenSlider:Int,
    onGreenChange: (Int) -> Unit,
    blueSlider:Int,
    onBlueChange: (Int) -> Unit,
){
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ){
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Icon(
                imageVector = Icons.Default.Palette,
                contentDescription = ""
            )
            Text(
                "Color Mixer",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(10.dp,0.dp)
            )
        }

        Row (
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .background(Color(redSlider, greenSlider, blueSlider)),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ){
            Icon(
                imageVector = Icons.Default.Tune,
                contentDescription = "",
                tint = Color.White,
                modifier = Modifier.size(50.dp)
            )
        }
        SliderComponent(redSlider, onRedChange, Color.Red, "Red")
        SliderComponent(greenSlider, onGreenChange, Color.Green, "Green")
        SliderComponent(blueSlider, onBlueChange, Color.Blue, "Blue")
    }
}

@Composable
fun SliderComponent (
    slider: Int,
    onChange: (Int) -> Unit,
    color: Color,
    colorName: String
){
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Text(colorName, fontSize = 20.sp)
        Text(slider.toString(), fontSize = 20.sp)
    }
    Slider (
        value = slider.toFloat(),
        onValueChange = { onChange(it.toInt())},
        valueRange = 0f..255f,
        steps = 253,
        colors =  SliderDefaults.colors(
            thumbColor = color,
            activeTrackColor = color,
            activeTickColor = color,
            inactiveTrackColor = Color.LightGray,
            inactiveTickColor = Color.LightGray
        ),
        modifier = Modifier.fillMaxWidth()
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ColorMixerTheme {
        ColorMixerScreen(
            redSlider = 0,
            onRedChange = {},
            greenSlider =0,
            onGreenChange = {},
            blueSlider = 0,
            onBlueChange = {},
        )
    }
}