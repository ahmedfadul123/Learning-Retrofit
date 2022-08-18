package com.example.myapplication.detail

import com.example.myapplication.api.BlogApi
import com.example.myapplication.models.Post
import javax.inject.Inject

class DetailRepositoryImpl @Inject constructor(val blogApi: BlogApi) : DetailRepository {

    override suspend fun getPost(postId: Int) = blogApi.getPost(postId)
    override suspend fun getUser(userId : Int)  = blogApi.getUser(userId)
}