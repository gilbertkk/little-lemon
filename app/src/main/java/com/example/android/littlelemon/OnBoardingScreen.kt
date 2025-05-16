package com.example.android.littlelemon

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.android.littlelemon.ui.theme.LittleLemonColor
import com.example.android.littlelemon.ui.theme.LittleLemonTextStyle
import com.example.android.littlelemon.ui.theme.LittleLemonTheme

@Composable
fun OnBoardingScreen(paddingValues: PaddingValues) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .padding(paddingValues)
            .verticalScroll(rememberScrollState())
    ) {
        HomeScreenUpper()
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp)
        ) {
            Text(
                text = stringResource(R.string.onboarding_name),
                style = LittleLemonTextStyle.leadText,
                color = LittleLemonColor.highlight2,
                modifier = Modifier
                    .padding(top = 8.dp),
            )
            OutlinedTextField (
                value = "",
                onValueChange = {},
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
                    .background(Color.White)
            )
            Text(
                text = stringResource(R.string.onboarding_email),
                style = LittleLemonTextStyle.leadText,
                color = LittleLemonColor.highlight2,
                modifier = Modifier
                    .padding(top = 16.dp)
            )
            OutlinedTextField (
                value = "",
                onValueChange = {},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
                    .background(Color.White)
            )
            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(containerColor = LittleLemonColor.primary2),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp)
            ) {
                Text(text = stringResource(R.string.onboarding_button_text))
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun OnBoardingScreenPreview() {
    LittleLemonTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = { MyTopAppBar(true)},
        ) { innerPadding ->
            OnBoardingScreen(innerPadding
            )
        }
    }

}