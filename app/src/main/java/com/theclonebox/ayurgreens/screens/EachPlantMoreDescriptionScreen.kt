package com.theclonebox.ayurgreens.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.theclonebox.ayurgreens.data.eachPlantDescriptionData

@Composable
fun EachPlantMoreDescriptionScreen(navController: NavHostController) {
    val morePlantData = eachPlantDescriptionData

    Column(
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0x4D81B148))
            .verticalScroll(rememberScrollState())
    ) {
        Image(
            painter = painterResource(id = morePlantData.img),
            contentDescription = morePlantData.name,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 16.dp)
        )
        PropertyTextRow("Name: ", morePlantData.name)
        PropertyTextRow("Scientific Name: ", morePlantData.scientificName)
        PropertyTextRow("Conservation Status: ", morePlantData.conservationStatus)
        PropertyTextRow("Height : ", morePlantData.height)
        PropertyTextRow("Lifetime: ", morePlantData.lifetime)
        PropertyTextRow("Optimum Temperature: ", morePlantData.temperature)
        PropertyTextRow("Type: ", morePlantData.type)
        PropertyTextRow("Description: ", morePlantData.longDescription)
        PropertyListRow("Medicinal Uses: ", morePlantData.medicinalProperties)
        PropertyListRow("Cultural Significance: ", morePlantData.culturalSignificance)
        PropertyListRow("Historical Significance: ", morePlantData.historicalSignificance)

    }
}

@Composable
fun PropertyTextRow(label: String, value: String) {
    Row(modifier = Modifier.padding(start = 8.dp, top = 8.dp, end = 8.dp)) {
        Text(
            text = label,
            textAlign = TextAlign.Start,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 8.dp)
        )
        Text(text = value)
    }
}

@Composable
fun PropertyListRow(label: String, values: List<String>) {
    Column(modifier = Modifier.padding(start = 8.dp, top = 8.dp, end = 8.dp)) {
        Text(
            text = label,
            textAlign = TextAlign.Start,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 8.dp)
        )
        Column(modifier = Modifier.padding(start = 16.dp, top = 8.dp)) {
            values.forEach { prop ->
                Text(text = prop)
            }
        }
    }
}