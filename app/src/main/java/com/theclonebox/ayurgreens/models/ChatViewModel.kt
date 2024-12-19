package com.theclonebox.ayurgreens.models

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.content
import com.theclonebox.ayurgreens.Constants
import com.theclonebox.ayurgreens.data.ChatBotMessageStorage
import kotlinx.coroutines.launch

class ChatViewModel : ViewModel() {

    val messageList by lazy {
        mutableStateListOf<ChatBotMessageStorage>()
    }

    val generativeModel: GenerativeModel = GenerativeModel(
        modelName = "gemini-1.5-flash",
        apiKey = Constants.apiKey
    )

    fun sendMessage(question: String) {
        viewModelScope.launch {
            try {
                val chat = generativeModel.startChat(
                    history = messageList.map {
                        content(it.role) { text(it.message) }
                    }.toList()
                )

                messageList.add(ChatBotMessageStorage(question, "User"))
                messageList.add(ChatBotMessageStorage("Typing...", "Model"))
                val response = chat.sendMessage(question)
                messageList.removeLast()
                messageList.add(ChatBotMessageStorage(response.text.toString(), "Model"))
            } catch (e: Exception) {
                messageList.removeLast()
                messageList.add(ChatBotMessageStorage("Sorry, " + e.message.toString(), "Model"))
            }
        }
    }
}