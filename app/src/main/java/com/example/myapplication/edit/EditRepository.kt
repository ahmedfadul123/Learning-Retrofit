package com.example.myapplication.edit

import com.example.myapplication.models.Post

interface EditRepository {
    suspend fun updatePost(postId :Int, newPostData:Post) : Post
    suspend fun updatePatch(postId: Int, params : Map<String,String>) : Post
    suspend fun deletePost( postId: Int )
    suspend fun createPost(post :Post) : Post
    suspend fun createPostUrlEncode(userId: Int, title : String, body : String):Post
}