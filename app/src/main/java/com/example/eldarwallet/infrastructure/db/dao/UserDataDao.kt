package com.example.eldarwallet.infrastructure.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.eldarwallet.core.domain.UserDataEntity

@Dao
interface UserDataDao {
    @Query("SELECT * FROM userData_table ORDER BY id DESC ")
    suspend fun getAllDataCars() : List<UserDataEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(userDataEntity: UserDataEntity)
}