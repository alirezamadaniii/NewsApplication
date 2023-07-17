package com.majazi.newsapplication.peresentation.viewmodel.detailnews

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.majazi.newsapplication.data.model.detailnews.DetailNews
import com.majazi.newsapplication.data.model.detailnews.comment.Comment
import com.majazi.newsapplication.data.model.detailnews.comment.SignInUser
import com.majazi.newsapplication.data.model.detailnews.comment.sendcomment.SendComment
import com.majazi.newsapplication.data.utils.Resource
import com.majazi.newsapplication.domien.usecase.GetCommentUseCase
import com.majazi.newsapplication.domien.usecase.GetDetailNewsUseCase
import com.majazi.newsapplication.domien.usecase.GetUserUseCase
import com.majazi.newsapplication.domien.usecase.SendCommentUseCase
import com.majazi.newsapplication.domien.usecase.SignInUserUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailNewsViewModel(
    private val app:Application,
    private val getDetailNewsUseCase: GetDetailNewsUseCase,
    private val getCommentUseCase: GetCommentUseCase,
    private val signInUserUseCase: SignInUserUseCase,
    private val getUserUseCase: GetUserUseCase,
    private val sendCommentUseCase: SendCommentUseCase

):AndroidViewModel(app) {
    val news:MutableLiveData<Resource<DetailNews>> = MutableLiveData()
    val comment:MutableLiveData<Resource<Comment>> = MutableLiveData()
    val sendComment:MutableLiveData<Resource<SendComment>> = MutableLiveData()

    fun getDetailNews(id:String) = viewModelScope.launch(Dispatchers.IO) {
        news.postValue(Resource.Loading())
        try {
            if (isInternetAvailable(app)){
                val apiResult  = getDetailNewsUseCase.execute(id)
                news.postValue(apiResult)
            }else{
                news.postValue(Resource.Error("Internet is not available"))
            }
        }catch (e:Exception){
            news.postValue(Resource.Error(e.message.toString()))
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

    fun getComment(id: String) = viewModelScope.launch(Dispatchers.IO) {
        comment.postValue(Resource.Loading())
        try {
            if (isInternetAvailable(app)){
                val apiResult = getCommentUseCase.execute(id)
                comment.postValue(apiResult)
            }else{
                comment.postValue(Resource.Error("Internet is not available"))
            }
        }catch (e:Exception){
            comment.postValue(Resource.Error(e.message.toString()))
        }
    }



    fun signInUser(signInUser: SignInUser) =viewModelScope.launch(Dispatchers.Main) {
        signInUserUseCase.execute(signInUser)
    }


    fun getUser() = liveData {
        getUserUseCase.execute().collect{
            emit(it)
        }

    }


    fun sendCommentNews(
        comment: String,
        postId:String,
        name:String,
        phone:String
    )=viewModelScope.launch(Dispatchers.IO) {
        sendComment.postValue(Resource.Loading())
        try {
            if (isInternetAvailable(app)){
                val apiResult = sendCommentUseCase.execute(comment, postId, name, phone)
                sendComment.postValue(apiResult)
            }else{
                sendComment.postValue(Resource.Error("Internet is not available"))
            }
        }catch (e:Exception){
            sendComment.postValue(Resource.Error(e.message.toString()))
        }
    }




}