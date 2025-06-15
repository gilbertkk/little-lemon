package com.example.android.littlelemon.ui.user

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

    var isNewUriToStore by mutableStateOf(false)


    init {
        viewModelScope.launch {
            val user = appRepository.getUser(userId)
            userUiState = user.toUserUiState()
            userUiStateBackup = userUiState.copy()
        }
    }

    /**
     *  Update the userUiState from UserScreen
     */
    fun updateUserUiState(userDetails: UserDetails) {
       userUiState = UserUiState(userDetails = userDetails)
    }

    /**
     *  Update the user in the database
     */
    suspend fun updateUser(storedImagePath: String?) {
        appRepository.updateUser(
            userUiState.userDetails.toUser().copy(profileImage = storedImagePath)
        )
    }

    /**
     * To backup userUiState for discarding (restoring) the changes
     */
    fun restoreUiState() {
        userUiState = userUiStateBackup
        isNewUriToStore = false
    }

    fun removeProfileImage() {
        userUiState = UserUiState(userDetails = userUiState.userDetails.copy(profileImage = null))
    }

    fun logout() {
        userUiState = UserUiState(
            UserDetails(notificationOrderStatuses = false, notificationPasswordChanges = false, id = userId)
        )
        isNewUriToStore = false
    }
}