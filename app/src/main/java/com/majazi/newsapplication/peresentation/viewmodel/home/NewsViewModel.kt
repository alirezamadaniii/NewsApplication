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
import com.majazi.newsapplication.data.model.trendingnews.TrendingNews
import com.majazi.newsapplication.data.utils.Resource
import com.majazi.newsapplication.data.utils.Resource2
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

    val news: MutableLiveData<Resource2<ItemNews>> = MutableLiveData()
    val trendingNews: MutableLiveData<ResourceTrending<Post>> = MutableLiveData()


    fun getNews() = viewModelScope.launch(Dispatchers.IO) {
        news.postValue(Resource2.Loading())
        try {
//            if (isInternetAvailable(app)){
            val apiResult = getNewsUseCase.execute()
            news.postValue(apiResult)
//            }else{
//                news.postValue(Resource.Error("Internet is not available"))
//            }
        } catch (e: Exception) {
            news.postValue(Resource2.Error(e.message.toString()))
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
//            if (isInternetAvailable(app)){
            val apiResult = getTrendingNewsUseCase.execute()
            trendingNews.postValue(apiResult)
//            }else{
//                news.postValue(Resource.Error("Internet is not available"))
//            }
        } catch (e: Exception) {
            trendingNews.postValue(ResourceTrending.Error(e.message.toString()))
        }

    }
}