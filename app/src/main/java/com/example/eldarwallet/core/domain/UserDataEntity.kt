package com.example.eldarwallet.core.domain

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Types.ROWID

@Entity(tableName =  "userdata_table")
data class UserDataEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id : Int,
    @ColumnInfo(name = "typeCard") val typeCard : String,
    @ColumnInfo(name = "surname") val surname: String?,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "number") val number: String?,
    @ColumnInfo(name = "expired") val expiry: String?,
    @ColumnInfo(name = "cvv") val cvv : String?
    )