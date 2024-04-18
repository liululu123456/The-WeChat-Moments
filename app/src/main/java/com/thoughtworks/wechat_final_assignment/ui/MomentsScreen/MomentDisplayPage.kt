package com.thoughtworks.wechat_final_assignment.ui.MomentsScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.thoughtworks.wechat_final_assignment.data.repositoryImpl.TweetRepositoryImpl
import com.thoughtworks.wechat_final_assignment.data.repositoryImpl.UserInfoRepositoryImpl
import com.thoughtworks.wechat_final_assignment.viewModel.MomentPageViewModel

@Preview
@Composable
fun MomentDisplayPage(){
    val tweetRepositoryImpl: TweetRepositoryImpl = TweetRepositoryImpl()
    val userInfoRepositoryImpl: UserInfoRepositoryImpl = UserInfoRepositoryImpl()
    val momentPageViewModel = MomentPageViewModel(tweetRepositoryImpl, userInfoRepositoryImpl)
   val userStates by momentPageViewModel.userInfoLiveData.observeAsState()
    val tweets by momentPageViewModel.tweetsLiveData.observeAsState()
    LaunchedEffect(Unit) {
        momentPageViewModel.fetchUserInfo()
        momentPageViewModel.fetchTweetsData()

    }

    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setSystemBarsColor(
            color = Color.Transparent,
            darkIcons = false,
        )
    }
    val statusBarHeight = LocalDensity.current.run {
        WindowInsets.statusBars.getTop(this).toDp()
    }
    val scrollState = rememberLazyListState()

    Box{
        LazyColumn(state = scrollState)
            {
                item { userStates?.let { MomentBackgroundPart(it) } }
                tweets?.let {
                    items(it.size) { i ->
                        TweetItemView(it[i],LocalContext.current)
                    }
                }
            }
        MomentTitleBar(scrollState,statusBarHeight,systemUiController)
    }

}

