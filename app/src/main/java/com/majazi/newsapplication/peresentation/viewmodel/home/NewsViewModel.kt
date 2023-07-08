package com.majazi.newsapplication.peresentation.viewmodel.home

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.majazi.newsapplication.data.model.homenews.ItemNews
import com.majazi.newsapplication.data.model.trendingnews.Post
import com.majazi.newsapplication.data.utils.ResourceItemNews
import com.majazi.newsapplication.data.utils.ResourceTrending
import com.majazi.newsapplication.domien.usecase.GetNewsUseCase
import com.majazi.newsapplication.domien.usecase.GetTrendingNewsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewsViewModel(
    private val app:Application,
    private val getNewsUseCase: GetNewsUseCase,
    private val getTrendingNewsUseCase: GetTrendingNewsUseCase

):AndroidViewModel(app) {

    val news: MutableLiveData<ResourceItemNews<ItemNews>> = MutableLiveData()
    val trendingNews: MutableLiveData<ResourceTrending<Post>> = MutableLiveData()
    val isInternetAvailable:MutableLiveData<Boolean> = MutableLiveData()


    fun getNews() = viewModelScope.launch(Dispatchers.IO) {
        news.postValue(ResourceItemNews.Loading())
        try {
            if (isInternetAvailable(app)) {
                val apiResult = getNewsUseCase.execute(true)
                news.postValue(apiResult)
                isInternetAvailable.postValue(true)
            }else{
                val apiResult = getNewsUseCase.execute(false)
                news.postValue(apiResult)
                isInternetAvailable.postValue(false)
            }
        } catch (e: Exception) {
            news.postValue(ResourceItemNews.Error(e.message.toString()))
        }
    }


    @Suppress("DEPRECATION")
    fun isInternetAvailable(context: Context): Boolean {
        var result = false
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            cm?.run {
                cm.getNetworkCapabilities(cm.activeNetwork)?.run {
                    result = when {
                        hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                        hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                        hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                        else -> false
                    }
                }
            }
        } else {
            cm?.run {
                cm.activeNetworkInfo?.run {
                    if (type == ConnectivityManager.TYPE_WIFI) {
                        result = true
                    } else if (type == ConnectivityManager.TYPE_MOBILE) {
                        result = true
                    }
                }
            }
        }
        return result
    }

    fun getTrendingNews() = viewModelScope.launch(Dispatchers.IO) {
        trendingNews.postValue(ResourceTrending.Loading())
        try {
            if (isInternetAvailable(app)){
            val apiResult = getTrendingNewsUseCase.execute(true)
            trendingNews.postValue(apiResult)
            }else{
                val apiResult = getTrendingNewsUseCase.execute(false)
                trendingNews.postValue(apiResult)
            }
        } catch (e: Exception) {
            trendingNews.postValue(ResourceTrending.Error(e.message.toString()))
        }

    }


}