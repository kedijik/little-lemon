package com.kedijik.littlelemon.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [MenuItem::class], version=1, exportSchema = false)
abstract class MenuDB: RoomDatabase() {
    abstract val DAO: MenuDAO
    companion object{
        @Volatile
        private var INSTANCE: MenuDAO? = null

        fun getDaoInstance(context: Context): MenuDAO {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = buildDatabase(context).DAO
                    INSTANCE = instance
                }
                return instance
            }
        }

        private fun buildDatabase(context: Context): MenuDB =
            Room.databaseBuilder(
                context.applicationContext,
                MenuDB::class.java,
                "MenuDB")
                .fallbackToDestructiveMigration()
                .build()
    }

}