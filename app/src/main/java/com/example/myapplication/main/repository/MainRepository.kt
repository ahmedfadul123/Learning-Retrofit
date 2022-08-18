package com.example.myapplication.main.repository

import androidx.lifecycle.LiveData
import com.example.myapplication.api.NetworkResult
import com.example.myapplication.models.Post
import kotlinx.coroutines.flow.Flow

interface MainRepository  {


    suspend fun getPosts() :List<Post>
}