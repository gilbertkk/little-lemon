package com.example.android.littlelemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBar() {
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
            IconButton(onClick = {}) {
                Image(
                    painter = painterResource(id = R.drawable.profile),
                    contentDescription = "profile Image"
                )
            }
        }
    )
//    Row (
//        verticalAlignment = Alignment.CenterVertically,
//        horizontalArrangement = Arrangement.End,
//       modifier = Modifier
//           .fillMaxWidth()
//    ){
//        Row(
//            horizontalArrangement = Arrangement.SpaceBetween,
//            verticalAlignment = Alignment.CenterVertically,
//            modifier = Modifier
//                .fillMaxWidth(.6f)
//        ) {
//            Image(
//                painter = painterResource(id = R.drawable.logo),
//                contentDescription = "Little Lemon logo"
//            )
//            IconButton(onClick = {}) {
//                Image(
//                    painter = painterResource(id = R.drawable.profile),
//                    contentDescription = "profile Image",
//                    modifier = Modifier
//                        .size(32.dp)
//                )
//            }
//        }
//
//    }
}

@Preview(showBackground = true)
@Composable
fun TopAppBarPreview() {
    MyTopAppBar()
}

