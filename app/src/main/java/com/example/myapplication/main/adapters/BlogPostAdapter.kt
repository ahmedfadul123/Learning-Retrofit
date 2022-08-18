package com.example.myapplication.main.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.api.interfaces.ItemClickListener
import com.example.myapplication.models.Post

class BlogPostAdapter(
    private val context :Context,
    private val posts :List<Post>,
    private val itemClickListener: ItemClickListener
):RecyclerView.Adapter<BlogPostAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_blog_post,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(posts[position])
    }

    override fun getItemCount() = posts.size


    inner class ViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){
        private val tvId = itemView.findViewById<TextView>(R.id.tvId)
        private val tvTitle = itemView.findViewById<TextView>(R.id.tvTitle)
        private val tvBody = itemView.findViewById<TextView>(R.id.tvBlogBody)

        fun bind(post :Post){
            tvId.text = "Post #${post.id}"
            tvTitle.text = post.title
            tvBody.text = post.body
            itemView.setOnClickListener {
                itemClickListener.onItemClick(post)
            }
        }

    }
}