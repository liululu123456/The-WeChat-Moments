package com.thoughtworks.wechat_final_assignment.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.thoughtworks.wechat_final_assignment.data.modal.Tweet
import com.thoughtworks.wechat_final_assignment.data.repository.TweetRepository
import kotlinx.coroutines.delay

class TweetDataSource(private val repository: TweetRepository): PagingSource<Int, Tweet>() {
    override fun getRefreshKey(state: PagingState<Int, Tweet>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Tweet> {
        return try {
            val nextPage = params.key ?: 1
            val pageSize = 5

            val tweets = repository.getTweetPage(nextPage, pageSize)

            if (nextPage > 1) {
                delay(2000)
            }
            LoadResult.Page(
                data = tweets.blockingFirst(),
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = nextPage + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

}