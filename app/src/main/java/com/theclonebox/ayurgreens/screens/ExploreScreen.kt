import android.text.TextPaint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
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
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.theclonebox.ayurgreens.R
import com.theclonebox.ayurgreens.screens.CategoriesRow
import androidx.compose.foundation.Canvas
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb



@Composable
fun ExploreScreen(modifier: Modifier, navController: NavHostController) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(start = 6.dp, end  = 6.dp)
    ) {
        ExploreTopPart()
        CategoriesRow()
        ExplorePageMainPart(navController = navController)
    }
}


@Composable
fun ExplorePageMainPart(modifier: Modifier = Modifier, navController: NavHostController) {
    val scrollState = rememberScrollState()

    Column(modifier = Modifier
        .fillMaxHeight(0.65f)
        .fillMaxWidth(1f)
        .padding(start = 6.dp, end = 6.dp)
        .background(Color(0x4D81B148), shape = RoundedCornerShape(30.dp))
        .border(2.dp, Color(0xFF608A38), RoundedCornerShape(30.dp)),
        verticalArrangement = Arrangement.SpaceAround,
    ) {
        Row {
            VerticalText("Trending")
            Column(
                modifier = Modifier.fillMaxWidth(1f)
                    .padding(8.dp)
                    .verticalScroll(scrollState)
            )
            {
                ExplorePageMainPartEachRow(
                    "Peperomia",
                    "Crassula",
                    R.drawable.plant1,
                    R.drawable.plant3,
                    navController
                )

                ExplorePageMainPartEachRow(
                    "Asplenium",
                    "Houseplant",
                    R.drawable.plant4,
                    R.drawable.plant2,
                    navController
                )

                ExplorePageMainPartEachRow(
                    "Peperomia",
                    "Crassula",
                    R.drawable.plant1,
                    R.drawable.plant3,
                    navController
                )

                ExplorePageMainPartEachRow(
                    "Asplenium",
                    "Houseplant",
                    R.drawable.plant4,
                    R.drawable.plant2,
                    navController
                )

            }

        }
    }

    Spacer(modifier = Modifier.height(6.dp))
    Column(modifier = Modifier
        .fillMaxSize(1f)
        .padding(6.dp)
        .border(2.dp, Color(0xFF608A38), RoundedCornerShape(30.dp))
        .background(Color(0x4D81B148), shape = RoundedCornerShape(30.dp))
    ) {
        Row(modifier = Modifier.fillMaxWidth(1f).padding(top = 12.dp)) {
            VerticalText("Popular")
            ExplorePageMainPartEachRow(
        "Nidus",
        "Peperomia",
        R.drawable.plant4,
        R.drawable.plant2,
        navController)
        }
    }
}

    @Composable
    fun ExplorePageMainPartEachRow(
        plantName1: String = "Plant Name",
        plantName2: String = "Plant Name",
        plantImage1: Int = R.drawable.ic_launcher_foreground,
        plantImage2: Int = R.drawable.ic_launcher_foreground,
        navController: NavHostController
    ) {
        Spacer(modifier = Modifier.height(12.dp))
        Row(horizontalArrangement = Arrangement.SpaceAround, modifier = Modifier.fillMaxWidth(1f)) {
            ExplorePageMainPartEachItem(plantName1, plantImage1, navController)
            ExplorePageMainPartEachItem(plantName2, plantImage2, navController)
        }
    }

@Composable
fun ExplorePageMainPartEachItem(
    plantName: String,
    plantImage: Int,
    navController: NavHostController
) {
    Box(
        modifier = Modifier
            .requiredWidth(130.dp)
            .requiredHeight(150.dp)
            .background(Color.White, shape = RoundedCornerShape(20.dp))
            .clip(RoundedCornerShape(10.dp))
            .border(1.dp, Color(0xFF5F943C), RoundedCornerShape(20.dp))
            .drawBehind {
                val shadowColor = Color(0xFF224B04).copy(alpha = 0.7f)
                val shadowRadius = 8.dp.toPx()
                val offsetY = 8.dp.toPx()
                drawRect(
                    color = shadowColor,
                    topLeft = Offset(0f, size.height - offsetY),
                    size = Size(size.width, shadowRadius)
                )
            }
            .clickable { navController.navigate("eachPlantMoreDescriptionScreen") }
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
                style = TextStyle(fontSize = 15.sp),
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
            .padding(top = 8.dp, start = 8.dp, end = 8.dp, bottom = 0.dp),
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
        modifier = Modifier.fillMaxWidth().padding(8.dp)
    )

}


@Composable
fun VerticalText(text: String, modifier: Modifier = Modifier) {
    Box(modifier = modifier
        .fillMaxHeight()
        .padding(18.dp),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.size(10.dp)) {
            drawIntoCanvas { canvas ->
                val paint = TextPaint().apply {
                    color = Color(0xff304022).toArgb()
                    textSize = 20.sp.toPx()
                    textAlign = android.graphics.Paint.Align.CENTER
                    typeface = android.graphics.Typeface.DEFAULT_BOLD // Make the text bold

                }
                val x = size.width
                val y = size.height /2
                canvas.nativeCanvas.save()
                canvas.nativeCanvas.rotate(-90f, x, y)
                canvas.nativeCanvas.drawText(text, x, y, paint)
                canvas.nativeCanvas.restore()
            }
        }
    }
}
@Composable
@Preview(showBackground = true, showSystemUi = true)
fun ExploreScreenPreview() {
    val navController = rememberNavController()
    ExploreScreen(Modifier, navController)
}


