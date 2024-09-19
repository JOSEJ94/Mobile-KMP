package org.example.project

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Apps
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import moe.tlaster.precompose.PreComposeApp
import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.navigation.path
import moe.tlaster.precompose.navigation.rememberNavigator
import org.example.project.data.TitleTopBarTypes
import org.example.project.navigation.Navigation

@Composable
fun App() {
    PreComposeApp {
        val colors = getColorsTheme()
        val navigator = rememberNavigator()
        val titleTopBar = getTitleTopAppBar(navigator)
        val isEditOrAddExpense = titleTopBar != TitleTopBarTypes.DASHBOARD.value

        AppTheme {
            Scaffold(
                modifier = Modifier.fillMaxSize(), topBar = {
                    TopAppBar(elevation = 0.dp, title = {
                        Text(
                            text = titleTopBar,
                            fontSize = 25.sp,
                            color = colors.textColor
                        )
                    }, navigationIcon = {
                        if (isEditOrAddExpense) {
                            IconButton(onClick = {
                                navigator.popBackStack()
                            }) {
                                Icon(
                                    modifier = Modifier.padding(start = 16.dp),
                                    imageVector = Icons.Default.ArrowBack,
                                    tint = colors.textColor,
                                    contentDescription = "Flecha back"
                                )
                            }
                        } else {
                            Icon(
                                modifier = Modifier.padding(start = 16.dp),
                                imageVector = Icons.Default.Apps,
                                tint = colors.textColor,
                                contentDescription = "Dashboard icon"
                            )
                        }
                    }, backgroundColor = colors.backgroundColor)
                },
                floatingActionButton = {
                    if (!isEditOrAddExpense) {
                        FloatingActionButton(
                            modifier = Modifier.padding(8.dp),
                            onClick = {
                                navigator.navigate("/addExpenses")
                            },
                            shape = RoundedCornerShape(50),
                            backgroundColor = colors.addIconColor,
                            contentColor = Color.White
                        ) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                tint = Color.White,
                                contentDescription = "Floating Action Button"
                            )
                        }
                    }
                }) {
                Navigation(navigator)
            }
        }
    }
}

@Composable
fun getTitleTopAppBar(navigator: Navigator): String {
    var titleTopBar = TitleTopBarTypes.DASHBOARD
    val isOnAddExpenses =
        navigator.currentEntry.collectAsState(null).value?.route?.route.equals("/addExpenses/{id}?")
    if (isOnAddExpenses) {
        titleTopBar = TitleTopBarTypes.ADD
    }

    val isOnEditExpenses =
        navigator.currentEntry.collectAsState(null).value?.path<Long>("id")
    isOnEditExpenses?.let {
        titleTopBar = TitleTopBarTypes.EDIT
    }
    return titleTopBar.value
}