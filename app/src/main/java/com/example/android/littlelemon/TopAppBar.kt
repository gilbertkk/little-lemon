package com.example.android.littlelemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(hasActions: Boolean = false, hasNavigationIcon: Boolean = false, navController: NavController) {
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
                    navController.navigate(ProfileDestination.route)
                }) {
                    Image(
                        painter = painterResource(id = R.drawable.profile),
                        contentDescription = "profile Image"
                    )
                }
            }
        },
        navigationIcon = {
            if (hasNavigationIcon) {
                IconButton(
                    onClick = {
                        navController.popBackStack()
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

