package com.example.global.network

import com.example.global.modules.app.model.forceupdate.ResponseForceUpdate
import com.example.global.modules.category.model.allCats.ResponseCategory
import com.example.global.modules.category.model.catPosts.ResponseCatPosts
import com.example.global.modules.category.model.like.ResponseLike
import com.example.global.modules.category.model.post.ResponsePost
import com.example.global.modules.category.model.sendFile.ResponseSendFile
import com.example.global.modules.category.model.sendParticipate.ResponseParticipation
import com.example.global.modules.category.model.singleIdea.ResponseSingleIdea
import com.example.global.modules.chat.model.sendMsg.ResponseSendMsg
import com.example.global.modules.home.model.ResponseSearchedPosts
import com.example.global.modules.login.model.IdentifyCode
import com.example.global.modules.login.model.LoginNumber
import com.example.global.modules.login.model.LoginOtp
import com.example.global.modules.message.chatMessage.ResponseChatMessage
import com.example.global.modules.message.chatRoom.ResponseChatRooms
import com.example.global.modules.message.supportMsg.ResponseSupportMsg
import com.example.global.modules.setting.model.*
import com.example.global.modules.shared.ResponseBigMain
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*

interface ApiService {

    interface App {

//        //ok
//        @FormUrlEncoded
//        @POST("addTokenFirebase")
//        suspend fun sendFCMToken(
//            @Header("Token") sessionToken: String,
//            @Field("mobile") mobile: String,
//            @Field("app_id") appId: String,
//            @Field("token") token:
//        ): FCMResponse


        @POST("checkForceUpdate")
        suspend fun forceUpdate(
            @Header("Accept") accept: String
        ): ResponseForceUpdate

    }

    interface Login {

        @FormUrlEncoded
        @POST("auth/authenticate")
        suspend fun sendNumber(
            @Header("Accept") accept: String,
            @Field("mobile") mobile: String
        ): LoginNumber


        @FormUrlEncoded
        @POST("auth/authenticate/login")
        suspend fun sendOtp(
            @Header("Accept") accept: String,
            @Field("mobile") mobile: String,
            @Field("otp_token") otp: String,
            @Field("iden_code") idenCode: String
        ): LoginOtp

        @FormUrlEncoded
        @POST("checkIdenCode")
        suspend fun checkIdentifyCode(
            @Header("Accept") accept: String,
            @Field("mobile") mobile: String,
            @Field("iden_code") idenCode: String
        ): IdentifyCode
    }

    interface Home {

        @POST("home/data")
        suspend fun getHomeList(
            @Header("Accept") accept: String
        ): ResponseBigMain


        @FormUrlEncoded
        @POST("post/getSearchedPost")
        suspend fun getSearchedPost(
            @Header("Accept") accept: String,
            @Field("character") mobile: String
        ): ResponseSearchedPosts

    }

    interface Messages {

        //for channels
        @POST("chat/getChatRooms")
        suspend fun getChatRooms(
            @Header("Accept") accept: String
        ): ResponseChatRooms

        @FormUrlEncoded
        @POST("chat/getChatMessages")
        suspend fun getChatMessages(
            @Header("Accept") accept: String,
            @Field("page") page: Int,
            @Field("per_page_number") perPageNumber: Int,
            @Field("chat_id") chatId: Int
        ): ResponseChatMessage

        //for support messages
        @FormUrlEncoded
        @POST("support/getSupportMsg")
        suspend fun getSupportMessages(
            @Header("Accept") accept: String,
            @Field("mobile") mobile: String
        ): ResponseSupportMsg

        //for support messages
        @POST("support/sendSupportMessage")
        @Multipart
        suspend fun sendSupportMessage(
            @Header("Accept") accept: String,
            @Part("mobile") mobile: RequestBody,
            @Part("text") text: RequestBody,
            @Part content: MultipartBody.Part,
        ): ResponseSendMsg
    }

    interface Category {

        @FormUrlEncoded
        @POST("category/getCategories")
        suspend fun getCategories(
            @Header("Accept") accept: String,
            @Field("min_age") minAge:Int?,
            @Field("max_age") maxAge:Int?,
            @Field("gender") gender: String?
        ): ResponseCategory

        @FormUrlEncoded
        @POST("category/getCategoryPosts")
        suspend fun getCategoryPosts(
            @Header("Accept") accept: String,
            @Field("category_id") CategoryId: Int,
            @Field("min_age") minAge: Int?,
            @Field("max_age") maxAge: Int?,
            @Field("gender") gender: Int?,
            @Field("sub_category") subCategory: String?
        ): ResponseCatPosts

        @FormUrlEncoded
        @POST("post/getSinglePost")
        suspend fun getSinglePost(
            @Header("Accept") accept: String,
            @Field("mobile") mobile: String,
            @Field("post_id") postId: Int
        ): ResponsePost

        @FormUrlEncoded
        @POST("post/getSingleIdea")
        suspend fun getSingleIdea(
            @Header("Accept") accept: String,
            @Field("idea_id") ideaId: Int
        ): ResponseSingleIdea

        @FormUrlEncoded
        @POST("post/like")
        suspend fun likePost(
            @Header("Accept") accept: String,
            @Field("mobile") mobile: String,
            @Field("dislike") dislike: Int,
            @Field("post_id") postId: Int
        ): ResponseLike


        @FormUrlEncoded
        @POST("post/participationData")
        suspend fun participationData(
            @Header("Accept") accept: String,
            @Field("mobile") mobile: String,
            @Field("post_id") postId: String,
            @Field("type") type: String,
            @Field("title") title: String,
            @Field("text") text: String
        ): ResponseParticipation

        @Multipart
        @POST("post/sendFileImage")
        suspend fun sendFileImage(
            @Header("Accept") accept: String,
            @Part attachments: MultipartBody.Part,
            @Part("idea_id") ideaId: RequestBody,
        ): ResponseSendFile

    }


    interface MySalehin {
        @FormUrlEncoded
        @POST("profile/getProfileData")
        suspend fun getProfileData2(
            @Header("Accept") accept: String,
            @Field("mobile") mobile: String
        ): ProfileUser


        @Multipart
        @POST("profile/updateImageProfile")
        suspend fun updateImageProfile(
            @Header("Accept") accept: String,
            @Part image: MultipartBody.Part,
            @Part("mobile") mobile: RequestBody
        ): UpdateImageProfile

        @FormUrlEncoded
        @POST("profile/editProfileData")
        suspend fun postDataProfile(
            @Header("Accept") accept: String,
            @Field("mobile") mobile: String,
            @Field("name") name: String,
            @Field("code_meli") nationalCode: String,
            @Field("birthday") birthday: String,
            @Field("gender") gender: String,
            @Field("ostan_id") provinceId: String,
            @Field("shahrestan_id") cityId: String,
            @Field("mahale") neighbourhoodId: String,
            @Field("masjed") mosque: String
        ): EditProfile
    }

    interface Setting {
        @FormUrlEncoded
        @POST("profile/getProfileData")
        suspend fun getProfileData(
            @Header("Accept") accept: String,
            @Field("mobile") mobile: String
        ): ProfileUser


        @POST("getOtherLinks")
        suspend fun getOtherLinks(
            @Header("Accept") accept: String
        ): OtherLink


        @FormUrlEncoded
        @POST("sendTicket")
        suspend fun sendTicket(
            @Header("Accept") accept: String,
            @Field("mobile") mobile: String,
            @Field("txt") message: String
        ): SendTicket


    }
}