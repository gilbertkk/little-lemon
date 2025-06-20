package com.example.android.littlelemon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import com.example.android.littlelemon.data.AppRepository
import com.example.android.littlelemon.data.MenuNetwork
import com.example.android.littlelemon.data.toMenuItem
import com.example.android.littlelemon.ui.theme.LittleLemonTheme
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

class MainActivity : ComponentActivity() {
    private val appRepository = AppRepository.get()

    private val client = HttpClient(Android)  {
        install(ContentNegotiation) {
            json( contentType = ContentType("text", "plain"))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            storeMenuToDatabase(getMenuFromNetwork())
        }

        enableEdgeToEdge()
        setContent {
            LittleLemonTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LittleLemonApp()
                }
            }
        }
    }

    private suspend fun getMenuFromNetwork() : MenuNetwork {
        val response: String =
            client.get("https://raw.githubusercontent.com/" +
                    "Meta-Mobile-Developer-PC/Working-With-Data-API/main/menu.json")
                .body()

        return Json.decodeFromString(response)
    }

    private suspend fun storeMenuToDatabase(menuNetwork: MenuNetwork) {
        menuNetwork.menu.forEach { menuItemNetwork ->
            appRepository.insert(menuItemNetwork.toMenuItem())
        }
    }
}