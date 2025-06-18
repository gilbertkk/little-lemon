package com.example.android.littlelemon.ui.home

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.littlelemon.data.AppRepository
import com.example.android.littlelemon.data.dishes
import com.example.android.littlelemon.ui.navigation.HomeDestination
import com.example.android.littlelemon.ui.profile.UserUiState
import com.example.android.littlelemon.ui.profile.toUserUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {
    private val appRepository = AppRepository.get()

    val userId: Int = checkNotNull(savedStateHandle[HomeDestination.ARG_USER_ID])

    private val _userUiState: MutableStateFlow<UserUiState> = MutableStateFlow(UserUiState())
    val userUiState: StateFlow<UserUiState> = _userUiState.asStateFlow()

    var dishList by mutableStateOf(dishes)

    init {
        viewModelScope.launch {
            appRepository.getUserStream(userId).collect {
                it?.let {
                    _userUiState.value = it.toUserUiState()
                }


            }
        }
    }

}