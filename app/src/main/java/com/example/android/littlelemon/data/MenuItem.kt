package com.example.android.littlelemon.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "menuItems")
data class MenuItem (
    @PrimaryKey val id: Int,
    val title: String,
    val description: String,
    val price: Double? = null,
    val image: String,
    val category: String
)