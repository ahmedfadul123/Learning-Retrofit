package com.example.myapplication.edit

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.databinding.ActivityEditBinding
import com.example.myapplication.main.MainActivity
import com.example.myapplication.models.Post
import dagger.hilt.android.AndroidEntryPoint

private const val EXTRA_POST = "EXTRA_POST"

@AndroidEntryPoint
class EditActivity : AppCompatActivity() {
    lateinit var binding : ActivityEditBinding
    private val viewModel: EditViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val post = intent.getSerializableExtra(EXTRA_POST) as Post

        title = "Editing Post #${post.id}"
        binding.etTitle.setText(post.title)
        binding.etContent.setText(post.body)

        viewModel.post.observe(this, Observer { updatedPost ->
            if (updatedPost == null){
                binding.clPostResult.visibility = View.GONE
                return@Observer
            }
            binding.tvUpdatedTitle.text = updatedPost.title
            binding.tvUpdatedContent.text = updatedPost.body
            binding.clPostResult.visibility = View.VISIBLE
        })

        viewModel.currentStatus.observe(this, Observer { currentStatus ->
            when(currentStatus) {
                ResultStatus.IDLE -> {
                    binding.tvStatus.text = "Idle"
                    binding.tvStatus.setTextColor(Color.GRAY)
                }
                ResultStatus.WORKING -> {
                    binding.tvStatus.text = "Working..."
                    binding.tvStatus.setTextColor(Color.MAGENTA)
                }
                ResultStatus.SUCCESS -> {
                    binding.tvStatus.text = "Success!"
                    binding.tvStatus.setTextColor(Color.GREEN)
                }
                ResultStatus.ERROR -> {
                    binding.tvStatus.text = "Error :("
                    binding.tvStatus.setTextColor(Color.RED)
                }
                else -> {
                    throw IllegalStateException("Unexpected result state found")
                }
            }



        })

        viewModel.wasDeletionSuccessful.observe(this, Observer { wasDeletionSuccessful ->

            if (wasDeletionSuccessful){
                Toast.makeText(this,"Deleted post successfully!",Toast.LENGTH_LONG).show()
                val intent = Intent(this,MainActivity::class.java)
                startActivity(intent)
            }
        })


        binding.btnUpdatePut.setOnClickListener {
            viewModel.updatePost(post.id, Post(post.userId,
                post.id,
                binding.etTitle.text.toString(),
                binding.etContent.text.toString())
            )
        }


        binding.btnUpdatePatch.setOnClickListener {
            viewModel.updatePatch(post.id, binding.etTitle.text.toString(), binding.etContent.text.toString())
        }


        binding.btnCreatePost.setOnClickListener {
            viewModel.createPost(8,post.userId, binding.etTitle.text.toString(), binding.etContent.text.toString())
        }

        binding.btnUrlEncoded.setOnClickListener {
            viewModel.createPost(9,post.userId, binding.etTitle.text.toString(), binding.etContent.text.toString())
        }


        binding.btnDelete.setOnClickListener {
            viewModel.deletePost(post.id)
        }


    }


    }
