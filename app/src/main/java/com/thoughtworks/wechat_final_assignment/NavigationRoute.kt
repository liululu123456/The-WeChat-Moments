package com.thoughtworks.wechat_final_assignment

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChatBubble
import androidx.compose.material.icons.filled.ChatBubbleOutline
import androidx.compose.material.icons.filled.Contacts
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.ChatBubbleOutline
import androidx.compose.material.icons.outlined.Contacts
import androidx.compose.material.icons.outlined.Explore
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector

object NavigationRoute {
    const val CHATS = "Chats"
    const val CONTACTS = "Contacts"
    const val DISCOVER = "Discover"
    const val ME = "Me"
}

data class NavigationItem(
    val route: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val iconTextId: Int
)

val NAVIGATION_ITEM_DESCRIPTION = listOf(
    NavigationItem(
        route = NavigationRoute.CHATS,
        selectedIcon = Icons.Outlined.ChatBubbleOutline,
        unselectedIcon= Icons.Filled.ChatBubble,
        iconTextId = R.string.tab_chats
    ),
    NavigationItem(
        route = NavigationRoute.CONTACTS,
        selectedIcon = Icons.Outlined.Contacts,
        unselectedIcon= Icons.Filled.Contacts,
        iconTextId = R.string.tab_contacts
    ),
    NavigationItem(
        route = NavigationRoute.DISCOVER,
        selectedIcon = Icons.Outlined.Explore,
        unselectedIcon = Icons.Filled.Explore,
        iconTextId = R.string.tab_discover
    ),
    NavigationItem(
        route = NavigationRoute.ME,
        selectedIcon = Icons.Outlined.Person,
        unselectedIcon= Icons.Filled.Person,
        iconTextId = R.string.tab_me
    )
)
