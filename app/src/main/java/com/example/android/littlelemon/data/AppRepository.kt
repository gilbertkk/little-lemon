package com.example.android.littlelemon.data

import android.content.Context
import java.util.UUID

class AppRepository private constructor(context: Context) {
    private val database: AppDatabase = AppDatabase.getDatabase(context)

    suspend fun insert(user: User) = database.userDao().insert(user)
    suspend fun update(user: User) = database.userDao().update(user)
    suspend fun getUser(userId: UUID) = database.userDao().getUser(userId)

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