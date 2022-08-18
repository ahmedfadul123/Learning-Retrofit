package com.example.myapplication.edit

import com.example.myapplication.api.BlogApi
import com.example.myapplication.models.Post
import javax.inject.Inject

class EditRepositoryImpl @Inject constructor(val blogApi: BlogApi) : EditRepository {
    override suspend fun updatePost(postId: Int, newPostData: Post) = blogApi.updatePost(postId,newPostData)
    override suspend fun updatePatch(postId: Int, params: Map<String, String>): Post = blogApi.updatePatch(postId,params)

    override suspend fun deletePost(postId: Int) = blogApi.deletePost(postId)

    override suspend fun createPost(post: Post) = blogApi.createPost(post)

    override suspend fun createPostUrlEncode(userId: Int, title: String, body: String) = blogApi.createPostUrlEncode(userId,title,body)
}