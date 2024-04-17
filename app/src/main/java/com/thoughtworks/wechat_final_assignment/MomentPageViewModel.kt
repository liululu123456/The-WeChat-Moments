package com.thoughtworks.wechat_final_assignment

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.thoughtworks.wechat_final_assignment.data.modal.Tweet
import com.thoughtworks.wechat_final_assignment.data.modal.UserInfo
import com.thoughtworks.wechat_final_assignment.data.repository.TweetRepository
import com.thoughtworks.wechat_final_assignment.data.repository.UserInfoRepository
import kotlinx.coroutines.flow.MutableStateFlow

class MomentPageViewModel(private val tweetRepository: TweetRepository, private val userInfoRepository: UserInfoRepository)
    : ViewModel() {
    private val tweetStates = mutableStateListOf<Tweet>()
    val tweetsLiveData: MutableLiveData<List<Tweet>> = MutableLiveData()

    private val userInfoState = mutableStateListOf<UserInfo>()
    val userInfoLiveData: MutableLiveData<UserInfo> = MutableLiveData()

    suspend fun fetchUserInfo(){
        val userInfo = userInfoRepository.fetchUserInfo().blockingFirst()
        userInfoLiveData.postValue(userInfo)
        userInfoState.clear()
        userInfoState.addAll(listOf(userInfo))
    }
    suspend fun fetchTweetsData(){
        val tweets = tweetRepository.fetchTweets().blockingFirst()
            .sortedByDescending { it.date}
            .filter { it.content != null }
        tweetsLiveData.postValue(tweets)
        tweetStates.clear()
        tweetStates.addAll(tweets)
    }
}