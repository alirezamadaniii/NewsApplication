package com.example.global.data

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
import com.example.global.network.resource.Resource
import okhttp3.MultipartBody
import okhttp3.RequestBody

interface Repository {

    interface App {

        suspend fun forceUpdate(
        ): Resource<ResponseForceUpdate>
    }

    interface Login {
        suspend fun sendNumber(
            mobile: String
        ): Resource<LoginNumber>

        suspend fun sendOtp(
            mobile: String,
            otp: String,
            idenCode: String
        ): Resource<LoginOtp>

        suspend fun checkIdenCode(
            mobile: String,
            idenCode: String
        ): Resource<IdentifyCode>
    }


    interface Home {
        suspend fun fakeHome(
        ): Resource<ResponseBigMain>

        suspend fun getSearchedPost(
            text: String
        ): Resource<ResponseSearchedPosts>
    }

    interface Messages {
        suspend fun getChatRooms(
        ): Resource<ResponseChatRooms>

        suspend fun getChatMessages(
            page: Int,
            perPageNumber: Int,
            chatId: Int
        ): Resource<ResponseChatMessage>

        suspend fun getSupportMessages(
            mobile: String
        ): Resource<ResponseSupportMsg>

        suspend fun sendSupportMessage(
            mobile: String,
            text: String,
            content: MultipartBody.Part,
        ): Resource<ResponseSendMsg>
    }

    interface Category {
        suspend fun getCategories(
            minAge: Int?,
            maxAge: Int?,
            gender: String?
        ): Resource<ResponseCategory>

        suspend fun getCategoryPosts(
            categoryId: Int,
            minAge: Int?,
            maxAge: Int?,
            gender: Int?,
            subCategory: String?
        ): Resource<ResponseCatPosts>

        suspend fun getSinglePost(
            mobile: String,
            postId: Int
        ): Resource<ResponsePost>

        suspend fun getSingleIdea(
            ideaId: Int
        ): Resource<ResponseSingleIdea>

        suspend fun likePost(
            mobile: String,
            dislike: Int,
            postId: Int
        ): Resource<ResponseLike>

        suspend fun participationData(
            mobile: String,
            postId: String,
            type: String,
            title: String,
            text: String,
        ): Resource<ResponseParticipation>

        suspend fun sendFileImage(
            attachments: MultipartBody.Part,
            ideaId: RequestBody
        ): Resource<ResponseSendFile>
    }


    interface MySalehin {

        suspend fun getProfileData2(
            mobile: String
        ): Resource<ProfileUser>

        suspend fun updateImageProfile(
            image: MultipartBody.Part,
            mobile: RequestBody
        ): Resource<UpdateImageProfile>


        suspend fun postDataProfile(
            mobile: String,
            name: String,
            nationalCode: String,
            birthday: String,
            gender: String,
            provinceId: String,
            cityId: String,
            neighbourhoodId: String,
            mosque: String
        ): Resource<EditProfile>
    }

    interface Setting {
        suspend fun getProfileData(
            mobile: String
        ): Resource<ProfileUser>


        suspend fun getOtherLinks(
        ): Resource<OtherLink>

        suspend fun sendTicket(
            mobile: String,
            message: String
        ): Resource<SendTicket>

    }
}