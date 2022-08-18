package com.example.myapplication.api

import com.example.myapplication.models.Post
import com.example.myapplication.models.User
import retrofit2.http.*

interface BlogApi {
    @GET("posts")
    suspend fun getPosts(@Query("_page") page : Int = 1, @Query("_limit") limit :Int = 10) : List<Post>

    @GET("posts/{id}")
    suspend fun getPost(@Path("id") postId : Int) : Post

    @GET("users/{id}")
    suspend fun getUser(@Path("id") userId : Int) : User

    @PUT("posts/{id}")
    suspend fun updatePost(@Path("id") postId: Int, @Body post: Post) : Post

    @PATCH("posts/{id}")
    suspend fun updatePatch(@Path("id") postId: Int, @Body params : Map<String,String>) : Post

    @DELETE("posts/{id}")
    suspend fun deletePost(@Path("id") postId: Int )

    @POST("posts/")
    suspend fun createPost(@Body post :Post) : Post

    @FormUrlEncoded
    @POST
    suspend fun createPostUrlEncode(
        @Field("userId") userId: Int,
        @Field("title") title : String,
        @Field("body") body : String
    ):Post



}