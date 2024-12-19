import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.theclonebox.ayurgreens.R
import com.theclonebox.ayurgreens.data.Plant
import com.theclonebox.ayurgreens.data.plantsMainScreenList
import com.theclonebox.ayurgreens.models.SearchViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchAndNavigationScreen(navController: NavHostController) {
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()
    val iconSize by animateDpAsState(if (isFocused) 32.dp else 22.dp)
    val focusManager = LocalFocusManager.current

    // Search bar view model
    val viewModel: SearchViewModel = viewModel()
    val searchText by viewModel.searchText.collectAsState()
    val plants by viewModel.plants.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable(
                interactionSource = interactionSource,
                indication = null
            ) {
                // Clear focus when clicking outside the TextField
                focusManager.clearFocus()
            }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {

            if (!isFocused) {

                Image(
                    painter = painterResource(id = R.drawable.ayurgreens_close_up),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    alignment = Alignment.Center
                )
            } else
            {
                Spacer(modifier = Modifier.height(32.dp))
            }
            Text(
                text = "Welcome !",
                fontSize = 22.sp,
                color = Color(0xff394929),
                textAlign = TextAlign.Start,
                style = TextStyle(fontSize = 28.sp),
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier.padding(10.dp)
            )
            Text(
                text = "What's the next search?",
                fontSize = 30.sp,
                color = Color(0xff394929),
                textAlign = TextAlign.Start,
                style = TextStyle(fontSize = 28.sp),
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier.padding(start = 12.dp)
            )
            Spacer(modifier = Modifier.padding(8.dp))
            TextField(
                value = searchText,
                onValueChange = viewModel::onSearchTextChange,
                placeholder = { Text(text = "Search...") },
                leadingIcon = {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.search_alt_2_svgrepo_com),
                        contentDescription = "Search Icon",
                        modifier = Modifier.size(iconSize)
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(6.dp)
                    .clip(MaterialTheme.shapes.extraLarge),
                interactionSource = interactionSource
            )
            Spacer(modifier = Modifier.padding(8.dp))

            if (isFocused) {
                LazyColumn {
                    items(plants) { plant ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                                .clip(MaterialTheme.shapes.medium)
                                .clickable { navController.navigate("eachPlantDescription/${plant.name}") },
                            colors = CardDefaults.cardColors(Color(0xFFF2F6EE))
                        ) {
                            Column {
                                Image(
                                    painter = painterResource(id = plant.img),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(20.dp)
                                        .clip(CircleShape),
                                )
                                Text(
                                    text = plant.name,
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xff394929),
                                    modifier = Modifier.padding(8.dp)
                                )
                                Text(
                                    text = plant.description,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Normal,
                                    color = Color(0xff394929),
                                    modifier = Modifier.padding(8.dp)
                                )
                            }
                        }
                    }
                }
            }
            if (!isFocused) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Recommended For You",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color(0xFF9D9D9D),
                    modifier = Modifier.padding(top = 20.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                TopThreeRecommendations()
                Spacer(modifier = Modifier.height(16.dp))
                RecommendedSearchesLazyRow(plantsMainScreenList, navController)
                Spacer(modifier = Modifier.height(12.dp))
            }
        }
    }
}


@Composable
fun RecommendedSearchesLazyRow(plants: List<Plant>, navController: NavHostController) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(plants) { plant ->
            RecommendedSearches(
                name = plant.name,
                imageResourceId = plant.imageResourceId,
                onClick = { navController.navigate("eachPlantDescription/${plant.id}") }
            )
        }
    }
}

@Composable
fun RecommendedSearches(name: String, imageResourceId: Int, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .requiredWidth(117.dp)
            .requiredHeight(158.dp)
            .clip(RoundedCornerShape(24.426.dp))
            .background(
                Brush.linearGradient(
                    colors = listOf(
                        Color(0xFFAFCB5A),
                        Color(0xFF60B34D),

                        ),
                )
            )
            .border(1.dp, color = Color(0xFF394929), shape = RoundedCornerShape(24.426.dp))
            .clickable { onClick() },
        contentAlignment = Alignment.TopCenter
    ) {
        Image(
            painter = painterResource(id = imageResourceId),
            contentDescription = "$name plant",
            modifier = Modifier
                .fillMaxHeight(0.75f)
                .padding(0.dp),
            contentScale = ContentScale.FillHeight
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = name,
            color = Color(0xff304022),
            style = TextStyle(
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier
                .padding(8.dp)
                .align(Alignment.BottomCenter),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun TopThreeRecommendations(){
    Row(modifier = Modifier
        .fillMaxWidth(1f)
        .horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        TopThreeRecommendationsEachBox("Tulsi")
        TopThreeRecommendationsEachBox("Aswagandha")
        TopThreeRecommendationsEachBox("Plum")
    }
}

@Composable
fun TopThreeRecommendationsEachBox(name:String){
    Box(modifier = Modifier
        .border(1.dp, Color(0x4D81B148), RoundedCornerShape(25.dp))
        .clickable { /* to do */ }
        .clip(RoundedCornerShape(25.dp)))
    {
        Text(text = name,
            fontWeight = FontWeight.SemiBold,
            fontSize = 20.sp,
            modifier = Modifier.padding(start = 8.dp, end = 8.dp, top = 3.dp, bottom = 3.dp)
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewSearchAndNavigationScreen() {
    val navController = rememberNavController()
    SearchAndNavigationScreen(navController)
}