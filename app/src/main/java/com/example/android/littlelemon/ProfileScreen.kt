package com.example.android.littlelemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.android.littlelemon.ui.theme.LittleLemonColor
import com.example.android.littlelemon.ui.theme.LittleLemonTextStyle

@Composable
fun ProfileScreen(hasActions: Boolean = true, hasNavigationIcons: Boolean = true) {
    var firstName by rememberSaveable { mutableStateOf("") }
    var lastName by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var phoneNumber by rememberSaveable { mutableStateOf("") }

    Scaffold(
        topBar = { MyTopAppBar(hasActions, hasNavigationIcons)},
        modifier = Modifier
            .fillMaxSize(),
    ) { paddingValues ->
        Card(
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            modifier = Modifier
                .padding(paddingValues)
                .padding(4.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Text(
                    text = stringResource(R.string.profile_screen_title),
                    style = LittleLemonTextStyle.sectionTitle,
                    modifier = Modifier
                        .padding(top = 8.dp, bottom = 16.dp)
                )
                Text(
                    text = stringResource(R.string.profile_screen_avatar),
                    style = LittleLemonTextStyle.highLightText,
                    modifier = Modifier
                        .padding(bottom = 8.dp)
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(R.drawable.profile),
                        contentDescription = stringResource(R.string.profile_screen_profile_image_description),
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(80.dp)
                            .clip(CircleShape)
                    )
                    Button(
                        onClick = {},
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = LittleLemonColor.primary2),
                        modifier = Modifier
                            .padding(start = 16.dp, end = 16.dp)

                    ) {
                        Text(text = stringResource(R.string.profile_screen_button_change))
                    }
                    OutlinedButton(
                        onClick = {},
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Gray)
                    ) {
                        Text(text = stringResource(R.string.profile_screen_button_remove))
                    }
                }


                Text(
                    text = stringResource(R.string.profile_screen_first_name),
                    modifier = Modifier
                        .padding(top = 16.dp))
                OutlinedTextField(
                    value = firstName,
                    onValueChange = { firstName = it },
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = Color.White, shape = RoundedCornerShape((8.dp)))
                )

                Text(
                    text = stringResource(R.string.profile_screen_last_name),
                    modifier = Modifier
                        .padding(top = 16.dp))
                OutlinedTextField(
                    value = lastName,
                    onValueChange = { lastName = it },
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = Color.White, shape = RoundedCornerShape((8.dp)))
                )

                Text(
                    text = stringResource(R.string.profile_screen_email),
                    modifier = Modifier
                        .padding(top = 16.dp))
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = Color.White, shape = RoundedCornerShape((8.dp)))
                )

                Text(
                    text = stringResource(R.string.profile_screen_phone_number),
                    modifier = Modifier
                        .padding(top = 16.dp))
                OutlinedTextField(
                    value = phoneNumber,
                    onValueChange = { phoneNumber = it },
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = Color.White, shape = RoundedCornerShape((8.dp)))
                )

                //************* EMAIL NOTIFICATIONS SECTION *****************************

                Text(
                    text = stringResource(R.string.profile_screen_email_notifications),
                    style = LittleLemonTextStyle.sectionTitle,
                    modifier = Modifier
                        .padding(top = 24.dp)
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(top = 16.dp)
                ) {
                    Checkbox(
                        checked = true,
                        onCheckedChange = {},
                        colors = CheckboxDefaults.colors(checkedColor = LittleLemonColor.primary2),
                        modifier = Modifier
                            .size(16.dp)
                            .padding(start = 4.dp)
                    )
                    Text(
                        text = stringResource(R.string.profile_screen_checkbox_order_statuses),
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(top = 16.dp)
                ) {
                    Checkbox(
                        checked = true,
                        onCheckedChange = {},
                        colors = CheckboxDefaults.colors(checkedColor = LittleLemonColor.primary2),
                        modifier = Modifier
                            .size(16.dp)
                            .padding(start = 4.dp)
                    )
                    Text(
                        text = stringResource(R.string.profile_screen_checkbox_password_changes),
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(top = 16.dp)
                ) {
                    Checkbox(
                        checked = true,
                        onCheckedChange = {},
                        colors = CheckboxDefaults.colors(checkedColor = LittleLemonColor.primary2),
                        modifier = Modifier
                            .size(16.dp)
                            .padding(start = 4.dp)
                    )
                    Text(
                        text = stringResource(R.string.profile_screen_checkbox_special_offers),
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(top = 16.dp)
                ) {
                    Checkbox(
                        checked = true,
                        onCheckedChange = {},
                        colors = CheckboxDefaults.colors(checkedColor = LittleLemonColor.primary2),
                        modifier = Modifier
                            .size(16.dp)
                            .padding(start = 4.dp)
                    )
                    Text(
                        text = stringResource(R.string.profile_screen_checkbox_newsletter),
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                }

                // ************** LOWER PART BUTTONS SECTION ****************************
                Button(
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(containerColor = LittleLemonColor.primary1),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 40.dp)
                        .height(50.dp)
                        .border(1.dp, LittleLemonColor.primary2, RoundedCornerShape(8.dp))
                ) {
                    Text(
                        text = stringResource(R.string.profile_screen_button_log_out),
                        color = Color.Black,
                        style = TextStyle(fontWeight = FontWeight.Bold))
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 24.dp, start = 8.dp, end = 8.dp, bottom = 24.dp)
                ) {
                    OutlinedButton(
                        onClick = {},
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Gray)
                    ) {
                        Text(text = stringResource(R.string.profile_screen_button_discard_changes))
                    }

                    Button(
                        onClick = {},
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = LittleLemonColor.primary2)
                    ) {
                        Text(text = stringResource(R.string.profile_screen_button_save_changes))
                    }
                }
            }
        }

    }
}

