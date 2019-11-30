package com.example.roompractice.presentation.main.post

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.roompractice.R
import com.example.roompractice.data.model.Post
import com.example.roompractice.databinding.LayoutPostListItemBinding

class PostsRecyclerAdapter:  RecyclerView.Adapter<PostsRecyclerAdapter.ViewHolder>() {

    private var postList:List<Post> = listOf()

    override fun getItemCount(): Int {
        return postList.size
    }

    public fun setPosts(postList: List<Post>) {
        this.postList = postList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsRecyclerAdapter.ViewHolder {
        val layoutInflater : LayoutInflater = LayoutInflater.from(parent.context)
        val binding : LayoutPostListItemBinding =
            LayoutPostListItemBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostsRecyclerAdapter.ViewHolder, position: Int) {
        val post :Post = this.postList[position]
        holder.bind(post)
    }

    inner class ViewHolder(var binding: LayoutPostListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        public fun bind(post: Post) {
            binding.model = post
            binding.executePendingBindings()
        }
    }
}