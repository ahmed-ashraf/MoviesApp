package com.movies.hilt_mvvm_compose_movie.ui.screens.mainscreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.movies.hilt_mvvm_compose_movie.R
import com.movies.hilt_mvvm_compose_movie.navigation.Navigation
import com.movies.hilt_mvvm_compose_movie.navigation.Screen
import com.movies.hilt_mvvm_compose_movie.navigation.currentRoute
import com.movies.hilt_mvvm_compose_movie.navigation.navigationTitle
import com.movies.hilt_mvvm_compose_movie.ui.component.CircularIndeterminateProgressBar
import com.movies.hilt_mvvm_compose_movie.ui.component.SearchUI
import com.movies.hilt_mvvm_compose_movie.ui.component.appbar.AppBarWithArrow
import com.movies.hilt_mvvm_compose_movie.ui.component.appbar.HomeAppBar
import com.movies.hilt_mvvm_compose_movie.ui.component.appbar.SearchBar
import com.movies.hilt_mvvm_compose_movie.ui.theme.FloatingActionBackground
import com.movies.hilt_mvvm_compose_movie.utils.networkconnection.ConnectionState
import com.movies.hilt_mvvm_compose_movie.utils.networkconnection.connectivityState
import com.movies.hilt_mvvm_compose_movie.utils.pagingLoadingState
import kotlinx.coroutines.launch


@Composable
fun MainScreen() {
    val mainViewModel = hiltViewModel<MainViewModel>()
    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val isAppBarVisible = remember { mutableStateOf(true) }
    val searchProgressBar = remember { mutableStateOf(false) }
    val connection by connectivityState()
    val isConnected = connection === ConnectionState.Available



    Scaffold(scaffoldState = scaffoldState, topBar = {
        when (currentRoute(navController)) {
            Screen.Home.route -> {
                if (isAppBarVisible.value) {
                    val appTitle: String =
                        stringResource(R.string.app_title)
                    HomeAppBar(title = appTitle, openDrawer = {
                        scope.launch {
                            scaffoldState.drawerState.apply {
                                if (isClosed) open() else close()
                            }
                        }
                    }, openFilters = {
                        isAppBarVisible.value = false
                    })
                } else {
                    SearchBar(isAppBarVisible, mainViewModel)
                }
            }
            else -> {
                AppBarWithArrow(navigationTitle(navController)) {
                    navController.popBackStack()
                }
            }
        }
    }, floatingActionButton = {
        when (currentRoute(navController)) {
            Screen.Home.route -> {
                FloatingActionButton(
                    onClick = {
                        isAppBarVisible.value = false
                    }, backgroundColor = FloatingActionBackground
                ) {
                    Icon(Icons.Filled.Search, "", tint = Color.White)
                }
            }
        }
    }, snackbarHost = {
        if (isConnected.not()) {
            Snackbar(
                action = {}, modifier = Modifier.padding(8.dp)
            ) {
                Text(text = stringResource(R.string.there_is_no_internet))
            }
        }
    }) {
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            Navigation(navController, Modifier.padding(it))
            Column {
                CircularIndeterminateProgressBar(isDisplayed = searchProgressBar.value, 0.1f)
                if (isAppBarVisible.value.not()) {
                    SearchUI(navController, mainViewModel.searchData) {
                        isAppBarVisible.value = true
                    }
                }
            }
        }
        mainViewModel.searchData.pagingLoadingState {
            searchProgressBar.value = it
        }
    }
}
