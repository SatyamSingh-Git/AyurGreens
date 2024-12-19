package com.theclonebox.ayurgreens.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.theclonebox.ayurgreens.R
import com.theclonebox.ayurgreens.data.EachPlantDescriptionData
import com.theclonebox.ayurgreens.data.eachPlantDescriptionData

@Composable
fun EachPlantDescriptionScreen(
    plantId: String,
    navController: NavHostController,
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues = PaddingValues(0.dp)
) {
    val plantData = eachPlantDescriptionData
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(paddingValues)
    ) {
        PlantInformation(
            plantData = plantData,
            navController = navController, // Pass navController here
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.65f)
                .padding(16.dp)
        )

        BottomBox(
            navController = navController,
            plantData = plantData,
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.4f)
        )
    }
}

@Composable
fun PlantInformation(
    plantData: EachPlantDescriptionData,
    navController: NavHostController, // Add navController parameter
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        PlantImage(
            plantData = plantData,
            navController = navController, // Pass navController here
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
        modifier = modifier.fillMaxWidth()
    ) {
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
            Text(
                text = plantData.name,
                color = Color(0xff394929),
                style = TextStyle(fontSize = 24.sp),
                fontWeight = FontWeight.ExtraBold
            )
            Icon(
                painter = painterResource(id = R.drawable.bookmark_svgrepo_com),
                contentDescription = null,
                tint = Color(0xff394929),
                modifier = Modifier
                    .size(24.dp)
                    .padding(0.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .clickable { /* Handle click here */ }
            )
        }

        Text(
            text = plantData.shortDescription,
            color = Color(0xff9d9d9d),
            style = TextStyle(fontSize = 18.sp),
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}

@Composable
fun BottomBox(plantData: EachPlantDescriptionData, modifier: Modifier = Modifier, navController: NavHostController) {
    Box(
        modifier = modifier
            .clip(
                shape = RoundedCornerShape(
                    topStart = 35.dp,
                    topEnd = 35.dp,
                )
            )
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF49792B),
                        Color(0xFF8BC34A)
                    )
                )
            )
            .fillMaxHeight()
    ) {
        MoreDescription(
            navController = navController,
            modifier = Modifier
                .align(Alignment.BottomEnd) // Correctly align the content
                .padding(16.dp)
        )

        HeightLifetimeConsStatus(
            modifier = Modifier
                .fillMaxSize(1f)
                .padding(16.dp)
                .align(Alignment.Center)
        )
    }
}

@Composable
fun HeightLifetimeConsStatus(modifier: Modifier = Modifier) {
    Column(modifier = Modifier.fillMaxSize()) {
        // first row of icons in the box
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 36.dp, top = 16.dp, bottom = 0.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            PlantInfoIconRow(R.drawable.height)
            PlantInfoIconRow(R.drawable.lifeline_in_a_heart_outline_svgrepo_com__1_)
            PlantInfoIconRow(R.drawable.conservation)
        }

        // first row of text in the box
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 0.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            PlantInfoTextRow("Height      ")
            PlantInfoTextRow("Lifetime")
            PlantInfoTextRow("Cons. Status")
        }
// first row of Values in the box
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            PlantInfoValueRow("110-130 cm")
            PlantInfoValueRow("10-15 years")
            PlantInfoValueRow("Least Concern")
        }

        Spacer(modifier = Modifier.size(16.dp))


//second row of icons in the box
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 36.dp, top = 16.dp, bottom = 0.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            PlantInfoIconRow(R.drawable.type)
        }


//second row of text in the box
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            PlantInfoTextRow("Type")
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            PlantInfoValueRow("Aromatic")
        }

    }

}

@Composable
fun PlantInfoIconRow(icon: Int) {
    Icon(
        painter = painterResource(id = icon),
        contentDescription = null,
        tint = Color.White,
        modifier = Modifier.size(48.dp)
    )
}

@Composable
fun PlantInfoTextRow(text: String) {
    Text(
        text = text,
        color = Color.White,
        textAlign = TextAlign.Center,
        style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Normal)
    )
}

@Composable
fun PlantInfoValueRow(text: String) {
    Text(
        text = text,
        color = Color.White,
        textAlign = TextAlign.Center,
        style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.ExtraBold)
    )
}


@Composable
fun MoreDescription(modifier: Modifier = Modifier, navController: NavHostController) {
    Box(
        modifier = modifier
            .shadow(5.dp, shape = RoundedCornerShape(15.dp))
            .clip(RoundedCornerShape(15.dp))
            .padding(bottom = 0.dp)
            .background(color = Color(0xFF558E32))
            .clickable { navController.navigate("eachPlantMoreDescription") }
    ) {
        Text(
            text = "More Description",
            color = Color.White,
            style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(20.dp)
        )
    }
}

@Composable
fun PlantImage(
    plantData: EachPlantDescriptionData,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(15.dp))
            .background(color = Color.White)
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "Back",
            modifier = Modifier
                .padding(16.dp)
                .clickable { navController.popBackStack() }
        )

        Icon(
            painter = painterResource(id = R.drawable.right_2_svgrepo_com),
            contentDescription = null,
            tint = Color(0xff394929),
            modifier = Modifier
                .size(36.dp)
                .padding(0.dp)
                .align(Alignment.CenterEnd)
                .clip(RoundedCornerShape(15.dp))
                .clickable { /* Handle click here */ }
        )

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
    val navController = rememberNavController() // Create a NavController for preview
    EachPlantDescriptionScreen("1", navController = navController)
}