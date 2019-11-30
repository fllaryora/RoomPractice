package com.example.roompractice.presentation.main.post

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roompractice.R
import com.example.roompractice.databinding.FragmentPostBinding
import com.example.roompractice.presentation.databinding.withTwoWayBinding
import com.example.roompractice.presentation.main.Resource
import com.example.roompractice.presentation.viewmodel.ViewModelProviderFactory
import com.example.roompractice.utils.VerticalSpacingItemDecoration
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class PostsFragment constructor(): DaggerFragment() {
    companion object {
        val TAG = PostsFragment::class.java.name
    }

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory

    @Inject
    lateinit var postsRecyclerAdapter: PostsRecyclerAdapter

    private lateinit var postsViewModel: PostsViewModel

    /**
     * binding by lazy FragmentProfileBinding
     */
    lateinit var binding : FragmentPostBinding

    @Nullable
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = withTwoWayBinding(inflater,R.layout.fragment_post, container)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        postsViewModel = ViewModelProviders.of(this,viewModelProviderFactory ).
            get(PostsViewModel::class.java)

        /**
         * This bind the databinding with MVVVM
         */
        //there is not data & variables to bond binding.viewModel = postsViewModel

        initRecyclerView()
        subscribeObservers()
    }

    private fun subscribeObservers() {
        /**
         * Se tiene que tener en cuenta que el ciclo de vida de los fragments es diferente al ciclo de vida
         * de las activities.
         * Por esta razon es diferente al de la activity
         * Y debemos estar seguros de remover los viejos vinagres (los observers) todo al rededor.
         */
        postsViewModel.observePosts().removeObservers(viewLifecycleOwner)
        postsViewModel.observePosts().observe(viewLifecycleOwner, Observer {
            it?.let { resource ->
                when(resource.status) {

                    Resource.Status.LOADING -> {
                        Log.d(TAG, "onChanged: LOADING...")
                    }
                    Resource.Status.SUCCESS -> {
                        Log.d(TAG, "onChanged: got posts...")
                        val unwrappedData = resource.data ?: listOf()
                        postsRecyclerAdapter.setPosts(unwrappedData)
                    }

                    Resource.Status.ERROR -> {
                        Log.e(TAG, "onChanged: ERROR..." + resource.message )
                    }

                }
            }
        })
    }

    private fun initRecyclerView(){
        binding.recyclerView.layoutManager = LinearLayoutManager(activity)
        val verticalSpacingItemDecoration = VerticalSpacingItemDecoration(15)
        binding.recyclerView.addItemDecoration(verticalSpacingItemDecoration)
        binding.recyclerView.adapter = postsRecyclerAdapter
    }
}