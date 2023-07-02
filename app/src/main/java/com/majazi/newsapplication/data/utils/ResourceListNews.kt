package com.majazi.newsapplication.data.utils

import com.majazi.newsapplication.data.model.newslist.Data
import com.majazi.newsapplication.data.model.trendingnews.Post

sealed class ResourceListNews <T>(
    var data: List<Data> = emptyList(),
    val message: String? = null
) {

    class Success(data: List<Data>) : ResourceListNews<Data>(data = data)
    class Loading(data: List<Data> = emptyList()) : ResourceListNews<Data>(data = data)
    class Error(message: String, data: List<Data> = emptyList()) : ResourceListNews<Data>(data = data, message = message)
}