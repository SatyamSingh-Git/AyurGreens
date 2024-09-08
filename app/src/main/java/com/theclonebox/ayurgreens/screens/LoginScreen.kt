package com.theclonebox.ayurgreens.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.theclonebox.ayurgreens.R


@Composable
fun AppSplashScreen(modifier: Modifier = Modifier){
    Box(modifier = modifier
        .fillMaxSize(1f)
        .clip(shape = RoundedCornerShape(20.dp))
        .background(color = Color(0xff2e481e))
    ){
        Image(painter = painterResource(id = R.drawable.background),
            contentDescription = "SplashScreen",
            modifier = Modifier.fillMaxSize(1f),
            contentScale = ContentScale.Crop,
            alignment = Alignment.Center,

        )
        Column(
            verticalArrangement = Arrangement.Bottom,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .fillMaxHeight(1f)
        ) {
            Text(
                text = stringResource(id = R.string.Ayurgreens),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 15.dp),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineLarge,
                fontSize = 50.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,

                )
            Text(text = stringResource(id = R.string.appSecondarySuheading),
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .padding(bottom = 100.dp),
                textAlign = TextAlign.Center,
                fontSize = 30.sp,
                fontFamily = MaterialTheme.typography.headlineMedium.fontFamily,
                style = TextStyle(lineHeight = 30.sp),
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
            )

        }

    }
}

@Preview(showSystemUi = true)
@Composable
fun AppSplashScreenPreview(){
    AppSplashScreen()
}
