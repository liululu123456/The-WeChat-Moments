package com.thoughtworks.wechat_final_assignment

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.jetbrains.annotations.Async

@Composable
fun MomentDisplayPage(){
    MomentBackgroundPart()
}

@Preview
@Composable
fun MomentBackgroundPart(){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(380.dp)
            .background(Color.Green)
    ){}

}