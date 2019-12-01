package com.example.roompractice.data.room.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class Users (
    @PrimaryKey(autoGenerate = true) var  id: Long?,
    @ColumnInfo(name = "user_name")  var userName: String?,
    @ColumnInfo(name = "email")  var emailAddress: String?,
    @ColumnInfo(name = "website")  var website: String?
    )