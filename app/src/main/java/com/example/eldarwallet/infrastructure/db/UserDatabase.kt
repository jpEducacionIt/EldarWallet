package com.example.eldarwallet.infrastructure.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.eldarwallet.core.domain.UserDataEntity
import com.example.eldarwallet.infrastructure.db.dao.UserDataDao

@Database(entities = [UserDataEntity::class], version = 1)
abstract class UserDatabase : RoomDatabase() {
    abstract fun getUserDataDao(): UserDataDao

    companion object {
        @Volatile
        private var INSTANCE: UserDatabase? = null

        fun getDataBase(context: Context): UserDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserDatabase::class.java,
                    "user_db"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}



