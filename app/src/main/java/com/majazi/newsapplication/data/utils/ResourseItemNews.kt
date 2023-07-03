package com.majazi.newsapplication.data.utils

import com.majazi.newsapplication.data.model.homenews.ItemNews

sealed class ResourceItemNews<T>(
    var data: List<ItemNews> = emptyList(),
    val message: String? = null
) {
    class Success(data: List<ItemNews>) : ResourceItemNews<ItemNews>(data = data)
    class Loading(data: List<ItemNews> = emptyList()) : ResourceItemNews<ItemNews>(data = data)
    class Error(message: String, data: List<ItemNews> = emptyList()) : ResourceItemNews<ItemNews>(data = data, message = message)
}