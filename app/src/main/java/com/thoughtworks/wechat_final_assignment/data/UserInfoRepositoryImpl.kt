package com.thoughtworks.wechat_final_assignment.data

import android.content.Context
import android.widget.Toast
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

class UserInfoRepositoryImpl(private val context: Context): UserInfoRepository {
    val BASE_URL = "https://xianmobilelab.gitlab.io/moments-data/"
    override suspend fun fetchUserInfo(): Flowable<UserInfo> = withContext(Dispatchers.IO) {
        var networkFlowable: Flowable<UserInfo>? = null
        try {
            val response = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(UserInfoApi::class.java)
                .fetchUserInfo()
                .body()
            networkFlowable = Flowable.just(response!!)
                .subscribeOn(Schedulers.io())
        } catch (e: IOException) {
            e.printStackTrace()
            handleNetworkError(e)
        }
        return@withContext networkFlowable!!
    }

    private suspend fun handleNetworkError(e: IOException) = withContext(Dispatchers.Main) {
        withContext(Dispatchers.Main) {
            println(e.message)
            Toast.makeText(context, "Error: ${e.message}", Toast.LENGTH_LONG).show()
        }
    }
}