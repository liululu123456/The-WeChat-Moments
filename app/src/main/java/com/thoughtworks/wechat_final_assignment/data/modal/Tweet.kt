package com.thoughtworks.wechat_final_assignment.data.modal


data class Tweet(
    var content: String?,
    var date: String?,
    var images: List<Image>?,
    var sender: Sender?,
    var comments: List<Comment>?,
    var error: String?,
    var unknownError: String?
    )
data class Image ( val url: String)
data class Comment(
                   var content: String,
                   val sender: Sender
)
data class Sender (val userName: String,
                   val nick: String,
                   val avatar: String)