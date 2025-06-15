package com.example.android.littlelemon.data

import android.content.Context

class AppRepository private constructor(context: Context) {
    private val database: AppDatabase = AppDatabase.getDatabase(context)

    suspend fun insert(user: User) : Long = database.userDao().insertUser(user)
    suspend fun updateUser(user: User) = database.userDao().updateUser(user)
    suspend fun getUser(userId: Int) = database.userDao().getUser(userId)
    fun getUserStream(userId: Int) = database.userDao().getUserStream(userId)

    companion object {
        private var INSTANCE: AppRepository? = null

        fun initialize(context: Context) {
            if(INSTANCE == null) {
                INSTANCE = AppRepository(context)
            }
        }

        fun get(): AppRepository {
            return INSTANCE ?: throw IllegalStateException("AppRepository must be initialized")
        }
    }
}