package uz.elmurod.domain.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.elmurod.domain.data.database.ArticleEntity
import uz.elmurod.domain.data.ui.Articles
import uz.elmurod.domain.repository.NewsRepository
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(private val repository: NewsRepository) : ViewModel() {
    var job: Job? = null
    private val currentQuery = MutableLiveData(DEFAULT_QUERY)
    private val currentCategory = MutableLiveData(DEFAULT_QUERY)

    private val _searchEveryThing = MutableLiveData<PagingData<Articles>>()
    val searchEveryThing: LiveData<PagingData<Articles>> = _searchEveryThing

    private val _getNewsCategory = MutableLiveData<PagingData<Articles>>()
    val getNewsCategory: LiveData<PagingData<Articles>> = _getNewsCategory

    private val _networkState = MutableLiveData<Boolean>()
    val networkState: LiveData<Boolean> = _networkState

    private val _getNewsSaved = MutableLiveData<List<ArticleEntity>>()
    val getNewsSaved: LiveData<List<ArticleEntity>> = _getNewsSaved

    private val _checkNews = MutableLiveData<Int>()
    val checkNews: LiveData<Int> = _checkNews

    fun searchEveryThing(query: String) {
        job?.cancel()
        currentQuery.value = query
        job = viewModelScope.launch {
            repository.searchEveryThing(searchQuery = query)?.cachedIn(viewModelScope)
                ?.collectLatest {
                    _searchEveryThing.postValue(it)
                }
        }
    }

    fun getNewsCategory(categoryCode: String) {
        job?.cancel()
        currentCategory.value = categoryCode
        job = viewModelScope.launch {
            repository.getNewsCategory(categoryCode = categoryCode)?.cachedIn(viewModelScope)
                ?.collectLatest {
                    _getNewsCategory.postValue(it)
                }
        }
    }

    fun getNetworkStatus() {
        repository.getNetworkStatus().onEach {
            _networkState.postValue(it)
        }.launchIn(viewModelScope)
    }

    fun saveArticle(articles: Articles) {
        viewModelScope.launch {
            repository.insertArticle(
                ArticleEntity(
                    articles.id,
                    articles.author,
                    articles.title,
                    articles.description,
                    articles.url,
                    articles.urlToImage,
                    articles.publishedAt,
                    articles.content
                )
            )
        }
    }

    fun getNewsSaved() {
        viewModelScope.launch {
            repository.getNewsAllSaved().onEach {
                _getNewsSaved.postValue(it)
            }.launchIn(viewModelScope)
        }
    }

    fun deleteArticle(articles: ArticleEntity) {
        viewModelScope.launch {
            repository.deleteArticle(articles)
        }
    }

    fun checkNews(id: Int) =
        viewModelScope.launch {
            repository.checkNews(id).apply {
                _checkNews.postValue(this)
            }
        }


    companion object {
        private const val DEFAULT_QUERY = "business"
    }

}

