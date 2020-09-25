package com.haag.news_app.ui

import android.util.Log
import android.view.View
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.Navigation
import com.haag.news_app.model.ArticleResponse
import com.haag.news_app.model.TopNews
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class NewsViewModel @ViewModelInject constructor(private val repo: NewsRepository) : ViewModel() {
    fun helloFromvm() = "Hello from vm and ${repo.helloFromRepo()}"
    private val _userData = MutableLiveData<ArticleResponse>()
    val userData: LiveData<ArticleResponse> get() = _userData

    init {
        getTopNews()
    }
    fun getTopNews(){
        viewModelScope.launch (Dispatchers.IO){
            try {
                val response = repo.getTopNews()

                withContext(Dispatchers.Main) {
                    _userData.value = response.body()
                }

                response.body()?.articles?.forEach {
//                    Log.d(",,", "response vm: ${it.title}")
                }

            } catch (e: Exception) {
                Log.d(",,", "Exception: $e")
            }
        }
    }

    // Navigation
    fun navigateTo(view: View, fragmentId: Int) {
        Navigation.findNavController(view)
            .navigate(fragmentId)
    }
}