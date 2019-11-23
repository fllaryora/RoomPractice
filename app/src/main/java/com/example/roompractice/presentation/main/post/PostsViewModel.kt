package com.example.roompractice.presentation.main.post

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.example.roompractice.data.model.Post
import com.example.roompractice.data.network.main.MainApi
import com.example.roompractice.presentation.SessionManager
import com.example.roompractice.presentation.main.Resource
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import androidx.lifecycle.Observer
import javax.inject.Inject

class PostsViewModel  @Inject constructor(private var sessionManager: SessionManager,
                                          private var mainApi: MainApi): ViewModel() {
    companion object {
        val TAG = PostsViewModel::class.java.name
    }

    private var mediatorLiveData:MediatorLiveData< Resource< List<Post>>> = MediatorLiveData()

    /**
     * Retriving  live data
     */
    public fun observePosts(): LiveData<Resource<List<Post>>> {
        if(mediatorLiveData.value == null) {
            mediatorLiveData.value = Resource.loading(null)

            //TODO Quitar el !!
            //TODO deberia llegar de otro lado:
            // RxJava Query in ViewModel with Subcomponent Dependency
            // 6:22 min
            val userId : Int = sessionManager.getAuthUser().value!!.data!!.id

            val source : LiveData<Resource<List<Post>>> = LiveDataReactiveStreams.fromPublisher(
                mainApi.getPostFromUsers(id = userId)
                    .onErrorReturn(object : Function<Throwable, List<Post>> {
                        override fun apply(t: Throwable):  List<Post> {
                            val list = mutableListOf<Post>()
                            val post = Post()
                            post.id = -1
                            list.add(post)
                            return list
                        }

                    })
                    .map(object : Function<List<Post>, Resource<List<Post>>> {
                        @Throws(Exception::class)
                        override fun apply(list: List<Post>): Resource<List<Post>> {
                            if (list.isNotEmpty() && list[0].id == -1) {
                                return Resource.error("Something went wrong", null)
                            }
                            return Resource.success(list)
                        }

                    })
                    .subscribeOn(Schedulers.io())
            )//frompublisher
            mediatorLiveData.addSource(source, Observer {
                mediatorLiveData.value = it
                //apenas lo deja de usar lo remueve al source
                mediatorLiveData.removeSource(source)
            })

        }//mediator is empty
        return mediatorLiveData
    }

}