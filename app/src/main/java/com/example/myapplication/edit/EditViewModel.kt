package com.example.myapplication.edit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.models.Post
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditViewModel @Inject constructor(val repository: EditRepository) : ViewModel() {
    private val _post = MutableLiveData<Post?>();
    val post: LiveData<Post?>
        get() = _post

    private val _currentStatus = MutableLiveData<ResultStatus>(ResultStatus.IDLE)
    val currentStatus: LiveData<ResultStatus>
        get() = _currentStatus

    private val _wasDeletionSuccessful = MutableLiveData<Boolean>(false)

    val wasDeletionSuccessful: LiveData<Boolean>
        get() = _wasDeletionSuccessful


    fun updatePost(postId: Int, newPostData: Post) {
        viewModelScope.launch {
            try {
                _post.value = null
                _currentStatus.value = ResultStatus.WORKING
                val updatedPost = repository.updatePost(postId, newPostData);
                _post.value = updatedPost
                _currentStatus.value = ResultStatus.SUCCESS

            } catch (e: Exception) {
                _currentStatus.value = ResultStatus.ERROR

            }

        }
    }




        fun updatePatch(postId: Int, title: String, body: String) {
            viewModelScope.launch {
                try {
                    _post.value = null
                    _currentStatus.value = ResultStatus.WORKING
                    val patchedPost =
                        repository.updatePatch(postId, mapOf("title" to title, "body" to body))
                    _post.value = patchedPost
                    _currentStatus.value = ResultStatus.SUCCESS
                } catch (e: Exception) {
                    _currentStatus.value = ResultStatus.ERROR
                }
            }
        }


    fun deletePost(postId: Int) {
        viewModelScope.launch {
            try {

                _post.value = null
                _currentStatus.value = ResultStatus.WORKING
                repository.deletePost(postId)
                _wasDeletionSuccessful.value = true
                _currentStatus.value = ResultStatus.SUCCESS

            } catch (e: Exception) {
                _currentStatus.value = ResultStatus.ERROR
                _wasDeletionSuccessful.value = false

            }
        }
    }


    fun createPost(id:Int , userId: Int, title : String, body : String){
        viewModelScope.launch {
            try {
                _post.value = null
                _currentStatus.value = ResultStatus.WORKING
                val updatedPost = repository.createPost( Post(userId,id,title,body));
                _post.value = updatedPost
                _currentStatus.value = ResultStatus.SUCCESS

            }catch (e: Exception){
                _currentStatus.value = ResultStatus.ERROR

            }

        }
    }

    fun createPostUrlEncode(userId: Int, title : String, body : String){
        viewModelScope.launch {
            try {
                _post.value = null
                _currentStatus.value = ResultStatus.WORKING
                val UrlEncodePost = repository.createPostUrlEncode(userId,title,body);
                _post.value = UrlEncodePost
                _currentStatus.value = ResultStatus.SUCCESS

            }catch (e: Exception){
                _currentStatus.value = ResultStatus.ERROR

            }

        }
    }
}
