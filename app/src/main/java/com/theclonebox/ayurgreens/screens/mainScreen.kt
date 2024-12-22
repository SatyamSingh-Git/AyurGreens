package com.theclonebox.ayurgreens.screens

import androidx.compose.animation.core.animateDpAsState
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.theclonebox.ayurgreens.AuthScreen
import com.theclonebox.ayurgreens.R
import com.theclonebox.ayurgreens.data.Plant
import com.theclonebox.ayurgreens.data.plantCategoryList
import com.theclonebox.ayurgreens.data.plantsMainScreenList
import androidx.compose.runtime.LaunchedEffect
import androidx.activity.compose.BackHandler

@Composable
fun MainScreen(modifier: Modifier = Modifier, navController: NavHostController) {
    val user = Firebase.auth.currentUser

    LaunchedEffect(user) {
        if (user != null) {
            navController.navigate("main") {
                popUpTo("authScreen") { inclusive = true }
            }
        } else {
            navController.navigate("authScreen") {
                popUpTo("main") { inclusive = true }
            }
        }
    }

    NavHost(
        navController = navController,
        startDestination = if (user != null) "main" else "authScreen"
    ) {
        composable("main") { MainContent(navController) }
        composable("authScreen") { AuthScreen(navController) }
        composable("eachPlantDescription/{plantId}") { backStackEntry ->
            EachPlantDescriptionScreen(
                plantId = backStackEntry.arguments?.getString("plantId") ?: "",
                navController = navController
            )
        }
    }
}

@Composable
fun MainContent(navController: NavHostController) {
    var recentSearchesHeight by remember { mutableStateOf(200.dp) }
    val animatedRecentSearchesHeight by animateDpAsState(
        targetValue = recentSearchesHeight,
        label = "RecentSearchesHeight"
    )

    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                val delta = available.y
                if (delta < 0) {
                    recentSearchesHeight = (recentSearchesHeight + delta.dp).coerceAtLeast(0.dp)
                } else if (delta > 0) {
                    recentSearchesHeight = (recentSearchesHeight + delta.dp).coerceAtMost(200.dp)
                }
                return Offset.Zero
            }
        }
    }

    var expanded by remember {
        mutableStateOf(false)
    }

    BackHandler {
        // Handle back press
        navController.popBackStack()
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 0.dp, start = 8.dp, end = 8.dp, bottom = 0.dp)
        ) {
            // Header
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
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = { expanded = true }) {
                        Icon(
                            painter = painterResource(id = R.drawable.person_front_view_svgrepo_com),
                            contentDescription = null,
                            modifier = Modifier.size(34.dp)
                        )
                    }
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        DropdownMenuItem(
                            text = {
                                Text(
                                    text = "Sign Out",
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFF49792B),
                                    modifier = Modifier
                                        .padding(12.dp)
                                )
                            },
                            onClick = {
                                Firebase.auth.signOut()
                                navController.navigate("authScreen") {
                                    popUpTo("mainScreen") { inclusive = true }
                                }
                                expanded = false
                            },
                            modifier = Modifier
                                .background(Color(0xFFD1E0C6))
                                .clip(RoundedCornerShape(50.dp))
                        )
                    }
                }
            }

            // Title
            Text(
                text = stringResource(R.string.MainscreenText),
                color = Color(0xff394929),
                textAlign = TextAlign.Start,
                style = TextStyle(fontSize = 28.sp),
                fontWeight = FontWeight.ExtraBold
            )

            // Categories
            Spacer(modifier = Modifier.height(16.dp))
            CategoriesRow()

            // Recent Searches Section
            Box(
                modifier = Modifier
                    .height(animatedRecentSearchesHeight)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(13.dp))
                    .background(Color.White)
            ) {
                Column {
                    Text(
                        text = "Recent Searches",
                        color = Color(0xff394929),
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        ),
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    RecentPlantsLazyRow(plantsMainScreenList, navController) // Pass navController here
                }
            }

            // Top Searches
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Top Searches",
                color = Color(0xff394929),
                style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold)
            )
            Spacer(modifier = Modifier.height(8.dp))

            // Top Searches List
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .nestedScroll(nestedScrollConnection) // Attach nested scroll
            ) {
                items(plantsMainScreenList) { plant ->
                    PlantCard(plant)
                }
            }
        }
    }
}

@Composable
fun CategoriesRow() {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
            .clip(RoundedCornerShape(30.dp))
            .background(
                Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF49792B),
                        Color(0xFF8BC34A)
                    )
                )
            )
            .drawBehind {
                drawRoundRect(
                    color = Color.Black.copy(alpha = 0.07f),
                    cornerRadius = CornerRadius(2.dp.toPx(), 2.dp.toPx()),
                    size = this.size,
                    style = Fill,
                    blendMode = BlendMode.SrcOver
                )
            },
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        items(plantCategoryList) { category ->
            CategoryButton(category)
        }
    }
}

@Composable
fun RecentPlantsLazyRow(plants: List<Plant>, navController: NavHostController) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(plants) { plant ->
            RecentPlantCard(
                name = plant.name,
                imageResourceId = plant.imageResourceId,
                onClick = { navController.navigate("eachPlantDescription/${plant.id}") }
            )
        }
    }
}

@Composable
fun RecentPlantCard(name: String, imageResourceId: Int, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .requiredWidth(117.dp)
            .requiredHeight(158.dp)
            .clip(RoundedCornerShape(24.426.dp))
            .background(
                Brush.linearGradient(
                    colors = listOf(
                        Color(0xEB8BC34A),
                        Color(0xEB578D32),

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
fun PlantCard(plant: Plant) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(2.dp)
            .clip(shape = RoundedCornerShape(25.dp))
            .background(Color(0x4D81B148))
            .shadow(1.dp)
            .border(1.dp, color = Color(0xFF394929), shape = RoundedCornerShape(25.dp)),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = plant.imageResourceId),
                contentDescription = plant.name,
                modifier = Modifier
                    .fillMaxHeight(0.9f)
                    .padding(1.dp)
            )
            Text(
                text = plant.name,
                color = Color(0xff304022),
                style = TextStyle(fontSize = 16.sp)
            )
            Icon(
                ImageVector.vectorResource(id = R.drawable.right_2_svgrepo_com),
                contentDescription = "35-arrow-right-2",
                modifier = Modifier.size(23.dp)
            )
        }
    }
}

@Composable
fun CategoryButton(text: String) {
    Box(
        modifier = Modifier
            .padding(8.dp)
            .requiredWidth(width = 135.dp)
            .requiredHeight(height = 36.dp)
            .clip(shape = RoundedCornerShape(25.dp))
            .background(color = Color(0xFFF2F6EE))
            .border(0.5.dp, color = Color(0xFF394929), shape = RoundedCornerShape(25.dp)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = Color(0xff394929),
            textAlign = TextAlign.Center,
            style = TextStyle(
                fontSize = 16.sp
            ),
            modifier = Modifier.padding(1.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    val navController = rememberNavController()
    MainScreen(modifier = Modifier, navController = navController)
}