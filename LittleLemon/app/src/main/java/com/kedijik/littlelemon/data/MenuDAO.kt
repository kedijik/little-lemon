package com.kedijik.littlelemon.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MenuDAO {
    @Query("select * from MenuItems ")
    suspend fun getAllItems(): List<MenuItem>
    @Query("select * from MenuItems where category=:category")
    suspend fun getItemsByCategory(category: String): List<MenuItem>
    @Query("select * from MenuItems where id=:id")
    suspend fun getItemByID(id: Int): MenuItem

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun putItems(menu: List<MenuItem>)
    @Query("delete from MenuItems")
    suspend fun truncateMenu()
}