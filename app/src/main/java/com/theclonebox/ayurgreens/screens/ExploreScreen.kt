import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.theclonebox.ayurgreens.R
import com.theclonebox.ayurgreens.screens.CategoriesRow


@Composable
fun ExploreScreen(modifier: Modifier) {

    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        ExploreTopPart()
        CategoriesRow()
        ExplorePageMainPart()
    }
}

@Composable
fun ExplorePageMainPart(modifier: Modifier = Modifier) {
    val scrollState = rememberScrollState()

    Column(modifier = Modifier
        .fillMaxHeight(0.5f)
        .fillMaxWidth(1f)
        .padding(6.dp)
        .border(2.dp, Color(0xFF608A38), RoundedCornerShape(10.dp)),
        verticalArrangement = Arrangement.SpaceAround,
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(1f)
                .padding(8.dp)
                .verticalScroll(scrollState)
        )
        {
            ExplorePageMainPartEachRow(
                "Peperomia Houseplant",
                "Crassula Houseplant",
                R.drawable.plant1,
                R.drawable.plant3
            )

            ExplorePageMainPartEachRow(
                "Asplenium Nidus",
                "Peperomia Houseplant",
                R.drawable.plant4,
                R.drawable.plant2
            )

            ExplorePageMainPartEachRow(
                "Peperomia Houseplant",
                "Crassula Houseplant",
                R.drawable.plant1,
                R.drawable.plant3
            )

            ExplorePageMainPartEachRow(
                "Asplenium Nidus",
                "Peperomia Houseplant",
                R.drawable.plant4,
                R.drawable.plant2
            )

        }

    }
    Column(modifier = Modifier
        .fillMaxSize(1f)
        .padding(6.dp)
        .border(2.dp, Color(0xFF608A38), RoundedCornerShape(10.dp))
    ) { ExplorePageMainPartEachRow(
        "Asplenium Nidus",
        "Peperomia Houseplant",
        R.drawable.plant4,
        R.drawable.plant2
    ) }
}

    @Composable
    fun ExplorePageMainPartEachRow(
        plantName1: String = "Plant Name",
        plantName2: String = "Plant Name",
        plantImage1: Int = R.drawable.ic_launcher_foreground,
        plantImage2: Int = R.drawable.ic_launcher_foreground
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Row(horizontalArrangement = Arrangement.SpaceAround, modifier = Modifier.fillMaxWidth(1f)) {
            ExplorePageMainPartEachItem(plantName1, plantImage1)
            ExplorePageMainPartEachItem(plantName2, plantImage2)
        }
    }

@Composable
fun ExplorePageMainPartEachItem(
    plantName: String,
    plantImage: Int
) {
    Box(
        modifier = Modifier
            .requiredWidth(130.dp)
            .requiredHeight(160.dp)
            .clip(RoundedCornerShape(10.dp))
            .border(1.dp, Color(0xFF5F943C), RoundedCornerShape(10.dp))
            .drawBehind {
                val shadowColor = Color(0xFF5E8611).copy(alpha = 0.2f)
                val shadowRadius = 8.dp.toPx()
                val offsetY = 8.dp.toPx()
                drawRect(
                    color = shadowColor,
                    topLeft = Offset(0f, size.height - offsetY),
                    size = Size(size.width, shadowRadius)
                )
            }
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = plantImage),
                contentDescription = null,
                modifier = Modifier.size(100.dp)
            )
            Text(
                text = plantName,
                color = Color(0xff394929),
                textAlign = TextAlign.Center,
                style = TextStyle(fontSize = 18.sp),
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun ExploreTopPart() {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp, bottom = 6.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = null,
            modifier = Modifier.size(60.dp)
        )
        Image(
            imageVector = Icons.Outlined.Settings,
            contentDescription = null,
            modifier = Modifier.size(34.dp)
        )
    }
    Text(
        text = "Let's Explore Plants",
        color = Color(0xff394929),
        textAlign = TextAlign.Start,
        style = TextStyle(fontSize = 28.sp),
        fontWeight = FontWeight.ExtraBold,
        modifier = Modifier.fillMaxWidth().padding(16.dp)
    )

}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun ExploreScreenPreview() {
    ExploreScreen(Modifier)
}


