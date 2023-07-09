package com.majazi.newsapplication.domien.usecase

import com.majazi.newsapplication.data.model.detailnews.comment.sendcomment.SendComment
import com.majazi.newsapplication.data.utils.Resource
import com.majazi.newsapplication.domien.repository.NewsRepository

class SendCommentUseCase(val repository: NewsRepository) {
    suspend fun execute(
        comment:String,
        postId:String,
        name:String,
        phone:String
    ):Resource<SendComment>{
        return repository.sendComment(comment, postId, name, phone)
    }
}