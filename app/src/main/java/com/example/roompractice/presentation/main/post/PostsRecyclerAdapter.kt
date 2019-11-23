package com.example.roompractice.presentation.main.post

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.roompractice.R
import com.example.roompractice.data.model.Post
import kotlinx.android.synthetic.main.layout_post_list_item.view.*

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
        val viewRow :View = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_post_list_item,parent,false)
        return ViewHolder(viewRow)
    }

    override fun onBindViewHolder(holder: PostsRecyclerAdapter.ViewHolder, position: Int) {
        val post :Post = this.postList[position]
        holder.bind(post)
    }

    inner class ViewHolder(var view : View) : RecyclerView.ViewHolder(view) {
        public fun bind(post: Post) {
            view.title.text = post.title
        }
    }
}