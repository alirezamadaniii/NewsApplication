package com.majazi.newsapplication.data.utils

import com.majazi.newsapplication.data.model.homenews.ItemNews

sealed class Resource2<T>(
    var data: List<ItemNews> = emptyList(),
    val message: String? = null
) {
    class Success(data: List<ItemNews>) : Resource2<ItemNews>(data = data)
    class Loading(data: List<ItemNews> = emptyList()) : Resource2<ItemNews>(data = data)
    class Error(message: String, data: List<ItemNews> = emptyList()) : Resource2<ItemNews>(data = data, message = message)
}