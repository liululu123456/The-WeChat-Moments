package com.thoughtworks.wechat_final_assignment

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun EmptyComingSoon() {
    Text(text = "We will come soon~",
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray))
}