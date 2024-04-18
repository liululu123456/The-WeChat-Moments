package com.thoughtworks.wechat_final_assignment.viewModel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.thoughtworks.wechat_final_assignment.data.TweetDataSource
import com.thoughtworks.wechat_final_assignment.data.modal.Tweet
import com.thoughtworks.wechat_final_assignment.data.modal.UserInfo
import com.thoughtworks.wechat_final_assignment.data.repository.TweetRepository
import com.thoughtworks.wechat_final_assignment.data.repository.UserInfoRepository
import kotlinx.coroutines.flow.Flow

class MomentPageViewModel(private val tweetRepository: TweetRepository, private val userInfoRepository: UserInfoRepository)
    : ViewModel() {
        private val userInfoState = mutableStateListOf<UserInfo>()
        val userInfoLiveData: MutableLiveData<UserInfo> = MutableLiveData()

        val rankTweetItems: Flow<PagingData<Tweet>> =
            Pager(PagingConfig(pageSize =5, prefetchDistance =1))
            { TweetDataSource(tweetRepository) }.flow

        suspend fun fetchUserInfo(){
            val userInfo = userInfoRepository.fetchUserInfo().blockingFirst()
            userInfoLiveData.postValue(userInfo)
            userInfoState.clear()
            userInfoState.addAll(listOf(userInfo))
        }
    }