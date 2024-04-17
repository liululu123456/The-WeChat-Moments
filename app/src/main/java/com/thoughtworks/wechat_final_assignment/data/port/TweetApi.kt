package com.thoughtworks.wechat_final_assignment.data

import com.thoughtworks.wechat_final_assignment.data.Tweet.Tweet
import retrofit2.Response
import retrofit2.http.GET

interface TweetApi {
    @GET("tweets.json")
    suspend fun getTweets(): Response<List<Tweet>>
}