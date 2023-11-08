package com.yakogdan.mtsteta.presentation.ui.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun BottomNavBar() {
    var selectedItem by remember { mutableIntStateOf(0) }
    val items = listOf(NavigationItem.Main, NavigationItem.Favourite)

    NavigationBar {
        items.forEachIndexed { index, item ->
            NavigationBarItem(icon = {
                Icon(
                    item.icon,
                    contentDescription = stringResource(id = item.titleResId)
                )
            },
                label = { Text(text = stringResource(id = item.titleResId)) },
                selected = selectedItem == index,
                onClick = { selectedItem = index })
        }
    }
}