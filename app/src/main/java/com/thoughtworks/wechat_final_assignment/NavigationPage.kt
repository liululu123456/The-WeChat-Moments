package com.thoughtworks.wechat_final_assignment

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun NavigationPage() {
    val navController = rememberNavController()
    val selectedTab = remember { mutableStateOf(NavigationRoute.DISCOVER) }
    Scaffold(
        bottomBar = {
            BottomNavigateWrapper(
                selectedDestination = selectedTab.value,
                onDrawerClicked = {
                        route ->
                    selectedTab.value = route
                    navController.navigate(route)
                }
            )
        },
    ) {
            innerPadding ->
        NavigationHost(
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
            NavigationContent(selectedItem.value,{})
        }
        composable(NavigationRoute.ME) {
            EmptyComingSoon()
        }
    }
}


@Composable
private fun NavigationContent(
    selectedDestination: String,
    onDrawerClicked: (String) -> Unit,
){
    Column(modifier = Modifier
        .background(Color.LightGray)
        .fillMaxHeight()) {
        NavigationDrawerItem(
            colors = NavigationDrawerItemDefaults.colors(
                selectedIconColor = colorResource(id = R.color.Green)
            ),
            selected = selectedDestination == MOMENTS_ITEM.route,
            onClick = { onDrawerClicked(MOMENTS_ITEM.route) },
            icon = { Icon(MOMENTS_ITEM.selectedIcon,
                contentDescription = MOMENTS_ITEM.route,
                modifier = Modifier.padding(horizontal = 8.dp)
            ) },
            label = { Text(text = MOMENTS_ITEM.route )},
            modifier = Modifier.padding(vertical = 5.dp),
            shape = RectangleShape
        )
        Column(
            modifier = Modifier.padding(vertical = 5.dp),
            ) {
            ENTERTAINMENT_ITEM.forEachIndexed{ _, item ->
                NavigationDrawerItem(
                    shape = RectangleShape,
                    colors = NavigationDrawerItemDefaults.colors(
                        selectedIconColor = colorResource(id = R.color.Green),
                    ),
                    selected = selectedDestination == item.route,
                    onClick = { onDrawerClicked(item.route) },
                    icon = { Icon(item.selectedIcon,
                        contentDescription = item.route,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    ) },
                    label = { Text(text = item.route )},
                )
                Divider(color = Color.LightGray)
            }
        }
        Column(
            modifier = Modifier.padding(vertical = 5.dp),
            ) {
            FUNCTION_ITEM.forEachIndexed{ _, item ->
                NavigationDrawerItem(
                    shape = RectangleShape,
                    colors = NavigationDrawerItemDefaults.colors(
                        selectedIconColor = colorResource(id = R.color.Green)
                    ),
                    selected = selectedDestination == item.route,
                    onClick = { onDrawerClicked(item.route) },
                    icon = { Icon(item.selectedIcon,
                        contentDescription = item.route,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    ) },
                    label = { Text(text = item.route )},
                )
                Divider(color = Color.LightGray)
            }
        }
    }
}
