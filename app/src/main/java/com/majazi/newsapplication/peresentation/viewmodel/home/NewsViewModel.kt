package com.majazi.newsapplication.peresentation.viewmodel.home

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.majazi.newsapplication.data.model.Category
import com.majazi.newsapplication.data.model.getuser.GetUser
import com.majazi.newsapplication.data.model.homenews.ItemNews
import com.majazi.newsapplication.data.model.interestpost.InterestPost
import com.majazi.newsapplication.data.model.trendingnews.Post
import com.majazi.newsapplication.data.model.trendingnews.TrendingNews
import com.majazi.newsapplication.data.utils.Resource
import com.majazi.newsapplication.data.utils.ResourceItemNews
import com.majazi.newsapplication.data.utils.ResourceTrending
import com.majazi.newsapplication.domien.usecase.GetAllCounterUseCase
import com.majazi.newsapplication.domien.usecase.GetAppIconUseCase
import com.majazi.newsapplication.domien.usecase.GetCategoryUseCase
import com.majazi.newsapplication.domien.usecase.GetInterestPostsUseCase
import com.majazi.newsapplication.domien.usecase.GetNewsUseCase
import com.majazi.newsapplication.domien.usecase.GetTrendingNewsUseCase
import com.majazi.newsapplication.domien.usecase.GetUserAuthUseCase
import com.majazi.newsapplication.domien.usecase.SaveCounterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val app:Application,
    private val getNewsUseCase: GetNewsUseCase,
    private val getTrendingNewsUseCase: GetTrendingNewsUseCase,
    private val getAppIconUseCase: GetAppIconUseCase,
    private val getUserAuthUseCase: GetUserAuthUseCase,
    private val getInterestPostsUseCase: GetInterestPostsUseCase,
    private val saveCounterUseCase: SaveCounterUseCase,
    private val getCategoryUseCase: GetCategoryUseCase,
    private val getAllCounterUseCase: GetAllCounterUseCase

):AndroidViewModel(app) {

    val news: MutableLiveData<ResourceItemNews<ItemNews>> = MutableLiveData()
    val trendingNews: MutableLiveData<ResourceTrending<Post>> = MutableLiveData()
    val appIcon: MutableLiveData<Resource<TrendingNews>> = MutableLiveData()
    val userAuthUseCase: MutableLiveData<Resource<GetUser>> = MutableLiveData()
    val isInternetAvailable:MutableLiveData<Boolean> = MutableLiveData()
    val interestPost:MutableLiveData<Resource<InterestPost>> = MutableLiveData()


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

    fun userAuth(name:String,phone:String) = viewModelScope.launch(Dispatchers.IO) {
        try {
                userAuthUseCase.postValue(getUserAuthUseCase.execute(name, phone))
                isInternetAvailable.postValue(true)

        } catch (e: Exception) {
            userAuthUseCase.postValue(Resource.Error(e.message.toString()))
        }
    }


    @Suppress("DEPRECATION")
    private fun isInternetAvailable(context: Context): Boolean {
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

    fun getAppIcon() =viewModelScope.launch(Dispatchers.IO) {
        appIcon.postValue(Resource.Loading())
        try {
            if (isInternetAvailable(app)){
                val apiResult = getAppIconUseCase.execute()
                appIcon.postValue(apiResult)
            }else{
                val apiResult = getAppIconUseCase.execute()
                appIcon.postValue(apiResult)
            }
        } catch (e: Exception) {
            appIcon.postValue(Resource.Error(e.message.toString()))
        }
    }

    fun getInterestPost(userId:String,categoryId: String)=viewModelScope.launch(Dispatchers.IO) {
        try {
            val apiResult = getInterestPostsUseCase.execute(userId,categoryId)
           interestPost.postValue(apiResult)
        } catch (e: Exception) {
            appIcon.postValue(Resource.Error(e.message.toString()))
        }
    }

    fun addCounter(category: Category) = viewModelScope.launch {
        saveCounterUseCase.execute(category)
    }


     fun getCounter(categoryId: Int) = liveData {
         try {
             getCategoryUseCase.execute(categoryId).collect{
                 emit(it)
             }
         }catch (e:Exception){
             Log.i("TAG", "getCounter: "+e.message)
         }
    }

    fun getAllCounter()= liveData{
        try {
            getAllCounterUseCase.execute().collect{
                emit(it)
            }
        }catch (e:Exception){
            Log.i("TAG", "getCounter: "+e.message)

        }
    }

}