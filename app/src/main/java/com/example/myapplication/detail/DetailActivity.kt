package com.example.myapplication.detail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityDetailBinding
import com.example.myapplication.edit.EditActivity
import dagger.hilt.android.AndroidEntryPoint

private const val EXTRA_POST_ID = "EXTRA_POST_ID"
private const val EXTRA_POST = "EXTRA_POST"

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {
    lateinit var binding : ActivityDetailBinding
    private val viewModel: DetailViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val postId = intent.getIntExtra(EXTRA_POST_ID,-1);

        viewModel.isLoading.observe(this, Observer { isLoading ->
            if (isLoading) binding.detailProgressBar.visibility = View.VISIBLE else  binding.detailProgressBar.visibility = View.GONE

            binding.clContent.visibility = if (isLoading) View.GONE else View.VISIBLE

        })

        viewModel.post.observe(this, Observer { post ->
            binding.tvPostId.text = "Post #${post.id}"
            binding.tvTitle.text = post.title
            binding.tvBody.text = post.body
        })

        viewModel.user.observe(this, Observer { user ->
            binding.tvUserName.text = user.name
            binding.tvUserEmail.text = user.email
            binding.tvUsername.text = user.username
            binding.tvWebsite.text = user.website
        })

        viewModel.getPostDetails(postId)

    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_detail,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.miEdit){
            viewModel.post.observe(this, Observer { post->
               val intent = Intent(this@DetailActivity,EditActivity::class.java)
                intent.putExtra(EXTRA_POST, post)
                startActivity(intent)
            })
        }
        return super.onOptionsItemSelected(item)
    }
}