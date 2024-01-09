package com.majazi.newsapplication.peresentation.ui.listnews

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.majazi.newsapplication.data.model.newslist.Data
import com.majazi.newsapplication.domien.usecase.GetNewsListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class PassengerItemDataSource
    (private val getNewsUseCase: GetNewsListUseCase,
     private val catId:String,
     private val number:String,
     private val internet:Boolean
            ):PagingSource<Int,Data>() {

    private var requestContents: List<Data>? = null
    private var lastPageSize = 0

    override fun getRefreshKey(state: PagingState<Int, Data>): Int? {
        return state.anchorPosition?.let {
            val anchorPage = state.closestPageToPosition(it)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Data> {
        try {

            var prevKey: Int?
            var nextKey: Int?

            withContext(Dispatchers.IO) {
                val currentLoadingPage = params.key ?: 1

                val requestedResponse =
                    getNewsUseCase.execute(catId,internet, currentLoadingPage.toString(),number)

                requestedResponse.let { requestContents = it.data }

                lastPageSize = requestContents?.size ?: 0

                prevKey = if (currentLoadingPage == 1) null else currentLoadingPage - 1
                nextKey = if (lastPageSize <= 4) null else {
                    delay(1000)
                    currentLoadingPage + 1
                }

            }
            return LoadResult.Page(
                data = requestContents ?: emptyList(),
                prevKey = prevKey,
                nextKey = nextKey
            )

        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

}