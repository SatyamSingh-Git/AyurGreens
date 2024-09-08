package com.theclonebox.ayurgreens.ScreenElements

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.theclonebox.ayurgreens.R
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable

@Composable
fun HomeScreen() {
    // TODO: Implement Home Screen
}

@Composable
fun ExploreScreen() {
    // TODO: Implement Explore Screen
}

@Composable
fun SearchScreen() {
    // TODO: Implement Search Screen
}

@Composable
fun BookmarkScreen() {
    // TODO: Implement Bookmark Screen
}

@Composable
fun ProfileScreen() {
    // TODO: Implement Profile Screen
}


@Composable
fun MainSelectedScreen() {
    val selectedItem = rememberSaveable { mutableStateOf(0) } // Default selected item

    Column(modifier = Modifier.fillMaxSize()) {
        // Display the selected screen
        when (selectedItem.value) {
            0 -> HomeScreen()
            1 -> ExploreScreen()
            2 -> SearchScreen()
            3 -> BookmarkScreen()
            4 -> ProfileScreen()
        }

        // Bottom Navigation Bar
        BottomNavigationBar(selectedItem = selectedItem)
    }
}

@Composable
fun BottomNavigationBar(selectedItem: MutableState<Int>, modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(
                elevation = 8.dp,
                shape = RoundedCornerShape(25.dp)
            ) // Add shadow with rounded corners
            .clip(RoundedCornerShape(25.dp)) // Apply rounded corners
            .background(Color.White) // Apply background color after clipping
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 0.dp, vertical = 3.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            BottomNavItem(icon = R.drawable.home_svgrepo_com, isSelected = selectedItem.value == 0) {
                selectedItem.value = 0
            }
            BottomNavItem(icon = R.drawable.explore_svgrepo_com, isSelected = selectedItem.value == 1) {
                selectedItem.value = 1
            }
            BottomNavItem(icon = R.drawable.search_alt_2_svgrepo_com, isSelected = selectedItem.value == 2) {
                selectedItem.value = 2
            }
            BottomNavItem(icon = R.drawable.bookmark_square_svgrepo_com, isSelected = selectedItem.value == 3) {
                selectedItem.value = 3
            }
            BottomNavItem(icon = R.drawable.person_front_view_svgrepo_com, isSelected = selectedItem.value == 4) {
                selectedItem.value = 4
            }
        }
    }
}

@Composable
fun BottomNavItem(icon: Int, isSelected: Boolean, onClick: () -> Unit) {
    val backgroundColor = if (isSelected) {
        Brush.linearGradient(
            colors = listOf(
                Color(0xFF4CAF50),
                Color(0xFF8BC34A),
            ),
            start = Offset(0f, 0f),
            end = Offset(100f, 100f)
        )
    } else {
        null
    }
    val iconColor = if (isSelected) Color.White else Color.Black

    Box(
        modifier = Modifier
            .size(65.dp)
            .clip(CircleShape)
            .then(
                if (backgroundColor != null) {
                    Modifier.background(backgroundColor)
                } else {
                    Modifier
                }
            )
            .clickable { onClick() }, // Add clickable modifier
        contentAlignment = Alignment.Center
    ) {
        Icon(
            ImageVector.vectorResource(id = icon),
            contentDescription = null,
            tint = iconColor,
            modifier = if (isSelected) {
                if (icon == R.drawable.search_alt_2_svgrepo_com) {
                    Modifier.size(42.dp)
                } else {
                    Modifier.size(32.dp)
                }
            } else {
                if (icon == R.drawable.person_front_view_svgrepo_com) {
                    Modifier.size(32.dp)
                } else {
                    Modifier.size(24.dp)
                }
            },
        )
    }
}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun BottomNavigationBarPreview() {
    BottomNavigationBar( rememberSaveable{ mutableStateOf(0) })
}
