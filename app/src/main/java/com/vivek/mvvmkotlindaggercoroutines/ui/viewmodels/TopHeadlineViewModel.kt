package com.vivek.mvvmkotlindaggercoroutines.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vivek.mvvmkotlindaggercoroutines.data.model.Article
import com.vivek.mvvmkotlindaggercoroutines.data.repository.TopHeadlineRepository
import com.vivek.mvvmkotlindaggercoroutines.utils.AppConstant.COUNTRY
import com.vivek.mvvmkotlindaggercoroutines.utils.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class TopHeadlineViewModel(private val topHeadlineRepository: TopHeadlineRepository)  : ViewModel(){
    private val _articleList  = MutableStateFlow<Resource<List<Article>>>(Resource.loading())
    val articleList: StateFlow<Resource<List<Article>>> = _articleList

    init {
        fetchTopHeadlines()
    }

    private fun fetchTopHeadlines() {

        viewModelScope.launch {
            topHeadlineRepository.getTopHeadlines(COUNTRY)
                .catch { e -> _articleList.value = Resource.error(e.toString())

                }
                .collect{
                    _articleList.value = Resource.success(it)
                }
        }
    }

}