package com.example.roompractice.presentation.main.post

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.example.roompractice.R
import com.example.roompractice.presentation.viewmodel.ViewModelProviderFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject
import kotlinx.android.synthetic.main.fragment_post.*

class PostsFragment constructor(): DaggerFragment() {
    companion object {
        val TAG = PostsFragment::class.java.name
    }

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory

    private lateinit var postsViewModel: PostsViewModel
    private lateinit var recyclerView: RecyclerView

    @Nullable
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_post, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = recycler_view

        postsViewModel = ViewModelProviders.of(this,viewModelProviderFactory ).get(PostsViewModel::class.java)
    }
}