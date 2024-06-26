package com.thoughtworks.wechat_final_assignment.ui.MainScreen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.thoughtworks.wechat_final_assignment.ui.emptyScreen.EmptyComingSoon
import com.thoughtworks.wechat_final_assignment.ui.MomentsScreen.MomentDisplayPage
import com.thoughtworks.wechat_final_assignment.R

@Composable
@Preview
fun MainScreen() {
    val navController = rememberNavController()
    val isBottomBarVisible = remember { mutableStateOf(true) }
    val selectedTab = remember { mutableStateOf(NavigationRoute.DISCOVER) }
    Scaffold(
        bottomBar = {
            if(isBottomBarVisible.value) {
                BottomNavigateWrapper(
                    selectedDestination = selectedTab.value,
                    onDrawerClicked = { route ->
                        selectedTab.value = route
                        navController.navigate(route)
                    }
                )
            }
        },
    ) {
        innerPadding ->
        NavigationHost(
            isBottomBarVisible = isBottomBarVisible,
            navController = navController,
            modifier = Modifier.padding(innerPadding),
        )
    }
}

@Composable
fun BottomNavigateWrapper(
    selectedDestination: String,
    onDrawerClicked: (String) -> Unit,
){
    NavigationBar(
        containerColor = Color.LightGray,
        tonalElevation = 0.dp,
    ) {
        NAVIGATION_ITEM_DESCRIPTION.forEachIndexed{ _, navigationItem ->
            val isSelected = selectedDestination == navigationItem.route
            val icon = if(isSelected) navigationItem.unselectedIcon else navigationItem.selectedIcon
            NavigationBarItem(
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = colorResource(id = R.color.Green)
                ),
                selected = selectedDestination == navigationItem.route,
                onClick = { onDrawerClicked(navigationItem.route) },
                icon = { Icon(icon, contentDescription = navigationItem.route) },
                label = { Text(text = navigationItem.route )}
            )
        }
    }
}

@Composable
private fun NavigationHost(
    isBottomBarVisible: MutableState<Boolean>,
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    val selectedItem = remember { mutableStateOf(NavigationRoute.DISCOVER) }

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = NavigationRoute.DISCOVER,
    ) {
        composable(NavigationRoute.CHATS) {
            EmptyComingSoon()
        }
        composable(NavigationRoute.CONTACTS) {
            EmptyComingSoon()
        }
        composable(NavigationRoute.DISCOVER) {
            DiscoverNavigationWrapper(
                selectedDestination = selectedItem.value,
                onDrawerClicked = { navController.navigate(DiscoverNavRoute.MOMENTS)}
            )
        }
        composable(NavigationRoute.ME) {
            EmptyComingSoon()
        }
        composable(DiscoverNavRoute.MOMENTS) {
            isBottomBarVisible.value = false
            MomentDisplayPage()
        }
    }
}




