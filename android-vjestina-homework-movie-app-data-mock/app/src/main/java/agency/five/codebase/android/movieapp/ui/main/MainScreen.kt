package agency.five.codebase.android.movieapp.ui.main

import agency.five.codebase.android.movieapp.navigation.MOVIE_ID_KEY
import agency.five.codebase.android.movieapp.navigation.MovieDetailsDestination
import agency.five.codebase.android.movieapp.navigation.NavigationItem
import agency.five.codebase.android.movieapp.ui.favourites.FavoritesRoute
import agency.five.codebase.android.movieapp.ui.home.HomeRoute
import agency.five.codebase.android.movieapp.ui.moviedetails.MovieDetailsRoute
import androidx.compose.foundation.Image
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import agency.five.codebase.android.movieapp.R
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraph.Companion.findStartDestination

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    val showBottomBar by remember {
        derivedStateOf {
            when (navBackStackEntry?.destination?.route) {
                MovieDetailsDestination.route -> false
                else -> true
            }
        }
    }

    val showBackIcon = !showBottomBar

    Scaffold(
        topBar = {
            TopBar(
                navigationIcon = {
                    if (showBackIcon) BackIcon(onBackClick = navController::popBackStack)
                }
            )
        },
        bottomBar = {
            if (showBottomBar)
                BottomNavigationBar(
                    destinations = listOf(
                        NavigationItem.HomeDestination,
                        NavigationItem.FavoritesDestination,
                    ),
                    onNavigateToDestination = {
                        navController.navigate(it.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    currentDestination = navBackStackEntry?.destination
                )
        }
    ) { padding ->
        Surface(
            color = MaterialTheme.colorScheme.background,
            modifier = Modifier.fillMaxSize()
        ) {
            NavHost(
                navController = navController,
                startDestination = NavigationItem.HomeDestination.route,
                modifier = Modifier.padding(padding)
            ) {
                composable(NavigationItem.HomeDestination.route) {
                    HomeRoute(
                        onNavigateToMovieDetails = {
                            navController.navigate(
                                MovieDetailsDestination.createNavigationRoute(it.id)
                            )
                        }
                    )
                }

                composable(NavigationItem.FavoritesDestination.route) {
                    FavoritesRoute(
                        onNavigateToMovieDetails = {
                            navController.navigate(
                                MovieDetailsDestination.createNavigationRoute(it.id)
                            )
                        }
                    )
                }

                composable(
                    route = MovieDetailsDestination.route,
                    arguments = listOf(navArgument(MOVIE_ID_KEY) { type = NavType.IntType }),
                ) {
                    MovieDetailsRoute()
                }
            }
        }
    }
}

@Composable
private fun TopBar(
    navigationIcon: @Composable (() -> Unit)? = null,
) {
    TopAppBar(
        backgroundColor = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.onPrimary,
        modifier = Modifier
            .wrapContentHeight()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            Image(
                painter = painterResource(id = R.drawable.tmdb_logo),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.Center)
            )

            if (navigationIcon != null) {
                navigationIcon()
            }
        }
    }
}

@Composable
private fun BackIcon(
    onBackClick: () -> Unit
) {
    Icon(
        modifier = Modifier
            .padding(8.dp)
            .clickable(onClick = onBackClick)
            .width(12.dp)
            .height(20.dp),
        painter = painterResource(id = R.drawable.back_icon),
        contentDescription = null
    )
}

@Composable
private fun BottomNavigationBar(
    destinations: List<NavigationItem>,
    onNavigateToDestination: (NavigationItem) -> Unit,
    currentDestination: NavDestination?,
) {
    BottomNavigation(
        backgroundColor = MaterialTheme.colorScheme.background
    ) {
        destinations.forEach { destination ->
            BottomNavigationItem(
                selected = currentDestination?.route == destination.route,
                icon = {
                    Icon(
                        painter = painterResource(
                            id = if (currentDestination?.route == destination.route)
                                destination.selectedIconId
                            else destination.unselectedIconId
                        ),
                        contentDescription = null
                    )
                },
                label = {
                    Text(
                        text = stringResource(id = destination.labelId),
                        fontSize = 10.sp,
                        color = Color.Blue
                    )
                },
                onClick = { onNavigateToDestination(destination) })
        }
    }
}