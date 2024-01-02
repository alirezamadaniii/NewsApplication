package com.majazi.newsapplication.peresentation.viewmodel.newslist

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.majazi.newsapplication.data.model.DataSavedList
import com.majazi.newsapplication.data.model.newslist.Data
import com.majazi.newsapplication.data.utils.ResourceListNews
import com.majazi.newsapplication.domien.usecase.GetNewsListUseCase
import com.majazi.newsapplication.domien.usecase.SaveNewsUseCase
import com.majazi.newsapplication.peresentation.ui.listnews.PassengerItemDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewListViewModel @Inject constructor(
    private val getNewsListUseCase: GetNewsListUseCase,
    private val saveNewsUseCase: SaveNewsUseCase
) : ViewModel(){

    val newsList :MutableLiveData<ResourceListNews<Data>> = MutableLiveData()
    val isInternetAvailable :MutableLiveData<String> = MutableLiveData()

    fun getNewsList(catId:String,number:String) : Flow<PagingData<Data>> {
//        newsList.postValue(ResourceListNews.Loading())
//        try {1
//            if (isInternetAvailable(app)){
//                val apiResult  = getNewsListUseCase.execute(catId,true,page, number)
//                newsList.postValue(apiResult)
//            }else{
//                val apiResult  = getNewsListUseCase.execute(catId,false,page, number)
//                newsList.postValue(apiResult)
//                isInternetAvailable.postValue("کاربر گرامی شما به اینترنت متصل نیستید")
//            }
//        }catch (e:Exception){
//            newsList.postValue(ResourceListNews.Error(e.message.toString()))
//        }

        var handleRequest = Pager(
            config = PagingConfig(
                pageSize = 8
            ), pagingSourceFactory = {
                PassengerItemDataSource(
                    getNewsListUseCase,catId,number
                )
            }
        ).flow.flowOn(Dispatchers.IO)
            .cachedIn(viewModelScope)
        return handleRequest

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

  // save news to database
    fun saveNews(data: DataSavedList) = viewModelScope.launch {
        saveNewsUseCase.execute(data)
    }

//    fun getSavedNews() = liveData {
//        getNewsFromDbUseCase.execute().collect{
//            emit(it)
//        }
//    }


}