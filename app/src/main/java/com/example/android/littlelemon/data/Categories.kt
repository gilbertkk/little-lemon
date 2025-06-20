package com.example.android.littlelemon.data

val Categories = listOf(
    Category.ALL,
    Category.STARTERS,
    Category.MAINS,
    Category.DESSERTS,
    Category.DRINKS,
)

enum class Category(val catName: String) {
    STARTERS("starters"),
    MAINS("mains"),
    DESSERTS("desserts"),
    DRINKS("drinks"),
    ALL("all")
}