package com.thoughtworks.wechat_final_assignment

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.thoughtworks.wechat_final_assignment.data.modal.UserInfo
import com.thoughtworks.wechat_final_assignment.data.repository.TweetRepository
import com.thoughtworks.wechat_final_assignment.data.repositoryImpl.TweetRepositoryImpl
import com.thoughtworks.wechat_final_assignment.data.repositoryImpl.UserInfoRepositoryImpl

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
//        momentPageViewModel.fetchTweetsData()

    }
    userStates?.let { MomentBackgroundPart(it) }
}


@Composable
fun MomentBackgroundPart(
    user:UserInfo
){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(380.dp)
    ){
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
        ){
            AsyncImage(
                model = user.profileImage,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.End)
                .padding(top = 260.dp, end = 15.dp),
            contentAlignment = Alignment.BottomEnd
        ) {
            Row {
                Text(
                    text = user.nick,
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(end = 6.dp, top = 13.dp)
                )
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color.White)
                ) {
                    AsyncImage(
                        model = user.avatar,
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxSize()
                            .align(Alignment.BottomEnd),
                        contentScale = ContentScale.Crop
                    )
                }
        }
        }





    }

}