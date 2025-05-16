package com.example.android.littlelemon

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBar(isOnBoarding: Boolean) {
    CenterAlignedTopAppBar(
        title = {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Little Lemon logo",
                modifier = Modifier
                    .fillMaxWidth(.40f)
            )
        },
        actions = {
            if (!isOnBoarding) {
                IconButton(onClick = {
                }) {
                    Image(
                        painter = painterResource(id = R.drawable.profile),
                        contentDescription = "profile Image"
                    )
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun TopAppBarPreview() {
    MyTopAppBar(false)
}

