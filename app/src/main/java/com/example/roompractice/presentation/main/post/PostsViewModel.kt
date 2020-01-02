package com.example.roompractice.presentation.main.post

import androidx.lifecycle.*
import com.example.roompractice.data.model.Post
import com.example.roompractice.data.repository.main.PostRepository
import com.example.roompractice.presentation.SessionManager
import com.example.roompractice.presentation.main.Resource
import javax.inject.Inject

class PostsViewModel  @Inject constructor(private var sessionManager: SessionManager,
                                          private var repository: PostRepository): ViewModel() {
    companion object {
        val TAG = PostsViewModel::class.java.name
    }

    private var mediatorLiveData:MediatorLiveData< Resource< List<Post>>> = MediatorLiveData()

    /**
     * Retrieving  live data
     */
    fun observePosts(): LiveData<Resource<List<Post>>> {
        if(mediatorLiveData.value == null) {
            mediatorLiveData.value = Resource.loading(null)

            //TODO It should be come from other side:
            // RxJava Query in ViewModel with SubComponent Dependency
            // 6:22 min
            val userId : Int = sessionManager.getAuthUser().value?.data?.id
                ?: return MutableLiveData(
                    Resource.error(
                        "Something went wrong",
                        getWrongListOfPost()
                    )
                )
            val source : LiveData<Resource<List<Post>>> = LiveDataReactiveStreams.fromPublisher(
                repository.getPost(fromUserId = userId)
            )//fromPublisher
            mediatorLiveData.addSource(source) {
                mediatorLiveData.value = it
                //as soon as it stops using it it removes it to the source
                mediatorLiveData.removeSource(source)
            }

        }//mediator is empty
        return mediatorLiveData
    }

    private fun getWrongListOfPost(): List<Post> {
        val list = mutableListOf<Post>()
        val post = Post()
        post.id = -1
        list.add(post)
        return list
    }

}