package com.majazi.newsapplication.data.db

import androidx.room.TypeConverter
import com.majazi.newsapplication.data.model.newslist.User

class Converters {

    @TypeConverter
    fun fromImage(image: com.majazi.newsapplication.data.model.newslist.Image):String{
        return image.ogImage
    }

    @TypeConverter
    fun toImage(name:String):com.majazi.newsapplication.data.model.newslist.Image{
        return com.majazi.newsapplication.data.model.newslist.Image(
            name,
            name,
            name,
            name,
            name,
            name,
            name,
            name,
            name
        )
    }

    @TypeConverter
    fun fromUser(user:User):String{
        return user.firstName
    }

    @TypeConverter
    fun toUser(name:String):User{
        return User(name,1,name)
    }
}