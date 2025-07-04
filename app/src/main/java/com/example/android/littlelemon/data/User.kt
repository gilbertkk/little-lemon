package com.example.android.littlelemon.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val firstname: String = "",
    val lastname: String = "",
    val email: String = "",
    val phoneNumber: String = "",
    val notificationOrderStatuses: Boolean = true,
    val notificationPasswordChanges: Boolean = true,
    val notificationSpecialOffers: Boolean = true,
    val notificationNewsletter: Boolean = true,
    val profileImage: String? = null
)