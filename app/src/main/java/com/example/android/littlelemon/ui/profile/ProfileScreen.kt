package com.example.android.littlelemon.ui.profile

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.example.android.littlelemon.R
import com.example.android.littlelemon.ui.TopAppBar
import com.example.android.littlelemon.ui.theme.LittleLemonColor
import com.example.android.littlelemon.ui.theme.LittleLemonTextStyle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun UserScreen(
    hasActions: Boolean = true,
    hasNavigationIcons: Boolean = true,
    viewModel: ProfileViewModel = viewModel(),
    navigateBack: () -> Unit = {},
    navigateToOnBoarding: (userDeletedId: Int) -> Unit = {}
) {
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    var isButtonEnabled by rememberSaveable { mutableStateOf(true) }


    val pickImageLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri: Uri? ->
            uri?.let {
                coroutineScope.launch(Dispatchers.IO) {
                    viewModel.updateUserUiState(viewModel.userDetails.copy(profileImage = uri))
                    viewModel.isNewUriToStore = true
                }
            }
        }
    )

    Scaffold(
        topBar = {
            TopAppBar(hasActions,
                hasNavigationIcons,
                navigateBack = navigateBack,
                userProfileImage = viewModel.userDetails.profileImage)
        },
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
                        painter = if (viewModel.userDetails.profileImage == null) {
                            painterResource(R.drawable.profile)
                        } else {
                            rememberAsyncImagePainter(viewModel.userDetails.profileImage)
                        },
                        contentDescription = stringResource(R.string.profile_screen_profile_image_description),
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(80.dp)
                            .clip(CircleShape)
                    )
                    Button(
                        onClick = {
                            isButtonEnabled = false
                            pickImageLauncher.launch(
                                PickVisualMediaRequest (
                                    ActivityResultContracts.PickVisualMedia.ImageOnly
                                )
                            )
                            isButtonEnabled = true
                        },
                        enabled = isButtonEnabled,
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = LittleLemonColor.primary2),
                        modifier = Modifier
                            .padding(start = 16.dp, end = 16.dp)

                    ) {
                        Text(text = stringResource(R.string.profile_screen_button_change))
                    }
                    OutlinedButton(
                        onClick = {
                            isButtonEnabled = false
                            viewModel.removeProfileImage()
                            isButtonEnabled = true
                        },
                        enabled = isButtonEnabled,
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
                    value = viewModel.userDetails.firstname,
                    onValueChange = {
                        viewModel.updateUserUiState(
                            viewModel.userDetails.copy(firstname = it)
                        )
                    },
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
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
                    value = viewModel.userDetails.lastname,
                    onValueChange = {
                        viewModel.updateUserUiState(
                            viewModel.userDetails.copy(lastname = it)
                        )
                    },
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
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
                    value = viewModel.userDetails.email,
                    onValueChange = {
                        viewModel.updateUserUiState(
                            viewModel.userDetails.copy(email = it)
                        )
                    },
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
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
                    value = viewModel.userDetails.phoneNumber,
                    onValueChange = {
                        viewModel.updateUserUiState(
                            viewModel.userDetails.copy(phoneNumber = it)
                        )
                    },
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
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
                        checked = viewModel.userDetails.notificationOrderStatuses,
                        onCheckedChange = {
                            viewModel.updateUserUiState(
                                viewModel.userDetails.copy(notificationOrderStatuses =
                                !viewModel.userDetails.notificationOrderStatuses)
                            )
                        },
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
                        checked = viewModel.userDetails.notificationPasswordChanges,
                        onCheckedChange = {
                            viewModel.updateUserUiState(
                                viewModel.userDetails.copy(notificationPasswordChanges =
                                !viewModel.userDetails.notificationPasswordChanges)
                            )
                        },
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
                        checked = viewModel.userDetails.notificationSpecialOffers,
                        onCheckedChange = {
                            viewModel.updateUserUiState(
                                viewModel.userDetails.copy(notificationSpecialOffers =
                                !viewModel.userDetails.notificationSpecialOffers)
                            )
                        },
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
                        checked = viewModel.userDetails.notificationNewsletter,
                        onCheckedChange = {
                            viewModel.updateUserUiState(
                                viewModel.userDetails.copy(notificationNewsletter =
                                !viewModel.userDetails.notificationNewsletter)
                            )
                        },
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
                    onClick = {
                        coroutineScope.launch {
                            val userDeletedId = viewModel.logout(context)
                            navigateToOnBoarding(userDeletedId)
                        }
                    },
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
                        onClick = {
                            viewModel.restoreUiState()
                        },
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Gray)
                    ) {
                        Text(text = stringResource(R.string.profile_screen_button_discard_changes))
                    }

                    Button(
                        onClick = {
                            coroutineScope.launch {
                                val imagePath: String? = if (viewModel.isNewUriToStore) {
                                    saveImageToInternalStorage(
                                        context, viewModel.userDetails.profileImage
                                    )
                                } else if (viewModel.userDetails.profileImage != null) {
                                    viewModel.userDetails.profileImage.toString()
                                } else {
                                    null
                                }

                                viewModel.updateUser(imagePath)
                            }
                            navigateBack()
                        },
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

// function to save image in internal storage
fun saveImageToInternalStorage(context: Context, uri: Uri?): String? {
    if (uri == null) {
        return null
    } else {
        return try {
            val inputStream = context.contentResolver.openInputStream(uri)
            val fileName = "profile_image.jpg" // Unique name for the file
            val file = File(context.filesDir, fileName) // Create the file in the internal storage

            FileOutputStream(file).use { outputStream ->
                inputStream?.copyTo(outputStream)
            }
            inputStream?.close()
            file.absolutePath // Return the absolute file path
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}