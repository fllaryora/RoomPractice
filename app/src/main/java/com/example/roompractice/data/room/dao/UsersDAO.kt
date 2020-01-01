package com.example.roompractice.data.room.dao

import androidx.room.*
import com.example.roompractice.data.room.model.Users
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Single

@Dao
interface UsersDAO {

    @Query("select * from user")
    fun getItemList(): Flowable<List<Users>>

    @Query("select * from user where id = :id LIMIT 1")
    fun getItemById(id:Long): Single<Users>

    /**
     * I wanted to have a query with subquery
     */
    @Query("select * from user where LENGTH(user_name) > (select LENGTH(user_name) from user where id = :id LIMIT 1)")
    fun getUsersWithNamesLongerThanMe(id:Long): Flowable<List<Users>>

    /**
     * where the value emitted on onSuccess is the list of row ids of the items inserted
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addData(users: List<Users>): Maybe<List<Long>>

    /**
     * Insert a user in the database. If the user already exists, replace it.
     * @param user the user to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: Users): Completable

    /**
     *  where the value emitted on onSuccess is the number of rows affected by update/delete
     *
     */
    @Update
    fun updateAll(users: List<Users>): Single<Integer>

    /**
     *  where the value emitted on onSuccess is the number of rows affected by update/delete
     */
    @Delete
    fun deleteAllUsers(users: List<Users>): Single<Int>
}