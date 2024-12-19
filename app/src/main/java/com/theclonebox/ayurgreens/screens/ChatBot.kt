package com.theclonebox.ayurgreens.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.theclonebox.ayurgreens.R
import com.theclonebox.ayurgreens.data.ChatBotMessageStorage
import com.theclonebox.ayurgreens.models.ChatViewModel
import com.theclonebox.ayurgreens.ui.theme.CustomFontFamily

@Composable
fun ChatBot(modifier: Modifier = Modifier, viewModel: ChatViewModel) {
    Column(
        modifier = modifier
    ) {
        BotHeader()
        MessageList(modifier = Modifier.weight(1f), messageList = viewModel.messageList)
        MessageInput(onMessageSend = { viewModel.sendMessage(it) })


    }

}

@Composable
fun BotHeader() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0x4D81B148))
    )
    {
        Text(
            text = "Ayur Gyan",
            modifier = Modifier
                .padding(top = 30.dp, bottom = 10.dp)
                .fillMaxWidth(),
            color = Color(0xFF394929),
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            fontFamily = CustomFontFamily,
        )
    }

}

@Composable
fun MessageInput(onMessageSend: (String) -> Unit) {

    var message by remember {
        mutableStateOf("")
    }

    Row(
        modifier = Modifier
            .padding(start = 12.dp)
            .clip(RoundedCornerShape(12.dp)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            value = message,
            onValueChange = {
                message = it
            },
            label = { Text("Type a message") },
            modifier = Modifier.weight(1f)
        )

        IconButton(
            onClick = {
                if (message.isNotEmpty()) {
                    onMessageSend(message)
                    message = ""
                }
            }
        ) {
            Icon(
                imageVector = Icons.Default.Send,
                contentDescription = "Send"
            )
        }

    }
}


@Composable
fun MessageList(modifier: Modifier = Modifier, messageList: List<ChatBotMessageStorage>) {
    if (messageList.isEmpty()) {
        Column(
            modifier = modifier.fillMaxSize(1f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(id = R.drawable.rounded_person_24),
                contentDescription = "Ayur Gyani",
                modifier = Modifier.size(100.dp),
                tint = Color(0xFF8BC34A)
            )
            Text(text = "Hello, Let's Begin the conversation", fontSize = 20.sp)
        }
    } else {
        LazyColumn(
            modifier = modifier,
            reverseLayout = true
        ) {
            items(messageList.reversed()) {
                MessageRow(it)
            }
        }
    }
}


@Composable
fun MessageRow(message: ChatBotMessageStorage) {
    val isModel = message.role == "Model"

    Row(
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .background(
                    if (isModel) {
                        Brush.linearGradient(
                            colors = listOf(
                                Color(0xFF49792B),
                                Color(0xFF8BC34A)
                            )
                        )
                    } else {
                        Brush.linearGradient(
                            colors = listOf(
                                Color(0xED4CAF50),
                                Color(0xE48BC34A)
                            )
                        )
                    }
                )
                .padding(12.dp),
            contentAlignment = if (isModel) Alignment.BottomEnd else Alignment.BottomStart
        ) {
            SelectionContainer {
                Text(
                    text = message.message,
                    color = if (isModel) Color.Black else Color.White,
                )
            }

        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ChatBotPreview() {
    ChatBot(viewModel = ChatViewModel())
}