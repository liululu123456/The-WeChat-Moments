package com.thoughtworks.wechat_final_assignment.data.repositoryImpl

import android.content.Context
import android.widget.Toast
import com.thoughtworks.wechat_final_assignment.data.modal.Tweet
import com.thoughtworks.wechat_final_assignment.data.port.TweetApi
import com.thoughtworks.wechat_final_assignment.data.repository.TweetRepository
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

class TweetRepositoryImpl() : TweetRepository {
    private val BASE_URL = "https://xianmobilelab.gitlab.io/moments-data/"
    override suspend fun fetchTweets(): Flowable<List<Tweet>> = withContext(Dispatchers.IO) {
        var networkFlowable: Flowable<List<Tweet>>? = null
        try {
            val response = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(TweetApi::class.java)
                .getTweets()
                .body()
            networkFlowable = Flowable.fromCallable { response!! }
                .subscribeOn(Schedulers.io())
        } catch (e: IOException) {
            e.printStackTrace()
//            handleNetworkError(e)
        }
        return@withContext networkFlowable!!
    }
//    private suspend fun handleNetworkError(e: IOException) = withContext(Dispatchers.Main) {
//        withContext(Dispatchers.Main) {
//            println(e.message)
//            Toast.makeText(context, "Error: ${e.message}", Toast.LENGTH_LONG).show()
//        }
//    }
}