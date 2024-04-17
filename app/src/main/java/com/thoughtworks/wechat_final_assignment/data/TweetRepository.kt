package com.thoughtworks.wechat_final_assignment.data

import io.reactivex.rxjava3.core.Flowable

interface TweetRepository {
    suspend fun fetchTweets(): Flowable<List<Tweet>>

}