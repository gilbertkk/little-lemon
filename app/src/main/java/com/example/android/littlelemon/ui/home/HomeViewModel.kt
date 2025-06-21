package com.example.android.littlelemon.ui.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.littlelemon.data.AppRepository
import com.example.android.littlelemon.data.Category
import com.example.android.littlelemon.data.MenuItem
import com.example.android.littlelemon.ui.navigation.HomeDestination
import com.example.android.littlelemon.ui.profile.UserUiState
import com.example.android.littlelemon.ui.profile.toUserUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {
    private val appRepository = AppRepository.get()

    val userId: Int = checkNotNull(savedStateHandle[HomeDestination.ARG_USER_ID])

    private val _userUiState: MutableStateFlow<UserUiState> = MutableStateFlow(UserUiState())
    val userUiState: StateFlow<UserUiState> = _userUiState.asStateFlow()

    val menuItemsStateFlow: StateFlow<List<MenuItem>> = appRepository.getMenuItemsStream().stateIn(
        initialValue = listOf(),
        started = SharingStarted.WhileSubscribed(5000L),
        scope = viewModelScope,
    )

    var homeUiState: HomeUiState by mutableStateOf(HomeUiState(emptyList()))

    init {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                appRepository.getUserStream(userId).collect { user ->
                    user?.let {
                        _userUiState.value = user.toUserUiState()
                    }
                }
            }
        }
    }

    fun categorizeMenuItems(searchPhrase: String, menuUiItems: List<MenuUiItem>) {
        if (searchPhrase == Category.ALL.catName) {
            homeUiState = HomeUiState(menuUiItems = menuUiItems)
        } else {
            val filteredList = menuUiItems.filter {
                it.category.contains(searchPhrase.trim(), ignoreCase = true)
            }
            homeUiState = HomeUiState(menuUiItems = filteredList)
        }
    }
}