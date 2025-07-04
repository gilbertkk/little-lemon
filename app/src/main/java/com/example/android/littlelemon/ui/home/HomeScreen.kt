@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.android.littlelemon.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
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
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.android.littlelemon.R
import com.example.android.littlelemon.data.Categories
import com.example.android.littlelemon.data.Category
import com.example.android.littlelemon.ui.TopAppBar
import com.example.android.littlelemon.ui.theme.LittleLemonColor
import com.example.android.littlelemon.ui.theme.LittleLemonTextStyle

@Composable
fun HomeScreen(
    hasActions: Boolean = true,
    hasNavigationIcons: Boolean = false,
    navigateToUser: (userId: Int) -> Unit = {},
    viewModel: HomeViewModel = viewModel()
) {
    val userUiState by viewModel.userUiState.collectAsStateWithLifecycle()
    val menuItems by viewModel.menuItemsStateFlow.collectAsStateWithLifecycle()
    val menuUiItems = menuItems.map { it.toMenuUiItem() }

    viewModel.homeUiState = HomeUiState(menuUiItems = menuUiItems)

    Scaffold(
        topBar = {
            TopAppBar(
                hasActions, hasNavigationIcons,
                navigateToUser = { navigateToUser(viewModel.userId)},
                userProfileImage = userUiState.userDetails.profileImage
            )
                 },
        modifier = Modifier
            .fillMaxSize(),
    ) { paddingValues ->
        Column (modifier = Modifier
            .padding(paddingValues)
        ) {
            HomeScreenUpper (searchBar ={ AppSearchBar { searchWord ->
                viewModel.homeUiState = HomeUiState(menuUiItems = menuUiItems.filter {
                    it.title.contains(searchWord.trim(), ignoreCase = true) }
                ) }
            })
            HomeScreenLower(
                dishes = viewModel.homeUiState.menuUiItems,
                categorizeDishes = {category ->
                    viewModel.categorizeMenuItems(category, menuUiItems)
                })
        }
    }
}

@Composable
fun HomeScreenUpper(searchBar: @Composable (() -> Unit)? = null) {
    Column (
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .fillMaxWidth()
            .background(LittleLemonColor.primary2)
            .padding(start = 16.dp, end = 16.dp, bottom = 8.dp)

    ){
        Text(
            text = stringResource(id = R.string.home_screen_title),
            style = LittleLemonTextStyle.displayTitle,
            color = LittleLemonColor.primary1,
            modifier = Modifier
                .offset(y = 10.dp)
        )

        Text(
            text = stringResource(id = R.string.home_screen_subtitle),
            style = LittleLemonTextStyle.subTitle,
            color = Color.White,
            modifier = Modifier
                .offset(y = (-10).dp)
        )
        Row (
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = stringResource(id = R.string.home_screen_description),
                style = LittleLemonTextStyle.leadText,
                color = LittleLemonColor.highlight1,
                modifier = Modifier
                    .padding(end = 16.dp, top = 8.dp)
                    .height(150.dp)
                    .fillMaxWidth(0.60f)
            )
            Image(
                painter = painterResource(id = R.drawable.hero_image),
                contentDescription = stringResource(id = R.string.home_screen_hero_image_description),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(150.dp)
                    .clip(RoundedCornerShape(20.dp))
            )
        }
        searchBar?.let { searchBar() }
    }
}

@Composable
fun AppSearchBar(onSearch: (search: String) -> Unit) {
    var searchInput by rememberSaveable { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current

    Surface(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp, bottom = 8.dp)
    ) {
        OutlinedTextField (
            value = searchInput,
            onValueChange = {
                searchInput = it
                onSearch(searchInput)
            },
            placeholder = { Text(text = stringResource(R.string.home_screen_search_bar_placeholder))},
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(
                onDone = {
                    onSearch(searchInput)
                    keyboardController?.hide()
                }
            ),
            shape = RoundedCornerShape(8.dp),
            colors = OutlinedTextFieldDefaults.colors(unfocusedBorderColor = LittleLemonColor.primary2),
            leadingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = "search icon") },
            modifier = Modifier
                .background(LittleLemonColor.highlight1)
        )
    }
}

@Composable
fun HomeScreenLower(dishes: List<MenuUiItem>, categorizeDishes: (String) -> Unit) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp, start = 24.dp, end = 24.dp)
    ) {
        Text(
            text = stringResource(id = R.string.home_screen_section_title).uppercase(),
            style = LittleLemonTextStyle.sectionTitle,
            color = Color.Black,
            modifier = Modifier.padding(top = 8.dp)
        )
        LazyRow {
            items(Categories) { category ->
                MenuCategory(category, onClick = categorizeDishes)
            }
        }
        HorizontalDivider(
            modifier = Modifier.padding(8.dp),
            color = Color.Gray,
            thickness = 1.dp
        )
        if (dishes.isNotEmpty()) {
            LazyColumn {
                items(dishes) { dish ->
                    MenuDish(dish)
                }
            }
        } else {
            Text(
                text = stringResource(R.string.home_screen_no_corresponding_dishes),
                style = LittleLemonTextStyle.sectionTitle,
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.CenterHorizontally)
            )
        }
    }
}

@Composable
fun MenuCategory(category: Category, onClick: (String) -> Unit) {
    Button(
        onClick = {
            onClick(category.catName)
        },
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFACADAC)),
        shape = RoundedCornerShape(40),
        modifier = Modifier
            .padding(5.dp)
    ) {
        Text(text=category.catName, color = Color.Black)
    }
}

@Composable
fun MenuDish(dish: MenuUiItem) {
    Card (
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ){
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Column {
                Text(text = dish.title, style = LittleLemonTextStyle.cardTitle)
                Text(
                    text = dish.description,
                    style = LittleLemonTextStyle.leadText,
                    maxLines = 2,
                    color = Color.Gray,
                    modifier = Modifier
                        .padding(top = 5.dp, bottom = 5.dp)
                        .fillMaxWidth(.75f)
                )
                Text(
                    text = dish.price,
                    color = LittleLemonColor.highlight2,
                    style = LittleLemonTextStyle.highLightText
                )
            }
            AsyncImage(
                model = dish.image,
                contentDescription = dish.title + " image."
            )
        }
    }
    HorizontalDivider(
        modifier = Modifier.padding(8.dp),
        color = Color.Gray,
        thickness = 0.2.dp
    )
}