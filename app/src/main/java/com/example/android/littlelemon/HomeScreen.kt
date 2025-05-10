package com.example.android.littlelemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.android.littlelemon.data.Categories
import com.example.android.littlelemon.data.Dish
import com.example.android.littlelemon.data.dishes

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Column {
        HomeScreenUpper()
        HomeScreenLower()
    }

}

@Composable
fun HomeScreenUpper() {
    Column (
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .padding(top = 50.dp)
    ){
        Text(text = stringResource(id = R.string.home_screen_title))
        Text(text = stringResource(id = R.string.home_screen_subtitle))
        Row (
        ) {
            Text(
                text = stringResource(id = R.string.home_screen_description),
                modifier = Modifier
                    .padding(end = 16.dp, top = 16.dp)
                    .height(200.dp)
                    .fillMaxWidth(0.60f)
            )
            Image(
                painter = painterResource(id = R.drawable.hero_image),
                contentDescription = stringResource(id = R.string.home_screen_hero_image_description),
                modifier = Modifier
                    .height(200.dp)
                    .clip(RoundedCornerShape(20.dp))
            )
        }
        OutlinedTextField (
            value = "",
            onValueChange = {},
            placeholder = {Text(text = "Search for menu")},
            leadingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = "search icon") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        )
    }
}

@Composable
fun HomeScreenLower() {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp)
    ) {
        Text(
            text = stringResource(id = R.string.home_screen_section_title),
        )
        LazyRow {
            items(Categories) { category ->
                MenuCategory(category)
            }
        }
        HorizontalDivider(
            modifier = Modifier.padding(8.dp),
            color = Color.Gray,
            thickness = 1.dp
        )
        LazyColumn {
            items(dishes) { dish ->
                MenuDish(dish)
            }
        }
    }
}

@Composable
fun MenuCategory(category: String) {
    Button(
        onClick = {},
        colors = ButtonDefaults.buttonColors(containerColor  = Color.DarkGray),
        shape = RoundedCornerShape(40),
        modifier = Modifier
            .padding(5.dp)
    ) {
        Text(text=category)
    }
}

//val categories = listOf<String>("Starters", "Mains", "Desserts", "Drinks")

@Composable
fun MenuDish(dish: Dish) {
    Card (
        colors = CardDefaults.cardColors(containerColor = Color.White),
    ){
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Column {
                Text(text = dish.name, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Text(
                    text = dish.description,
                    maxLines = 2,
                    color = Color.Gray,
                    modifier = Modifier
                        .padding(top = 5.dp, bottom = 5.dp)
                        .fillMaxWidth(.75f)
                )
                Text(
                    text = dish.price, color = Color.Gray, fontWeight = FontWeight.Bold
                )
            }
            Image(
                painter = painterResource(id = dish.image),
                contentDescription = dish.name + " image."
            )
        }
    }
    HorizontalDivider(
        modifier = Modifier.padding(8.dp),
        color = Color.Gray,
        thickness = 0.2.dp
    )
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}