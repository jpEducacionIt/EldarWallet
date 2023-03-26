package com.example.eldarwallet.infrastructure.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.eldarwallet.core.domain.UserDataEntity

@Dao
interface UserDataDao {
    @Query("SELECT * FROM userData_table ORDER BY id DESC ")
    fun getAllCardData() : LiveData<List<UserDataEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(userDataEntity: UserDataEntity)
}