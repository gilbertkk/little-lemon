package com.example.android.littlelemon.ui.user

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.littlelemon.data.AppRepository
import com.example.android.littlelemon.ui.navigation.UserDestination
import kotlinx.coroutines.launch

class UserViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {
    private val appRepository = AppRepository.get()
    private val userId: Int = checkNotNull(savedStateHandle[UserDestination.ARG_USER_ID])

    private var userUiState by mutableStateOf(UserUiState())

    val userDetails: UserDetails
        get() = userUiState.userDetails

    private var userUiStateBackup: UserUiState = UserUiState()

    init {
        Log.d("debugging", "userId from userViewModel: $userId")
        viewModelScope.launch {
            val user = appRepository.getUser(userId)
            userUiState = user.toUserUiState()
            userUiStateBackup = userUiState.copy()
        }
    }

    /**
     *  Update the userUiState from UserScreen
     */
    fun updateUseUiState(userDetails: UserDetails) {
       userUiState = UserUiState(userDetails = userDetails)
    }

    /**
     *  Update the user in the database
     */
    suspend fun updateUser() {
        appRepository.updateUser(userUiState.userDetails.toUser())
    }

    /**
     * To backup userUiState for discarding (restoring) the changes
     */
    fun restoreUiState() {
        userUiState = userUiStateBackup
    }


}