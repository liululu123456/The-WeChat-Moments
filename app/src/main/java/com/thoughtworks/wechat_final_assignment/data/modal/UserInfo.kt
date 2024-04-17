package com.thoughtworks.wechat_final_assignment.data.modal

import com.google.gson.annotations.SerializedName

data class UserInfo(
    @SerializedName("profile-image")
    val profileImage: String,
    val avatar: String,
    val nick: String,
    val username: String
)