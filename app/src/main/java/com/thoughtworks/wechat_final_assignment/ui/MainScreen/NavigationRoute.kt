package com.thoughtworks.wechat_final_assignment.ui.MainScreen

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AllInclusive
import androidx.compose.material.icons.filled.Camera
import androidx.compose.material.icons.filled.ChatBubble
import androidx.compose.material.icons.filled.Contacts
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.QrCodeScanner
import androidx.compose.material.icons.filled.RadioButtonChecked
import androidx.compose.material.icons.outlined.ChatBubbleOutline
import androidx.compose.material.icons.outlined.Contacts
import androidx.compose.material.icons.outlined.Explore
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.thoughtworks.wechat_final_assignment.R

object NavigationRoute {
    const val CHATS = "Chats"
    const val CONTACTS = "Contacts"
    const val DISCOVER = "Discover"
    const val ME = "Me"
}

object DiscoverNavRoute {
    const val MOMENTS = "Moments"
    const val CHANNELS = "Channels"
    const val LIVE = "Live"
    const val SCAN = "Scan"
    const val LISTEN = "Listen"
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
val MOMENTS_ITEM = NavigationItem(
    route = DiscoverNavRoute.MOMENTS,
    selectedIcon = Icons.Default.Camera,
    unselectedIcon= Icons.Default.Camera,
    iconTextId = R.string.tab_moments
)

val ENTERTAINMENT_ITEM = listOf(
    NavigationItem(
        route = DiscoverNavRoute.CHANNELS,
        selectedIcon = Icons.Default.AllInclusive,
        unselectedIcon= Icons.Default.AllInclusive,
        iconTextId = R.string.tab_channel
    ),
    NavigationItem(
        route = DiscoverNavRoute.LIVE,
        selectedIcon = Icons.Default.RadioButtonChecked,
        unselectedIcon = Icons.Default.RadioButtonChecked,
        iconTextId = R.string.tab_live
    )
)

val FUNCTION_ITEM = listOf(
    NavigationItem(
        route = DiscoverNavRoute.SCAN,
        selectedIcon = Icons.Default.QrCodeScanner,
        unselectedIcon= Icons.Default.QrCodeScanner,
        iconTextId = R.string.tab_scan
    ),
    NavigationItem(
        route = DiscoverNavRoute.LISTEN,
        selectedIcon = Icons.Default.MusicNote,
        unselectedIcon= Icons.Default.MusicNote,
        iconTextId = R.string.tab_listen
    )
)
