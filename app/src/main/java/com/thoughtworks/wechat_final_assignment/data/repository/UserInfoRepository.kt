package com.thoughtworks.wechat_final_assignment.data

import com.thoughtworks.wechat_final_assignment.data.modal.UserInfo
import io.reactivex.rxjava3.core.Flowable

interface UserInfoRepository {
    suspend fun fetchUserInfo():Flowable<UserInfo>
}