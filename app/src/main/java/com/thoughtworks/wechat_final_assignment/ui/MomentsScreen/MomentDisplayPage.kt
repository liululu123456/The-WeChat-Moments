package com.thoughtworks.wechat_final_assignment.ui.MomentsScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
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
    LaunchedEffect(Unit) {
        momentPageViewModel.fetchUserInfo()

    }

    val momentsLazyPagingItems = momentPageViewModel.rankTweetItems.collectAsLazyPagingItems()
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
                items(momentsLazyPagingItems.itemCount) {
                    it.let {
                        momentsLazyPagingItems[it]?.let { item -> TweetItemView(item, LocalContext.current) }
                    }
                }
                momentsLazyPagingItems.apply {
                    when (loadState.append) {
                        is LoadState.Loading -> {
                            item {
                                Loading()
                            }
                        } else -> {
                    }
                    }
                }
            }
        MomentTitleBar(scrollState,statusBarHeight,systemUiController)
    }
}
@Composable
fun Loading() {
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(30.dp)
        .wrapContentWidth(Alignment.CenterHorizontally)
    ) {
        Row(
            modifier = Modifier.fillMaxHeight(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            CircularProgressIndicator(
                color = Color(0xFFCCCCCC),
                modifier = Modifier
                    .height(18.dp)
                    .width(18.dp),
                strokeWidth = 1.5.dp
            )
            Text(
                text = "loading...",
                fontSize = 13.sp,
                color = Color(0xFF888888),
                modifier = Modifier.padding(start = 8.dp)
            )
        }
    }
}