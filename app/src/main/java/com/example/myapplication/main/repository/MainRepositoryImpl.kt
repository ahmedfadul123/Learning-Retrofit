package com.example.myapplication.main.repository

import com.example.myapplication.api.BlogApi
import com.example.myapplication.api.NetworkResult
import com.example.myapplication.models.Post
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(private val blogApi: BlogApi) : MainRepository {

    override suspend fun getPosts() = blogApi.getPosts()
}