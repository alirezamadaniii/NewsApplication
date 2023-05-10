package com.majazi.newsapplication.peresentation.viewmodel.newslist

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.majazi.newsapplication.data.model.newslist.NewsList
import com.majazi.newsapplication.data.utils.Resource
import com.majazi.newsapplication.domien.usecase.GetNewsListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewListViewModel(
    private val app:Application,
    private val getNewsListUseCase: GetNewsListUseCase
) :AndroidViewModel(app){

    val newsList :MutableLiveData<Resource<NewsList>> = MutableLiveData()

    fun getNewsList(catId:String) =viewModelScope.launch(Dispatchers.IO) {
        newsList.postValue(Resource.Loading())
        try {
            if (isInternetAvailable(app)){
                val apiResult  = getNewsListUseCase.execute(catId)
                newsList.postValue(apiResult)
            }else{
                newsList.postValue(Resource.Error("Internet is not available"))
            }
        }catch (e:Exception){
            newsList.postValue(Resource.Error(e.message.toString()))
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

}