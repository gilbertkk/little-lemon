package com.example.android.littlelemon.data

import com.example.android.littlelemon.R

data class Dish(
    val name: String,
    val price: String,
    val description: String,
    val image: Int,
    val category: Category
)

val dishes = listOf(
    Dish(
        "Greek Salad",
        "$12.99",
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin ut nulla sed augue ultrices posuere. Ut aliquam purus quis diam tempor condimentum. Curabitur elementum pharetra ipsum nec pretium. Phasellus maximus consequat erat, ut vestibulum urna porta eu. ",
        R.drawable.greek_salad,
        Category.STARTERS
    ),
    Dish(
        "Brushetta",
        "$5.99",
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin ut nulla sed augue ultrices posuere. Ut aliquam purus quis diam tempor condimentum. Curabitur elementum pharetra ipsum nec pretium. Phasellus maximus consequat erat, ut vestibulum urna porta eu. ",
        R.drawable.bruschetta,
        Category.MAINS
    ),
    Dish(
        "Lemon Dessert",
        "$9.99",
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin ut nulla sed augue ultrices posuere. Ut aliquam purus quis diam tempor condimentum. Curabitur elementum pharetra ipsum nec pretium. Phasellus maximus consequat erat, ut vestibulum urna porta eu. ",
        R.drawable.lemon_dessert,
        Category.DESSERTS
    ),
    Dish(
        "Pasta",
        "$8.99",
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin ut nulla sed augue ultrices posuere. Ut aliquam purus quis diam tempor condimentum. Curabitur elementum pharetra ipsum nec pretium. Phasellus maximus consequat erat, ut vestibulum urna porta eu. ",
        R.drawable.pasta,
        Category.MAINS
    ),
    Dish(
        "Grilled Fish",
        "$8.99",
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin ut nulla sed augue ultrices posuere. Ut aliquam purus quis diam tempor condimentum. Curabitur elementum pharetra ipsum nec pretium. Phasellus maximus consequat erat, ut vestibulum urna porta eu. ",
        R.drawable.grilled_fish,
        Category.MAINS
    ),
    Dish(
        "Lemon soda",
        "$4.99",
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin ut nulla sed augue ultrices posuere. Ut aliquam purus quis diam tempor condimentum. Curabitur elementum pharetra ipsum nec pretium. Phasellus maximus consequat erat, ut vestibulum urna porta eu. ",
        R.drawable.drink_lemon_soda,
        Category.DRINKS
    ),
    Dish(
        "Lemon cocktail",
        "$6.99",
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin ut nulla sed augue ultrices posuere. Ut aliquam purus quis diam tempor condimentum. Curabitur elementum pharetra ipsum nec pretium. Phasellus maximus consequat erat, ut vestibulum urna porta eu. ",
        R.drawable.drink_lemon_cocktail,
        Category.DRINKS
    ),

)