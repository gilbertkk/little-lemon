package com.example.android.littlelemon.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserDao {
    @Insert
    suspend fun insertUser(user: User)

    @Update
    suspend fun updateUser(user: User)

    @Query("SELECT * FROM users WHERE id = :userId;")
    suspend fun getUser(userId: Int) : User

    @Query("SELECT * FROM users")
    suspend fun getAllUsers(): List<User>
}