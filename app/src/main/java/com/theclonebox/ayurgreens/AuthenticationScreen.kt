package com.theclonebox.ayurgreens

import android.content.Intent
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.theclonebox.ayurgreens.ui.theme.CustomFontFamilyMedium
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

@Composable
fun AuthScreen(navController: NavHostController) {
    var user by remember { mutableStateOf(Firebase.auth.currentUser) }

    val launcher = authLauncher(
        onAuthComplete = { result ->
            user = result.user
            navController.navigate("mainScreen") {
                popUpTo("loginScreen") { inclusive = true }
            }//***
        },
        onAuthError = {
            user = null
        }
    )
    val token = stringResource(id = R.string.web_id)
    val context = LocalContext.current

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF486D05),
                        Color(0xEBC0FF77),
                        Color(0xEBC0FF77),
                        Color(0xFF486D05),

                        )
                )
            )
    ) {
        if (user == null) {

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ayurgreens_close_up),
                    contentDescription = null,
                    modifier = Modifier.size(180.dp)
                )
                Text(
                    text = "Welcome to",
                    color = Color.LightGray,
                    fontWeight = FontWeight.Bold,
                    fontSize = 25.sp
                )
                Text(
                    text = "AyurGreens",
                    color = Color(0xFF394929),
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp,
                    fontFamily = CustomFontFamilyMedium
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {
                    val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestIdToken(token)
                        .requestEmail()
                        .build()
                    val gsc = GoogleSignIn.getClient(context, gso)
                    val signInIntent = gsc.signInIntent
                    launcher.launch(signInIntent)
                },
                colors = ButtonDefaults.buttonColors(Color.White)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = R.drawable.google_symbol),
                        contentDescription = null,
                        modifier = Modifier.size(30.dp)
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "  Sign In with Google",
                        color = Color.Black,
                        fontSize = 20.sp
                    )
                }
            }
        } else {
            navController.navigate("mainScreen")      //***
        }
    }
}

@Composable
fun authLauncher(
    onAuthComplete: (AuthResult) -> Unit,
    onAuthError: (ApiException) -> Unit
): ManagedActivityResultLauncher<Intent, ActivityResult> {

    val scope = rememberCoroutineScope()
    return rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)

        try {
            val account = task.getResult(ApiException::class.java)
            val credential = GoogleAuthProvider.getCredential(account.idToken!!, null)
            scope.launch {
                val authResult = Firebase.auth.signInWithCredential(credential).await()
                onAuthComplete(authResult)
            }
        } catch (e: ApiException) {
            onAuthError(e)
        }
    }
}