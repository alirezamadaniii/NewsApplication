package com.majazi.newsapplication.domien.usecase

import com.majazi.newsapplication.data.model.detailnews.comment.Comment
import com.majazi.newsapplication.data.utils.Resource
import com.majazi.newsapplication.data.utils.Resource2
import com.majazi.newsapplication.domien.repository.NewsRepository

class GetCommentUseCase(private val newsRepository: NewsRepository) {
    suspend fun execute(id:String): Resource<Comment> {
        return newsRepository.getComment(id)
    }
}