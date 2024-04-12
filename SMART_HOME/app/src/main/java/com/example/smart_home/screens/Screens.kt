package com.example.smart_home.screens

import DialogAddDevice
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smart_home.R
import com.example.smart_home.ui.theme.Custom
import data.Camera
import data.Charger
import data.Device
import data.Kettle
import data.Lamp
import data.MusicColumn
import data.VacuumCleaner
import deviceCamera
import deviceCharger
import deviceKettle
import deviceLamp
import deviceMusicColumn
import deviceVacuumCleaner

@Composable
fun screenMain(
    onClickExit: () -> Unit,
    onClickChoose: () -> Unit,
    deviceState: MutableState<SnapshotStateList<Any>>
) {
    Image(
        painter = painterResource(id = R.drawable.bg),
        contentDescription = "bg",
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.FillBounds
    )
    Column() {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp, start = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Умный дом",
                style = TextStyle(fontSize = 30.sp),
                color = Color.White
            )
            Row() {
                IconButton(onClick = {
                    onClickExit.invoke()
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_logout),
                        contentDescription = "logout",
                        tint = Color.White
                    )
                }

                IconButton(onClick = {
                    onClickChoose()
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_add),
                        contentDescription = "add button",
                        tint = Color.White
                    )
                }
            }
        }

        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
            deviceState.value.forEach { item ->
                when (item) {
                    is Lamp -> {
                        deviceLamp(item, deviceState)
                    }
                    is MusicColumn -> {
                        deviceMusicColumn(item, deviceState)
                    }
                    is Kettle -> {
                        deviceKettle(item, deviceState)
                    }
                    is Camera -> {
                        deviceCamera(item, deviceState)
                    }
                    is Charger -> {
                        deviceCharger(item, deviceState)
                    }
                    is VacuumCleaner -> {
                        deviceVacuumCleaner(item, deviceState)
                    }
                }
            }
        }
    }
}


@Composable
fun screenAuth(onClickLogin: () -> Unit, onClickRegister: () -> Unit) {
    val usernameState = remember { mutableStateOf("") }
    val passwordState = remember { mutableStateOf("") }
    Image(
        painter = painterResource(id = R.drawable.bg),
        contentDescription = "bg",
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.FillBounds
    )
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.padding(16.dp)
            .fillMaxSize()
    ) {
        Text(
            "Войти в аккаунт",
            style = TextStyle(fontSize = 20.sp),
            color = Color.White
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = usernameState.value,
            onValueChange = { usernameState.value = it },
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            label = { Text("Пользователь") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            maxLines = 1
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = passwordState.value,
            onValueChange = { passwordState.value = it },
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Password
            ),
            visualTransformation = PasswordVisualTransformation(),
            label = { Text("Пароль") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            maxLines = 1
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                onClickLogin()
            }
        ) {
            Text("Войти")
        }
        Spacer(modifier = Modifier.height(16.dp))
        TextButton(
            onClick = { onClickRegister() }
        ) {
            Text("Создать аккаунт")
        }
    }
}

@Composable
fun screenRegister(onClick: () -> Unit) {
    val usernameState = remember { mutableStateOf("") }
    val passwordState = remember { mutableStateOf("") }
    Image(
        painter = painterResource(id = R.drawable.bg),
        contentDescription = "bg",
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.FillBounds
    )
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.padding(16.dp)
            .fillMaxSize()
    ) {
        Text(
            "Регистрация",
            style = TextStyle(fontSize = 20.sp),
            color = Color.White
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = usernameState.value,
            onValueChange = { usernameState.value = it },
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text
            ),
            label = { Text("Пользователь") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            maxLines = 1
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = passwordState.value,
            onValueChange = { passwordState.value = it },
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Password
            ),
            visualTransformation = PasswordVisualTransformation(),
            label = { Text("Пароль") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            maxLines = 1
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                onClick()
            }
        ) {
            Text("Регистрация")
        }
    }
}

@Composable
fun screenChooseDevice(
    gridSize: Int = 3,
    onIconClick: () -> Unit,
    deviceState: MutableState<SnapshotStateList<Any>>
) {
    Image(
        painter = painterResource(id = R.drawable.bg),
        contentDescription = "bg",
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.FillBounds
    )
    val dialogState = remember {
        mutableStateOf(false)
    }
    val nameState = remember {
        mutableStateOf("")
    }
    if (dialogState.value) {
        DialogAddDevice(dialogState, nameState, onIconClick, deviceState)
    }

    val icons = listOf(
        Device("Лампочка", painterResource(id = R.drawable.lamp)),
        Device("Чайник", painterResource(id = R.drawable.kettle)),
        Device("Колонка", painterResource(id = R.drawable.column)),
        Device("Камера", painterResource(id = R.drawable.camera)),
        Device("Розетка", painterResource(id = R.drawable.charger)),
        Device("Пылесос", painterResource(id = R.drawable.cleaner)),
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Добавить новое устройство",
            color = Color.White
        )
        for (row in 0 until gridSize) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                for (col in 0 until gridSize) {
                    val index = row * gridSize + col
                    val icon = icons.getOrNull(index)
                    if (icon != null) {
                        Column(
                            modifier = Modifier
                                .clickable(onClick = {
                                    dialogState.value = true
                                    nameState.value = icon.title
                                })
                                .background(Custom, shape = RoundedCornerShape(10.dp))
                                .size(110.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                painter = icon.painter,
                                contentDescription = null,
                                Modifier.size(90.dp)
                            )
                            Text(
                                text = icon.title,
                                color = Color.White
                            )
                        }
                    }
                }
            }
        }
    }
}
