package com.majazi.newsapplication.peresentation.viewmodel.search

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.majazi.newsapplication.data.model.search.Search
import com.majazi.newsapplication.data.utils.Resource
import com.majazi.newsapplication.domien.usecase.GetSearchNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchNewsViewModel @Inject constructor(
    private val app :Application,
    private val getSearchNewsUseCase: GetSearchNewsUseCase
):AndroidViewModel(app) {
    var news :MutableLiveData<Resource<Search>> = MutableLiveData()

    fun getNewsFromSearch(search: String) = viewModelScope.launch(Dispatchers.IO) {
        news.postValue(Resource.Loading())
        try {
            if (isInternetAvailable(app)){
                val apiResult = getSearchNewsUseCase.execute(search)
                news.postValue(apiResult)
            }else{
                news.postValue(Resource.Error("Internet is not available"))
            }
        }catch (e:Exception){
            news.postValue(Resource.Error(e.message.toString()))
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
}