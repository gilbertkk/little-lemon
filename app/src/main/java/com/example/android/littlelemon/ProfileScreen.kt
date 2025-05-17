package com.example.android.littlelemon

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource

@Composable
fun ProfileScreen(hasActions: Boolean = true, hasNavigationIcons: Boolean = true) {
    Scaffold(
        topBar = { MyTopAppBar(hasActions, hasNavigationIcons)},
        modifier = Modifier
            .fillMaxWidth()
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
        ) {
            Text(text = stringResource(R.string.profile_screen_title))
            Text(text = stringResource(R.string.profile_screen_avatar))

        }
    }
}

