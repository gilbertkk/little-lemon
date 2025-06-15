package com.example.android.littlelemon.ui.onboarding

import android.content.Context.MODE_PRIVATE
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.android.littlelemon.R
import com.example.android.littlelemon.SHARED_PREFERENCES_FILE_NAME
import com.example.android.littlelemon.SHARED_PREFERENCES_IS_ONBOARDED
import com.example.android.littlelemon.SHARED_PREFERENCES_USER_ID
import com.example.android.littlelemon.data.AppRepository
import com.example.android.littlelemon.data.User
import com.example.android.littlelemon.ui.TopAppBar
import com.example.android.littlelemon.ui.home.HomeScreenUpper
import com.example.android.littlelemon.ui.theme.LittleLemonColor
import com.example.android.littlelemon.ui.theme.LittleLemonTextStyle
import kotlinx.coroutines.launch

@Composable
fun OnBoardingScreen(
    hasActions: Boolean = false,
    hasNavigationIcons: Boolean = false,
    navigateToHome: (userId: Int) -> Unit
) {
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_FILE_NAME, MODE_PRIVATE)
    val appRepository by lazy { AppRepository.get() }

    val coroutineScope = rememberCoroutineScope()

    var name by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }

    Scaffold(
        topBar = { TopAppBar(hasActions, hasNavigationIcons) },
        modifier = Modifier
            .fillMaxSize(),
    ) { paddingValues ->
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
                    value = name,
                    onValueChange = { name = it },
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
                    value = email,
                    onValueChange = { email = it },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                        .background(Color.White)
                )
                Button(
                    onClick = {
                        coroutineScope.launch {
                            val userIdLong: Long = (appRepository.insert(
                                User(firstname = name, email = email)
                            ))
                            val userId = userIdLong.toInt()
                            if (userId > 0) {
                                sharedPreferences.edit()
                                    .putBoolean(SHARED_PREFERENCES_IS_ONBOARDED, true)
                                    .putInt(SHARED_PREFERENCES_USER_ID, userId)
                                    .apply()

                                navigateToHome(userId)
                            }else {
                                throw Exception("Failed to create the user in the database")
                            }
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = LittleLemonColor.primary2),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 40.dp)
                        .height(50.dp)
                ) {
                    Text(text = stringResource(R.string.onboarding_button_text))
                }
            }

        }
    }


}