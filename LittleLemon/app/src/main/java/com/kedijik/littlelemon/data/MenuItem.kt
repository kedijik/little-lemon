package com.kedijik.littlelemon.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index

@Entity(tableName = "MenuItems", primaryKeys = ["id"],indices = [Index(value = ["category"])])
data class MenuItem (@ColumnInfo(name="id") val id:Int,
                     @ColumnInfo(name="title") val title: String,
                     @ColumnInfo(name="description") val desc: String,
                     @ColumnInfo(name="price") val price: Double,
                     @ColumnInfo(name="image") val imgURL: String,
                     @ColumnInfo(name="category") val category: String)
