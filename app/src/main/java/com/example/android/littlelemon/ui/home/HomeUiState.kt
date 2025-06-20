package com.example.android.littlelemon.ui.home

import com.example.android.littlelemon.data.MenuItem

data class HomeUiState(
    val menuUiItems: List<MenuUiItem> = emptyList()
)

data class MenuUiItem(
    val id: Int = 0,
    val title: String = "",
    val description: String = "",
    val price: String = "",
    val image: String = "",
    val category: String = ""
)

fun MenuItem.toMenuUiItem() = MenuUiItem(
    id = id,
    title = title,
    description = description,
    price = price.toString(),
    image = image,
    category = category
)





