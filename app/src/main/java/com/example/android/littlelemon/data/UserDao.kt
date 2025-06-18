package com.example.android.littlelemon.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert
    suspend fun insertUser(user: User) : Long

    @Update
    suspend fun updateUser(user: User)

    @Delete
    suspend fun deleteUser(user: User): Int

    @Query("SELECT * FROM users WHERE id = :userId;")
    suspend fun getUser(userId: Int) : User

    @Query("SELECT * FROM users WHERE id = :userId;")
    fun getUserStream(userId: Int) : Flow<User?>

    @Query("SELECT * FROM users")
    suspend fun getAllUsers(): List<User>
}