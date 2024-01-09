package com.majazi.newsapplication.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.majazi.newsapplication.data.model.Category
import com.majazi.newsapplication.data.model.DataSavedList
import com.majazi.newsapplication.data.model.detailnews.comment.SignInUser
import com.majazi.newsapplication.data.model.homenews.ItemNews
import com.majazi.newsapplication.data.model.newslist.Data
import com.majazi.newsapplication.data.model.trendingnews.Post
import kotlinx.coroutines.flow.Flow


@Dao
interface NewsDao {

    //News List
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveNews(data:List<Data>)
    @Query("DELETE FROM news_list")
    suspend fun deleteAllNews()
    @Query("SELECT * FROM news_list WHERE categoryId LIKE :categoryId")
     fun getNewsList(categoryId:String): List<Data>


     //Save page
     @Query("SELECT * FROM save_news_list")
     fun getSavedNews(): Flow<List<DataSavedList>>

     @Insert(onConflict = OnConflictStrategy.REPLACE)
     suspend fun saveNewsToSaved(data: DataSavedList)

    @Delete
    suspend fun deleteNews(data: DataSavedList)


    //Home Page Category
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveCategoryHomePage(itemNews: List<ItemNews>)

    @Query("SELECT * FROM category_list_home_page")
    fun getCategoryList(): List<ItemNews>

    @Query("DELETE FROM category_list_home_page")
    suspend fun deleteNewsList()


   //Trending News
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveTrendingNews(data: List<Post>)

    @Query("SELECT * FROM trending_news")
    fun getTradingNewsFromDb():List<Post>

    @Query("DELETE FROM trending_news")
    suspend fun deleteTradingNewsFromDb()


    //Uesr
    @Insert
    suspend fun signInUser(data: SignInUser)

    @Query("SELECT * FROM user")
    fun getUser():Flow<SignInUser>


    //Counter
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCounter(category: Category)

    @Query("SELECT count FROM category_table WHERE category_id LIKE :categoryId")
     fun getCounter(categoryId:Int):Flow<Int>

     @Query("SELECT * FROM category_table ORDER BY count DESC")
     fun getAllCounter():Flow<List<Category>>
}