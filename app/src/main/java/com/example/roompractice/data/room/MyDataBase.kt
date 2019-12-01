package com.example.roompractice.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.roompractice.data.room.dao.PostDAO
import com.example.roompractice.data.room.dao.UsersDAO
import com.example.roompractice.data.room.model.Post
import com.example.roompractice.data.room.model.Users

@Database(entities = [Post::class, Users::class], version = 1)
@TypeConverters(AppConverters::class)
abstract class MyDataBase : RoomDatabase() {
    abstract fun postDAO() :PostDAO
    abstract fun usersDAO() :UsersDAO
}