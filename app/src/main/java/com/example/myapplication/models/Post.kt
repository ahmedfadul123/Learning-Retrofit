package com.example.myapplication.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class Post (
    @SerializedName("userId")
    @Expose
    val userId : Int,
    @SerializedName("id")
    @Expose
    val id : Int ,
    @SerializedName("title")
    @Expose
    val title : String ,
    @SerializedName("body")
    @Expose
    val body : String) : Serializable