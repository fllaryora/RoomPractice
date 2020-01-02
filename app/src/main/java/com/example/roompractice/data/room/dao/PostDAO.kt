package com.example.roompractice.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.roompractice.data.room.model.Post
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Single

@Dao
interface PostDAO {

    @Query("select * from post")
    fun getItemList(): Flowable<List<Post>>

    @Query("select * from post where id = :id")
    fun getItemById(id:Long): Flowable<Post>

    /**
     * where the value emitted on onSuccess is the list of row ids of the items inserted
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addData(post: List<Post>): Maybe<List<Long>>

    @Query("select * from post where user_id = :userId LIMIT 1")
    fun getPostsByUser(userId:Long): Single<List<Post>>

}