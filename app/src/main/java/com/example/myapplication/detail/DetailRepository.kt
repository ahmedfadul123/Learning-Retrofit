package com.example.myapplication.detail

import com.example.myapplication.models.Post
import com.example.myapplication.models.User

interface DetailRepository {
    suspend fun getPost(postId : Int) : Post
    suspend fun getUser(userId : Int) : User

}