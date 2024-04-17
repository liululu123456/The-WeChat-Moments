package com.thoughtworks.wechat_final_assignment.data

import retrofit2.Response
import retrofit2.http.GET

interface UserInfoApi {
    @GET("user.json")
    suspend fun fetchUserInfo(): Response<UserInfo>
}