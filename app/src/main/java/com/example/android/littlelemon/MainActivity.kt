package com.example.android.littlelemon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.android.littlelemon.ui.theme.LittleLemonTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LittleLemonTheme {
                ScreenContainer(false)
            }
        }
    }
}

@Composable
fun ScreenContainer(hasActions: Boolean = false, hasNavigationIcons: Boolean = false) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { MyTopAppBar(hasActions, hasNavigationIcons)},
    ) { innerPadding ->
        HomeScreen(innerPadding
        )
    }
}



@Preview(showBackground = true)
@Composable
fun MainPreview() {
    LittleLemonTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = { MyTopAppBar(true)},
        ) { innerPadding ->
            HomeScreen(innerPadding
            )
        }
    }
}