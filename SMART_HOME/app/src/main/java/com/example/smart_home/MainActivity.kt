package com.example.smart_home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import com.example.smart_home.screens.screenAuth
import com.example.smart_home.screens.screenChooseDevice
import com.example.smart_home.screens.screenMain
import com.example.smart_home.screens.screenRegister

//data class DeviceState(
//    val camera: MutableList<Camera>,
//    val charger: MutableList<Charger>,
//    val kettle: MutableList<Kettle>,
//    val lamp: MutableList<Lamp>,
//    val musicColumn: MutableList<MusicColumn>,
//    val vacuumCleaner: MutableList<VacuumCleaner>
//)

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

//            var deviceState by remember {
//                mutableStateOf(
//                    DeviceState(
//                        camera = SnapshotStateList(),
//                        charger = SnapshotStateList(),
//                        kettle = SnapshotStateList(),
//                        lamp = SnapshotStateList(),
//                        musicColumn = SnapshotStateList(),
//                        vacuumCleaner = SnapshotStateList()
//                    )
//                )
//            }


            val deviceState = remember {
                mutableStateOf(SnapshotStateList<Any>())
            }
            val navController = rememberNavController()

            NavHost(
                navController = navController,
                startDestination = "screen_auth"
            ) {
                composable("screen_auth") {
                    screenAuth (
                        onClickLogin = { navController.navigate("screen_main") },
                        onClickRegister = { navController.navigate("screen_register") }
                    )
                }
                composable("screen_register") {
                    screenRegister {
                        navController.navigate("screen_main")
                    }
                }
                composable("screen_main") {
                    screenMain(
                        onClickExit = { navController.navigate("screen_auth") },
                        onClickChoose = {
                            navController.navigate("screen_chooseDevice")
                        },
                        deviceState = deviceState
                    )
                }
                composable("screen_chooseDevice") {
                    screenChooseDevice(
                        onIconClick = {
                            navController.navigate("screen_main")
                        },
                        deviceState = deviceState
                    )
                }
            }
        }
    }
}