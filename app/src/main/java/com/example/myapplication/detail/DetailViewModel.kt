package com.example.myapplication.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.models.Post
import com.example.myapplication.models.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(val repository: DetailRepository) : ViewModel() {
     private val _post = MutableLiveData<Post>();
    val post : LiveData<Post>
    get() = _post

    private val _user = MutableLiveData<User>();

    val user : LiveData<User>
    get() = _user

   private val _isLoading = MutableLiveData<Boolean>()
    val isLoading :LiveData<Boolean>
    get() = _isLoading


    fun getPostDetails(postId : Int){
        try {

            viewModelScope.launch {
                _isLoading.value = true
                val fetchedPost = repository.getPost(postId)
                val fetchedUser = repository.getUser(fetchedPost.userId)
                _post.value = fetchedPost
                _user.value = fetchedUser

            }


        }catch (e: Exception){

        }finally {
            _isLoading.value = false
        }
    }


}