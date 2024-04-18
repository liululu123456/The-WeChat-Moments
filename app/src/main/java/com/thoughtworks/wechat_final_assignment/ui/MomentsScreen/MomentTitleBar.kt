package com.thoughtworks.wechat_final_assignment.ui.MomentsScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.systemuicontroller.SystemUiController

@Composable
fun MomentTitleBar(
    scrollState: LazyListState,
    statusBarHeight: Dp,
    systemUiController: SystemUiController
) {
    val target = LocalDensity.current.run { 250.dp.toPx() }
    val firstVisibleItemIndex = remember { derivedStateOf { scrollState.firstVisibleItemIndex } }
    val firstVisibleItemScrollOffset =
        remember { derivedStateOf { scrollState.firstVisibleItemScrollOffset } }
    val scrollPercent: Float =
        if (firstVisibleItemIndex.value > 0) 1f else (firstVisibleItemScrollOffset.value / target)
    val isTransparent = rememberSaveable { mutableStateOf(true) }

    if (scrollPercent > 0) {
        if (isTransparent.value) {
            systemUiController.setSystemBarsColor(
                color = Color(0xFFEDEDED),
                darkIcons = true,
            )
            isTransparent.value = false
        }
    } else {
        systemUiController.setSystemBarsColor(
            color = Color.Transparent,
            darkIcons = false,
        )
        isTransparent.value = true
    }

    val backgroundColor = Color(0xFFEDEDED)
    Column {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = statusBarHeight)
                .alpha(scrollPercent)
                .background(backgroundColor)
        )
        Box(modifier = Modifier.height(44.dp)) {
            Spacer(
                modifier = Modifier
                    .fillMaxSize()
                    .alpha(scrollPercent)
                    .background(backgroundColor)
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 15.dp)
                        .fillMaxHeight(),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBackIos,
                        contentDescription = null,
                        modifier = Modifier
                            .size(20.dp)
                            .align(Alignment.CenterStart),
                        tint = if (scrollPercent > 0) Color(0xff2E2E2E) else Color.White
                    )
                }
                Box(
                    modifier = Modifier
                        .weight(4f)
                        .fillMaxHeight(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Moments",
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.alpha(scrollPercent)
                    )
                }
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 15.dp)
                        .fillMaxHeight(),
                    contentAlignment = Alignment.CenterEnd
                ) {
                    Icon(
                        imageVector = Icons.Filled.PhotoCamera,
                        contentDescription = null,
                        modifier = Modifier
                            .size(20.dp)
                            .align(Alignment.CenterEnd),
                        tint = if (scrollPercent > 0) Color(0xff2E2E2E) else Color.White
                    )
                }
            }
        }
    }


}
