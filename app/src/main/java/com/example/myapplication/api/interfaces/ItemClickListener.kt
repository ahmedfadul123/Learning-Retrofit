package com.example.myapplication.api.interfaces

import com.example.myapplication.models.Post

interface ItemClickListener {
    fun onItemClick(post : Post)
}