package com.example.android.littlelemon.data

val Categories = listOf(
    Category.ALL,
    Category.STARTERS,
    Category.MAINS,
    Category.DESSERTS,
    Category.DRINKS,
)

enum class Category(val catName: String) {
    STARTERS("Starters"),
    MAINS("Mains"),
    DESSERTS("Desserts"),
    DRINKS("Drinks"),
    ALL("All")
}