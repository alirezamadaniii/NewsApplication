package com.majazi.newsapplication.data.model.detailnews

data class DetailNews(
    val additional_contents: List<AdditionalContent>,
    val audio: List<Any>,
    val category: Category,
    val category_id: Int,
    val commentsCount: Int,
    val content: String,
    val created: String,
    val created_at: String,
    val id: Int,
    val image: ImageX,
    val image_id: Int,
    val language: String,
    val post_type: String,
    val related_posts: List<RelatedPost>,
    val related_topic: List<RelatedTopic>,
    val slug: String,
    val tags: List<String>,
    val title: String,
    val url: String,
    val user: UserX,
    val user_id: Int
)