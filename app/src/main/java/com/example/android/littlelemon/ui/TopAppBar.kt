package com.example.android.littlelemon.ui

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.android.littlelemon.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(
    hasActions: Boolean = false,
    hasNavigationIcon: Boolean = false,
    navigateToUser: () -> Unit = {},
    navigateBack: () -> Unit = {},
    userProfileImage: Uri? = null
) {
    CenterAlignedTopAppBar(
        title = {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Little Lemon logo",
                modifier = Modifier
                    .fillMaxWidth(.50f)
            )
        },
        actions = {
            if (hasActions) {
                IconButton(onClick = {
                    navigateToUser()
                }) {
                    var imagePainter: Painter = painterResource(R.drawable.profile)
                    if (userProfileImage != null) {
                        imagePainter = rememberAsyncImagePainter(userProfileImage)
                    }
                    Image(
                        painter = imagePainter,
                        contentDescription = "profile Image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                    )
                }
            }
        },
        navigationIcon = {
            if (hasNavigationIcon) {
                IconButton(
                    onClick = {
                        navigateBack()
                    }
                ) {
                    Image(
                        painter = painterResource(R.drawable.arrow_back_button),
                        contentDescription = "Localized description",
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }
    )
}

