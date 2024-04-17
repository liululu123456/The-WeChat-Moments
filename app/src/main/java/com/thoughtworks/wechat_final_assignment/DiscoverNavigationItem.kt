package com.thoughtworks.wechat_final_assignment

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp

@Composable
fun DiscoverNavigationItem(
    item: NavigationItem,
    selectedDestination: String,
    onDrawerClicked: (String) -> Unit,
    modifier: Modifier = Modifier
){
    NavigationDrawerItem(
        colors = NavigationDrawerItemDefaults.colors(
            selectedIconColor = colorResource(id = R.color.Green)
        ),
        selected = selectedDestination == item.route,
        onClick = { onDrawerClicked(item.route) },
        icon = { Icon(item.selectedIcon,
            contentDescription = item.route,
            modifier = Modifier.padding(horizontal = 8.dp),
        ) },
        label = {
            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.fillMaxWidth()) {
                Text(text = item.route,
                    modifier = Modifier.weight(1f)
                )
                Icon(
                    Icons.Default.ArrowForwardIos,
                    contentDescription = item.route,
                    modifier = Modifier
                        .width(16.dp)
                        .align(Alignment.CenterVertically)
                )
            }

        },
        shape = RectangleShape,
        modifier = Modifier.then(modifier)
    )
}