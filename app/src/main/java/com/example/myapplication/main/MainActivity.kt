package com.example.myapplication.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.api.interfaces.ItemClickListener
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.detail.DetailActivity
import com.example.myapplication.main.adapters.BlogPostAdapter
import com.example.myapplication.models.Post
import dagger.hilt.android.AndroidEntryPoint

private const val EXTRA_POST_ID = "EXTRA_POST_ID"
@AndroidEntryPoint
class MainActivity : AppCompatActivity() , ItemClickListener{
    private val viewModel : MainViewModel by viewModels()
    private lateinit var blogPostsAdapter : BlogPostAdapter
    private val blogPosts = mutableListOf<Post>()
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        blogPostsAdapter = BlogPostAdapter(this, blogPosts, this)
        binding.rvPosts.adapter = blogPostsAdapter
        binding.rvPosts.layoutManager = LinearLayoutManager(this)
        viewModel.posts.observe(this, Observer { posts ->
            blogPosts.clear()
            blogPosts.addAll(posts)
            blogPostsAdapter.notifyDataSetChanged()

        })

        viewModel.errorMessage.observe(this, Observer { errorMessage ->
            if (errorMessage == null){
                binding.tvError.visibility = View.GONE
            }else{
                binding.tvError.visibility = View.VISIBLE
                binding.tvError.text = errorMessage
                Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()

            }

        })

        viewModel.isLoading.observe(this, Observer { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        })



    }

    override fun onItemClick(post: Post) {
        val intent = Intent(this@MainActivity,DetailActivity::class.java)
        intent.putExtra(EXTRA_POST_ID, post.id)
        startActivity(intent)
    }
}