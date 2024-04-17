package com.thoughtworks.wechat_final_assignment.data.port

import com.thoughtworks.wechat_final_assignment.data.modal.UserInfo
import retrofit2.Response
import retrofit2.http.GET

interface UserInfoApi {
    @GET("user.json")
    suspend fun fetchUserInfo(): Response<UserInfo>
}