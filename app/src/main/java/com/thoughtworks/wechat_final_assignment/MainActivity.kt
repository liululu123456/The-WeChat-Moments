package com.thoughtworks.wechat_final_assignment

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.thoughtworks.wechat_final_assignment.ui.theme.WechatfinalassignmentTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContent {
            WechatfinalassignmentTheme() {
                Surface(
                    contentColor = Color.LightGray,
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    NavigationPage()
                }
            }
        }
    }


    @Composable
    fun SetUpNavigation(navController: NavHostController){
        Scaffold {
                innerPadding ->
            NavHost(navController = navController ,
                startDestination = NavigationRoute.DISCOVER,
                modifier = Modifier.padding(innerPadding),
            ){
                composable(NavigationRoute.DISCOVER) {
                    NavigationPage()
                }
                composable(DiscoverNavRoute.MOMENTS) {
                    MomentDisplayPage()
                }

            }
        }
    }

}