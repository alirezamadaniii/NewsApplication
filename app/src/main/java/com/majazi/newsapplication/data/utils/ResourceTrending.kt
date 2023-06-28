package com.majazi.newsapplication.data.utils

import com.majazi.newsapplication.data.model.homenews.ItemNews
import com.majazi.newsapplication.data.model.trendingnews.Post

sealed class ResourceTrending<T>(
    var data: List<Post> = emptyList(),
    val message: String? = null
) {

    class Success(data: List<Post>) : ResourceTrending<Post>(data = data)
    class Loading(data: List<Post> = emptyList()) : ResourceTrending<Post>(data = data)
    class Error(message: String, data: List<Post> = emptyList()) : ResourceTrending<Post>(data = data, message = message)
}