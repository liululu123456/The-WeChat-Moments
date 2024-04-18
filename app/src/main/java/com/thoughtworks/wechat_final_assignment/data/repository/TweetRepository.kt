package com.thoughtworks.wechat_final_assignment.data.repository

import com.thoughtworks.wechat_final_assignment.data.modal.Tweet
import io.reactivex.rxjava3.core.Flowable

interface TweetRepository {
    suspend fun fetchTweets(): Flowable<List<Tweet>>
    suspend fun getTweetPage(nextPage: Int, pageSize: Int): Flowable<List<Tweet>>

}