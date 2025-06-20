package com.example.android.littlelemon.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MenuItemDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(menuItem: MenuItem)

    @Query("SELECT * FROM menuItems")
    fun getMenuItemsStream() : Flow<List<MenuItem>>
}