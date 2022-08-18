package com.example.myapplication.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.api.NetworkResult
import com.example.myapplication.main.repository.MainRepository
import com.example.myapplication.models.Post
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "MainViewModel"
@HiltViewModel
class MainViewModel @Inject constructor(private val repository: MainRepository) : ViewModel() {
    private val _posts : MutableLiveData<List<Post>> = MutableLiveData();
    val posts :LiveData<List<Post>>
    get() = _posts



    private val _isLoading = MutableLiveData(false)
    val isLoading :LiveData<Boolean>
    get() = _isLoading

    private val _errorMessage = MutableLiveData<String?>(null)
    val errorMessage : LiveData<String?>
    get() = _errorMessage

    private var currentPage = 1


    init {
     getPosts()
    }

    fun getPosts(){
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val fetchedPosts = repository.getPosts()
                currentPage += 1
                val currentPosts = _posts.value ?: emptyList()
                _posts.value = currentPosts + fetchedPosts
            }catch (e : Exception){
                Log.e(TAG, "Exception $e")
                _errorMessage.value = e.message

            }finally {
                _isLoading.value = false
            }
        }
    }

}