// File: app/src/main/java/com/theclonebox/ayurgreens/screens/EachPlantDescriptionScreen.kt
package com.theclonebox.ayurgreens.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.theclonebox.ayurgreens.ScreenElements.BottomNavigationBar
import com.theclonebox.ayurgreens.data.EachPlantDescriptionData
import com.theclonebox.ayurgreens.data.eachPlantDescriptionData

@Composable
fun EachPlantDescriptionScreen() {
    val plantData = eachPlantDescriptionData
    Column(
        modifier = Modifier
            .fillMaxSize() // Ensure the composable fills the entire screen
            .background(color = Color.White)
    ) {
        PlantInformation(
            plantData = plantData,
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.65f)
                .padding(16.dp)
        )

        BottomBox(
            plantData = plantData,
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.35f)
        )

        BottomNavigationBar(
            rememberSaveable {
                mutableStateOf(0)
            })
            }
    }

@Composable
fun PlantInformation(plantData: EachPlantDescriptionData, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
    ) {
        PlantImage(
            plantData = plantData,
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.7f)
        )

        PlantNameAndDescription(
            plantData = plantData,
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.3f)
                .padding(top = 16.dp)
        )
    }
}

@Composable
fun PlantNameAndDescription(plantData: EachPlantDescriptionData, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = plantData.name,
            color = Color(0xff394929),
            style = TextStyle(fontSize = 30.sp),
            fontWeight = FontWeight.ExtraBold
        )

        Text(
            text = plantData.shortDescription,
            color = Color(0xff9d9d9d),
            style = TextStyle(fontSize = 20.sp),
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}

@Composable
fun BottomBox(plantData: EachPlantDescriptionData, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .clip(
                shape = RoundedCornerShape(
                    topStart = 35.dp,
                    topEnd = 35.dp
                )
            )
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF558E32),
                        Color(0xFF558E32)
                    )
                )
            )
            .fillMaxHeight()
    ) {
        TypeAndLifeTime(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(top = 16.dp, start = 16.dp)
        )

        HeightAndTemperature(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(top = 48.dp, start = 16.dp)
        )

        MoreDescription(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(top = 80.dp, start = 16.dp)
        )
    }
}

@Composable
fun TypeAndLifeTime(modifier: Modifier = Modifier) {
    Row(modifier = modifier) {
        Text(
            text = "Type",
            color = Color.White,
            style = TextStyle(fontSize = 20.sp)
        )

        Text(
            text = "LifeTime",
            color = Color.White,
            style = TextStyle(fontSize = 20.sp),
            modifier = Modifier.padding(start = 100.dp)
        )
    }
}

@Composable
fun HeightAndTemperature(modifier: Modifier = Modifier) {
    Row(modifier = modifier) {
        Box(
            modifier = Modifier.size(128.dp, 63.dp)
        ) {
            Text(
                text = "Height",
                color = Color.White,
                style = TextStyle(fontSize = 20.sp),
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(start = 19.dp)
            )

            Text(
                text = "110 - 120cm",
                color = Color.White,
                textAlign = TextAlign.Center,
                style = TextStyle(fontSize = 20.sp),
                modifier = Modifier.align(Alignment.Center)
            )
        }

        Text(
            text = "Temperature",
            color = Color.White,
            style = TextStyle(fontSize = 20.sp),
            modifier = Modifier.padding(start = 20.dp)
        )
    }
}

@Composable
fun MoreDescription(modifier: Modifier = Modifier) {
    Text(
        text = "This plant makes your life day and night beautiful by providing continuous fresh air around.",
        color = Color.White,
        style = TextStyle(fontSize = 20.sp),
        modifier = modifier.padding(top = 16.dp, start = 16.dp)
    )
}

@Composable
fun PlantImage(plantData: EachPlantDescriptionData, modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(15.dp))
            .background(color = Color.White)
    ) {
        Image(
            painter = painterResource(id = plantData.img),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize(0.7f)
                .clip(RoundedCornerShape(15.dp))
                .padding(top = 36.dp)
                .align(Alignment.Center)
        )
    }
}

@Preview
@Composable
fun AndroidLarge3Preview() {
    EachPlantDescriptionScreen()
}
