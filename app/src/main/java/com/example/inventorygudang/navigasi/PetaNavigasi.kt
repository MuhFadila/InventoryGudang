package com.example.inventorygudang.navigasi

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.inventorygudang.R
import com.example.inventorygudang.login.AuthViewModel
import com.example.inventorygudang.login.DestinasiHalamanLogin
import com.example.inventorygudang.login.HalamanLogin
import com.example.inventorygudang.ui.halaman.DestinasiEntry
import com.example.inventorygudang.ui.halaman.DestinasiHalamanAwal
import com.example.inventorygudang.ui.halaman.DestinasiHome
import com.example.inventorygudang.ui.halaman.DetailsDestination
import com.example.inventorygudang.ui.halaman.DetailsScreen
import com.example.inventorygudang.ui.halaman.EntryBarangScreen
import com.example.inventorygudang.ui.halaman.HalamanAwal
import com.example.inventorygudang.ui.halaman.HomeScreen
import com.example.inventorygudang.ui.halaman.ItemEditDestination
import com.example.inventorygudang.ui.halaman.ItemEditScreen




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BarangTopAppBar(
    title: String,
    canNavigateBack: Boolean,
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior? = null,
    navigateUp: () -> Unit = {},
) {
    CenterAlignedTopAppBar(title = { Text(title) },
        modifier = modifier,
        scrollBehavior = scrollBehavior,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back)
                    )
                }
            }
        })
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HostNavigasi(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    authViewModel: AuthViewModel = viewModel()
) {
    NavHost(
        navController = navController,
        startDestination = DestinasiHalamanAwal.route,
        modifier = Modifier
    )
    {

        composable(DestinasiHalamanAwal.route) {
            HalamanAwal(
                onNextButtonClicked = {
                    navController.navigate(DestinasiHalamanLogin.route)
                })
        }

        composable(DestinasiHalamanLogin.route) {
            HalamanLogin(
                onLoginButtonClicked = { username, password ->
                    if (authViewModel.authenticate(username, password)) {
                        navController.navigate(DestinasiHome.route)
                    } else {

                    }
                }
            )}

        composable(DestinasiHome.route) {
            HomeScreen(
                navigateToItemEntry = { navController.navigate(DestinasiEntry.route) },
                onDetailClick = { itemId ->
                    navController.navigate("${DetailsDestination.route}/$itemId")
                },
            )
        }
        composable(DestinasiEntry.route) {
            EntryBarangScreen(navigateBack = { navController.popBackStack() })
        }

        composable(
            DetailsDestination.routeWithArgs,
            arguments = listOf(navArgument(DetailsDestination.barangIdArg) {
                type = NavType.IntType
            })
        ) { backStackEntry ->
            val itemId = backStackEntry.arguments?.getInt(DetailsDestination.barangIdArg)
            itemId?.let {
                DetailsScreen(
                    navigateBack = { navController.popBackStack() },
                    navigateToEditItem = { navController.navigate("${ItemEditDestination.route}/$it") }
                )
            }
        }

        composable(
            ItemEditDestination.routeWithArgs,
            arguments = listOf(navArgument(ItemEditDestination.itemIdArg) {
                type = NavType.IntType
            })
        ) {
            ItemEditScreen(navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() })
        }


    }
}