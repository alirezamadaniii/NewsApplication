package com.majazi.newsapplication.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.bumptech.glide.load.HttpException
import com.majazi.newsapplication.data.model.newslist.Data
import com.majazi.newsapplication.data.model.newslist.NewsList
import com.majazi.newsapplication.data.repository.news.datasource.NewsRemoteDataSource
import java.io.IOException

private const val TMDB_STARTING_PAGE_INDEX = 1
 class NewsPagingSource(
    private val remoteDataSource: NewsRemoteDataSource
) : PagingSource<Int, Data>()
 {
     override fun getRefreshKey(state: PagingState<Int, Data>): Int? {
         // We need to get the previous key (or next key if previous is null) of the page
         // that was closest to the most recently accessed index.
         // Anchor position is the most recently accessed index.
         return state.anchorPosition?.let { anchorPosition ->
             state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                 ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
         }
     }

     override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Data> {
         val pageIndex = params.key ?: TMDB_STARTING_PAGE_INDEX
         return try {
             val currentPage = params.key ?: 1
             val response = remoteDataSource.getNewsList(
                     "",
                 currentPage.toString(),
                 "10"
             )
             val movies = response.body()
             val nextKey =
                 if (movies?.data?.isEmpty() == true) {
                     null
                 } else {
                     // By default, initial load size = 3 * NETWORK PAGE SIZE
                     // ensure we're not requesting duplicating items at the 2nd request
                     pageIndex + (params.loadSize / 10)
                 }
             LoadResult.Page(
                 data = movies!!.data,
                 prevKey = if (pageIndex == TMDB_STARTING_PAGE_INDEX) null else pageIndex,
                 nextKey = nextKey
             )
         } catch (exception: IOException) {
             return LoadResult.Error(exception)
         } catch (exception: HttpException) {
             return LoadResult.Error(exception)
         }
     }


 }







