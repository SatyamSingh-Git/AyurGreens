import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.theclonebox.ayurgreens.R
import com.theclonebox.ayurgreens.ScreenElements.BottomNavItem
import com.theclonebox.ayurgreens.ScreenElements.BottomNavigationBar

@Composable
fun SearchAndNavigationScreen() {
    val backgroundColor = Color(0xFFF5F5F5)
    val primaryTextColor = Color(0xFF4A6741)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            HeaderText(primaryTextColor)
            Spacer(modifier = Modifier.height(16.dp))
            SearchBar()
            Spacer(modifier = Modifier.height(24.dp))
            RecentSection()
            Spacer(modifier = Modifier.height(24.dp))
            TopSearchesSection()
        }
        BottomNavigationBar(rememberSaveable {
            mutableStateOf(2)
        })
    }
}

@Composable
fun HeaderText(textColor: Color) {
    Text(
        text = "We made the finding\nplants easy.",
        fontSize = 28.sp,
        fontWeight = FontWeight.Bold,
        color = textColor,
        lineHeight = 34.sp
    )
}

@Composable
fun SearchBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .clip(RoundedCornerShape(28.dp))
            .background(Color.White)
            .shadow(elevation = 8.dp, shape = RoundedCornerShape(28.dp)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = "Search plants...",
            color = Color.Gray,
            modifier = Modifier.weight(1f)
        )
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color(0xFF9CCC65), Color(0xFF8BC34A))
                    )
                )
                .shadow(
                    elevation = 8.dp,
                    shape = CircleShape,
                    spotColor = Color(0xFF8BC34A)
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = "Search",
                tint = Color.White
            )
        }
        Spacer(modifier = Modifier.width(4.dp))
    }
}


@Composable
fun RecentSection() {
    Column {
        Text(
            text = "Recent",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF4A6741)
        )
        Spacer(modifier = Modifier.height(12.dp))
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(recentPlants) { plant ->
                RecentPlantItem(plant)
            }
        }
    }
}

@Composable
fun RecentPlantItem(plant: Plant) {
    Card(
        modifier = Modifier
            .width(160.dp)
            .height(80.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = plant.imageRes),
                contentDescription = null,
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(topStart = 16.dp, bottomStart = 16.dp)),
                contentScale = ContentScale.Crop
            )
            Text(
                text = plant.name,
                modifier = Modifier.padding(horizontal = 12.dp),
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
                color = Color(0xFF4A6741)
            )
        }
    }
}

data class Plant(val name: String, val imageRes: Int)

val recentPlants = listOf(
    Plant("Peperomia Houseplant", R.drawable.plant1),
    Plant("Crassula Houseplant", R.drawable.plant4),
    Plant("Monstera Houseplant", R.drawable.plant3)
)

@Composable
fun TopSearchesSection() {
    Column {
        Text(
            text = "Top Searches",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF4A6741)
        )
        Spacer(modifier = Modifier.height(12.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            topSearchPlants.forEach { plant ->
                TopSearchItem(plant, modifier = Modifier.weight(1f))
            }
        }
    }
}

@Composable
fun TopSearchItem(plant: Plant, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.aspectRatio(0.8f),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column {
            Image(
                painter = painterResource(id = plant.imageRes),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentScale = ContentScale.Crop
            )
            Text(
                text = plant.name,
                modifier = Modifier.padding(12.dp),
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                color = Color(0xFF4A6741)
            )
        }
    }
}

val topSearchPlants = listOf(
    Plant("Peperomia Houseplant", R.drawable.plant1),
    Plant("Crassula Houseplant", R.drawable.plant2)
)




@Preview
@Composable
fun SearchAndNavigationScreenPreview() {
    SearchAndNavigationScreen()
}