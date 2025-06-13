package com.example.android.littlelemon.ui.user

import android.net.Uri
import androidx.core.net.toUri
import com.example.android.littlelemon.data.User

data class UserUiState (val userDetails: UserDetails = UserDetails())

data class UserDetails (
    val id : Int = 0,
    val firstname: String = "",
    val lastname: String = "",
    val email: String = "",
    val phoneNumber: String = "",
    val notificationOrderStatuses: Boolean = true,
    val notificationPasswordChanges: Boolean = true,
    val notificationSpecialOffers: Boolean = false,
    val notificationNewsletter: Boolean = false,
    val profileImage: Uri? = null
)

fun UserDetails.toUser() : User = User(
    id = id,
    firstname = firstname,
    lastname =  lastname,
    email = email,
    notificationOrderStatuses = notificationOrderStatuses,
    notificationPasswordChanges = notificationPasswordChanges,
    notificationSpecialOffers = notificationSpecialOffers,
    notificationNewsletter = notificationNewsletter,
    profileImage = profileImage?.toString()
)

fun User.toUserDetails() : UserDetails = UserDetails(
    id = id,
    firstname = firstname,
    lastname = lastname,
    email = email,
    notificationOrderStatuses = notificationOrderStatuses,
    notificationPasswordChanges = notificationPasswordChanges,
    notificationSpecialOffers = notificationSpecialOffers,
    notificationNewsletter = notificationNewsletter,
    profileImage = profileImage?.toUri()
)

fun User.toUserUiState() : UserUiState = UserUiState(
    userDetails = this.toUserDetails()
)