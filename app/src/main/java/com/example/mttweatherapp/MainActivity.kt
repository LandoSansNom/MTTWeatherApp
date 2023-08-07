package com.example.mttweatherapp

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mttweatherapp.ui.forcastscreen.ForecastScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import com.example.mttweatherapp.navigation.Drawer
import com.example.mttweatherapp.navigation.NavDrawerItem
import com.example.mttweatherapp.ui.CityListComponent
import com.example.mttweatherapp.ui.theme.MTTWeatherAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MTTWeatherAppTheme {
                // A surface container using the 'background' color from the theme

                    ForecastScreen()

            }
        }
    }
}

//@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
//@RequiresApi(Build.VERSION_CODES.O)
//@Composable
//fun MainScreen() {
//    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
//    val scope = rememberCoroutineScope()
//    val navController = rememberNavController()
//
//    Scaffold(
//        scaffoldState = scaffoldState,
//        topBar = { TopBar(scope = scope, scaffoldState = scaffoldState) },
//        drawerBackgroundColor = Color.White,
//        drawerContent = {
//            Drawer(
//                scope = scope,
//                scaffoldState = scaffoldState,
//                navController = navController
//            )
//        }
//    ) {
//        Navigation(navController = navController)
//    }
//}

@Composable
fun TopBar(
    scope: CoroutineScope,
    scaffoldState: ScaffoldState
) {
    TopAppBar(
        title = {
            Row(
                Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                ProvideTextStyle(value = MaterialTheme.typography.h6) {
                    CompositionLocalProvider(
                        LocalContentAlpha provides ContentAlpha.high,
                    ) {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(0.dp, 0.dp, 64.dp, 0.dp),
                            textAlign = TextAlign.Center,
                            maxLines = 1,
                            text = stringResource(R.string.current_location_string),
                            color = MaterialTheme.colors.primary
                        )
                    }
                }
            }
        },
        navigationIcon = {
            IconButton(onClick = {
                scope.launch {
                    scaffoldState.drawerState.open()
                }
            }) {
                Image(
                    painter = painterResource(id = R.drawable.ic_menu),
                    stringResource(R.string.description)
                )
            }
        },
        backgroundColor = Color(0, 0, 0, 0),
        contentColor = Color.Black,
        elevation = 0.dp,
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController, startDestination = NavDrawerItem.NextSevenDays.route) {
        composable(NavDrawerItem.NextSevenDays.route) {
            ForecastScreen()
        }
    }
}