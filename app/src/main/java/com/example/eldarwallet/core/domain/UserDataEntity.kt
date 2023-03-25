package com.example.eldarwallet.core.domain

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "userData_table")
data class UserDataEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id : Int,
    @ColumnInfo(name = "typeCard") val typeCard : String,
    @ColumnInfo(name = "name") val name : String,
    @ColumnInfo(name = "surname") val surname : String,
    @ColumnInfo(name = "number") val number : String,
    @ColumnInfo(name = "cvv") val cvv : String,
    @ColumnInfo(name = "expired") val expired : String
    )