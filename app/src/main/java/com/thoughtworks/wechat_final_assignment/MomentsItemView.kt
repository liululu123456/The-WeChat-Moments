package com.thoughtworks.wechat_final_assignment

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.thoughtworks.wechat_final_assignment.data.modal.Tweet

@Composable
fun MomentsItemView(it: Tweet, context: Context) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp)
    ){
        Row {
            Box(modifier = Modifier
                .padding(top = 4.dp, end = 6.dp)
                .width(45.dp)
                .height(45.dp)) {
                AsyncImage(
                    model = it.sender?.avatar,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(6.dp))
                )
            }
            TweetContentView(item = it, context = context)
        }
    }
}
@Composable
fun TweetContentView(item: Tweet, context: Context){
    val defaultHeight = 100.dp
    var height: Dp = defaultHeight
    val size = item.images?.size
    if (size != null) {
        if (size > 6) {
            height =  defaultHeight * 3
        } else if(size in 4..6) {
            height =  defaultHeight * 2
        }
    }

    Column {
        item.sender?.let { it1 ->
            Text(
                text = it1.nick,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 6.dp, bottom = 8.dp),
                fontSize = 16.sp,
                color = Color(0xff61698e)
            )
        }

        item.content?.let { it1 ->
            Text(
                text = it1,
                modifier = Modifier.padding(start = 6.dp, bottom = 8.dp),
                fontSize = 16.sp,
                color = Color(0xff1e1e1e)
            )
        }

        if (item.images != null && size != null) {
            TweetImageView(item = item, height = height)
        }

        Row(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "15 minutes ago",
                modifier = Modifier
                    .padding(start = 6.dp)
                    .weight(1f),
                fontSize = 12.sp,
                color = Color(0xff1e1e1e),
                textAlign = TextAlign.Start
            )
            Box(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 4.dp, bottom = 8.dp),
                contentAlignment = Alignment.CenterEnd
            ) {
                Box(
                    modifier = Modifier
                        .width(30.dp)
                        .height(20.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .background(Color(0xFFEDEDED)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Filled.MoreHoriz,
                        contentDescription = null,
                        modifier = Modifier.size(25.dp),
                        tint = Color(0xff000000)
                    )
                }
            }
        }

        if (item.comments != null && item.comments?.isNotEmpty() == true) {
            TweetCommentsView(item)
        }

    }
}
@Composable
fun TweetImageView(item: Tweet, height:Dp){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(height)
            .padding(start = 10.dp, top = 0.dp, bottom = 8.dp)
    )
    {
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            userScrollEnabled = false,
            content = {
                itemsIndexed(item.images!!) { _, image ->
                    Box(
                        modifier = Modifier
                            .height(100.dp)
                            .fillMaxWidth()

                    ) {
                        AsyncImage(
                            model = image.url,
                            modifier = Modifier
                                .fillMaxSize(),
                            contentDescription = null,
                            contentScale = ContentScale.FillBounds
                        )
                    }
                }
            }
        )
    }
}
@Composable
fun TweetCommentsView(item: Tweet){
    Box(modifier = Modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(4.dp))
        .background(Color(0xFFEDEDED))
    ) {
        Column {
            item.comments!!.forEach { comment ->
                Row {
                    Text(
                        text = comment.sender.nick+":",
                        modifier = Modifier
                            .padding(6.dp),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xff61698e),
                        textAlign = TextAlign.Start
                    )
                    Text(
                        text = comment.content,
                        modifier = Modifier
                            .padding(6.dp),
                        fontSize = 14.sp,
                        color = Color(0xff1e1e1e),
                    )
                }
            }
        }
    }
}