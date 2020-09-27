package com.haag.news_app.ui.newsList

import android.util.Log.d
import android.view.View
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import com.haag.news_app.model.ArticleResponse
import com.haag.news_app.room.Read
import com.haag.news_app.repo.NewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NewsViewModel @ViewModelInject constructor(private val repo: NewsRepository) : ViewModel() {
    private val _userData = MutableLiveData<ArticleResponse>()
    val getArticlesList: LiveData<ArticleResponse> get() = _userData

    init {
        getTopNews()
    }

    private fun getTopNews() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repo.getTopNews()

                withContext(Dispatchers.Main) {
                    _userData.value = response.body()
                }
            } catch (e: Exception) {
                d("NewsViewModel", "Exception: $e")
            }
        }
    }

    fun insertTitleDao(title: Read) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repo.insertTitleDao(title)
            } catch (e: Exception) {
                d("NewsViewModel", "Exception: $e")
            }
        }
    }

    fun getTitleList(): LiveData<List<Read>> = liveData {
        try {
            val response = repo.getTitleList()

            emit(response)
        } catch (e: Exception) {
            d("NewsViewModel", "Exception: $e")
        }
    }


    // Navigation
    fun navigateTo(view: View, navDir: NavDirections) {
        Navigation.findNavController(view)
            .navigate(navDir)
    }
}