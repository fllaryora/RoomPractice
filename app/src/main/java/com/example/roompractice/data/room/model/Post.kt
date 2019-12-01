package com.example.roompractice.data.room.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "post",
    foreignKeys = [ForeignKey(entity = Users::class,
        parentColumns = ["id"], childColumns = ["user_id"],
        onDelete = ForeignKey.CASCADE)])
data class Post (
    @PrimaryKey(autoGenerate = true) var  id: Long?,
    @ColumnInfo(name = "user_id") var userId :Long?,
    @ColumnInfo(name = "title")  var title: String?,
    @ColumnInfo(name = "title") var  body: String?)
