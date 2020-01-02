package com.example.roompractice.data.repository.main

import android.util.Log
import com.example.roompractice.data.model.Post
import com.example.roompractice.data.room.model.Post as RoomPost
import com.example.roompractice.data.network.main.MainApi
import com.example.roompractice.data.room.dao.PostDAO
import com.example.roompractice.presentation.main.Resource
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PostRepository @Inject constructor(private val mainApi: MainApi, private val postDAO: PostDAO){
    fun getPost( fromUserId : Int) : Flowable<Resource<List<Post>>> {
        val dataFromApi: Single<List<Post>> =  mainApi.getPostFromUsers(fromUserId)
            .doAfterSuccess {
                Log.d("PostRepository","2) showing progress on:" + Thread.currentThread().name)
                postDAO.addData(transFormApiToBaseFormat(it))
            }

        val dataFromBase: Single<List<Post>>  =  postDAO.getPostsByUser( fromUserId.toLong())
            .flatMap { postList -> Single.just(transFormBaseToApiFormat(postList)) }

        return dataFromApi.concatWith(dataFromBase)
            .firstOrError()
            .flatMap { list -> Single.just(Resource.success(list)) }
            .onErrorReturnItem(Resource.error("Something went wrong", null))
            .toFlowable() //flowable is publisher for some reason
            .subscribeOn(Schedulers.io())
    }

    private fun transFormBaseToApiFormat(postList: List<RoomPost>) : List<Post> {
        val mutableList = mutableListOf<Post>()
        for (post in postList) {
            mutableList.add(transFormBaseToApiFormat(post))
        }
        return mutableList
    }

    private fun transFormBaseToApiFormat(post: RoomPost) : Post {
        val postId: Int  = post.id?.toInt() ?: -1
        val userId: Int  = post.userId?.toInt() ?: -1
        return Post(postId, userId, post.title ?: "", post.body ?: "")
    }

    private fun transFormApiToBaseFormat(postList: List<Post>) :  List<RoomPost> {
        val mutableList = mutableListOf<RoomPost>()
        for (post in postList) {
            mutableList.add(transFormApiToBaseFormat(post))
        }
        return mutableList
    }

    private fun transFormApiToBaseFormat(post: Post) : RoomPost {
        return RoomPost(
            post.id.toLong(), post.userId.toLong(), post.title, post.body
        )
    }

}