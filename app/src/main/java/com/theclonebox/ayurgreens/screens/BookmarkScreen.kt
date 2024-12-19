package com.theclonebox.ayurgreens.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.theclonebox.ayurgreens.R
import com.theclonebox.ayurgreens.data.BookmarkData
import com.theclonebox.ayurgreens.data.BookmarkData1
import com.theclonebox.ayurgreens.ui.theme.CustomFontFamily

@Composable
fun BookmarkScreen(modifier : Modifier) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        BookmarkHeader()
        BookmarkPlantCategory()
        BookmarkRecentPlants()
        BookmarkPlants()
    }
}

@Composable
private fun BookmarkHeader() {
    Column(modifier = Modifier.padding(horizontal = 10.dp, vertical = 10.dp)) {
        Text(
            text = "My Bookmarks",
            fontSize = 35.sp,
            fontWeight = FontWeight.W900,
            fontFamily = CustomFontFamily,
            color = Color(0xFF394929)
        )
        Text(
            text = stringResource(R.string.NoOfBookmarks),
            fontSize = 20.sp,
            fontWeight = FontWeight.ExtraBold,
            color = Color(0xFF9D9D9D),
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}

private val categoryList = listOf("Medicinal", "Aromatic", "Spices", "Nutritional", "Adaptogenic")

@Composable
fun BookmarkPlantCategory() {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
            .clip(RoundedCornerShape(30.dp))
            .background(
                Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF49792B),
                        Color(0xFF8BC34A)
                    )
                )
            )
    ) {
        items(categoryList) { item ->
            CategoryCard(item)
        }
    }
}

@Composable
private fun CategoryCard(category: String) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .clip(RoundedCornerShape(20.dp))
            .shadow(30.dp, RoundedCornerShape(20.dp), clip = false)
            .drawBehind {
                drawRoundRect(
                    brush = Brush.radialGradient(
                        colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.5f)),
                        radius = size.minDimension * 2f
                    ),
                    topLeft = center.copy(x = size.width / 2, y = size.height / 2),
                    size = size,
                    cornerRadius = CornerRadius(size.minDimension / 2),
                )
            },
        elevation = CardDefaults.cardElevation(20.dp),
        colors = CardDefaults.cardColors(Color.White)
    ) {
        Text(
            text = category,
            modifier = Modifier.padding(8.dp),
            fontSize = 15.sp,
            color = Color(0xFF394929),
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun BookmarkRecentPlants() {
    Text(text = "Recently Bookmarked",
        fontSize = 20.sp,
        color = Color(0xFF394929),
        modifier = Modifier.padding(6.dp)
    )
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
            .clip(RoundedCornerShape(20.dp))
            .shadow(20.dp, RoundedCornerShape(20.dp), clip = false),
        elevation = CardDefaults.cardElevation(20.dp),
        colors = CardDefaults.cardColors(Color.White)
    ) {
        Column(
            modifier = Modifier.background(Color(0x4D81B148))
        ) {
            repeat(2) {
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState())) {
                    RecentBookmarkEachCard()
                    RecentBookmarkEachCard()
                    RecentBookmarkEachCard()
                }
            }
        }
    }
}

@Composable
private fun RecentBookmarkEachCard(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .padding(8.dp)
            .clip(RoundedCornerShape(20.dp))
            .shadow(20.dp, RoundedCornerShape(20.dp), clip = false)
            .border(1.dp, Color.Black, RoundedCornerShape(20.dp)),
        elevation = CardDefaults.cardElevation(defaultElevation = 24.dp),
        colors = CardDefaults.cardColors(Color(0xFFF2F6EE))
    ) {
        Row(verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween) {
            Image(
                painter = painterResource(id = R.drawable.plant1),
                contentDescription = "Plant",
                modifier = Modifier
                    .size(60.dp)
                    .padding(5.dp)
                    .clip(RoundedCornerShape(20.dp))
            )
            Text(
                text = "Ayur Greens",
                modifier = Modifier.padding(5.dp),
                fontSize = 15.sp,
                color = Color(0xFF394929)
            )
        }
    }
}

@Composable
fun BookmarkPlants() {
    Card(
        elevation = CardDefaults.cardElevation(20.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .clip(RoundedCornerShape(20.dp))
            .shadow(20.dp, RoundedCornerShape(20.dp), clip = false),
        colors = CardDefaults.cardColors(Color.White)
    ) {
        LazyRow {
            items(BookmarkData1) { item ->
                BookmarkPlantCard(item)
            }
        }
    }
}

@Composable
private fun BookmarkPlantCard(item: BookmarkData) {
    Card(
        onClick = { /*TODO*/ },
        modifier = Modifier
            .padding(16.dp)
            .fillMaxHeight(0.9f)
            .fillMaxWidth(0.6f)
            .clip(RoundedCornerShape(20.dp))
            .size(width = 170.dp, height = 180.dp)
            .border(1.dp, Color.Black, RoundedCornerShape(20.dp)),
        colors = CardDefaults.cardColors(Color(0x4D81B148))
    ) {
        Column {
            Image(
                painter = painterResource(id = item.img),
                contentDescription = item.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.7f)
                    .clip(RoundedCornerShape(20.dp))
            )
            Text(
                text = item.name,
                modifier = Modifier
                    .padding(5.dp)
                    .fillMaxWidth(),
                style = TextStyle(lineHeight = 16.sp),
                fontSize = 18.sp,
                color = Color(0xFF394929),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PreviewBookmarkScreen() {
    BookmarkScreen(modifier = Modifier)
}