import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.smart_home.R
import com.example.smart_home.ui.theme.Custom
import com.example.smart_home.ui.theme.DarkGray
import data.Camera
import data.Charger
import data.Kettle
import data.Lamp
import data.MusicColumn
import data.VacuumCleaner
import java.util.Calendar


@Composable
fun deviceLamp(lamp: Lamp, deviceState: MutableState<SnapshotStateList<Any>>) {
    val checkedState = remember { mutableStateOf(false) }
    Card(
        modifier = Modifier.padding(100.dp, 10.dp),
        shape = RoundedCornerShape(10.dp)
    )
    {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Custom),
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End){
                IconButton(
                    onClick = {
                        deviceState.value.remove(lamp)
                    }) {
                    Icon(painter = painterResource(id = R.drawable.baseline_delete),
                        contentDescription = "delete",
                        tint = Color.Red
                    )
                }
            }
            Image(
                painter = painterResource(id = R.drawable.lamp),
                contentDescription = "lamp"
            )
            Text(
                text = lamp.title,
                color = Color.White
            )
            Text(
                text = lamp.number,
                color = Color.White
            )
            Text(
                text = lamp.location,
                color = Color.Gray
            )
            Switch(
                checked = checkedState.value,
                onCheckedChange = { checkedState.value = it }
            )
        }
    }
}

@Composable
fun deviceMusicColumn(musicColumn: MusicColumn, deviceState: MutableState<SnapshotStateList<Any>>) {
    var sliderPosition by remember { mutableStateOf(musicColumn.volume.toFloat()) }
    Card(
        modifier = Modifier.padding(100.dp, 10.dp),
        shape = RoundedCornerShape(10.dp)
    )
    {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Custom),
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End){
                IconButton(
                    onClick = {
                        deviceState.value.remove(musicColumn)
                    }) {
                    Icon(painter = painterResource(id = R.drawable.baseline_delete),
                        contentDescription = "delete",
                        tint = Color.Red
                    )
                }
            }
            Image(
                painter = painterResource(id = R.drawable.column),
                contentDescription = "column"
            )
            Text(
                text = musicColumn.title,
                color = Color.White
            )
            Text(
                text = musicColumn.location,
                color = Color.Gray
            )
            Text(text = "Громкость: ${sliderPosition.toInt()}", color = Color.White)
            Slider(
                value = sliderPosition,
                valueRange = 0f..10f,
                steps = 9,
                onValueChange = { sliderPosition = it },
                colors = SliderDefaults.colors(
                    thumbColor = Color(0xFF4A1CB7),
                    activeTrackColor = Color(0xFFEF9A9A),
                    inactiveTrackColor = Color(0xFFFFEBEE),
                    inactiveTickColor = Color(0xFFEF9A9A),
                    activeTickColor = DarkGray
                )
            )
        }
    }
}


@Composable
fun deviceKettle(kettle: Kettle, deviceState: MutableState<SnapshotStateList<Any>>) {
    val checkedState = remember { mutableStateOf(false) }
    val checkedStateButton = remember {
        mutableStateOf(false)
    }
    var targetTemperature by remember { mutableStateOf(kettle.temperature.toFloat()) }
    Card(
        modifier = Modifier.padding(100.dp, 10.dp),
        shape = RoundedCornerShape(10.dp)
    )
    {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Custom),
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End){
                IconButton(
                    onClick = {
                        deviceState.value.remove(kettle)
                    }) {
                    Icon(painter = painterResource(id = R.drawable.baseline_delete),
                        contentDescription = "delete",
                        tint = Color.Red
                    )
                }
            }
            Image(
                painter = painterResource(id = R.drawable.kettle),
                contentDescription = "kettle"
            )
            Text(
                text = kettle.title,
                color = Color.White
            )
            Text(
                text = kettle.location,
                color = Color.Gray
            )
            Text(text = "Нужная температура: ${targetTemperature.toInt()}°C", color = Color.White)
            Slider(
                value = targetTemperature,
                valueRange = 0f..100f,
                steps = 99,
                onValueChange = { targetTemperature = it },
                colors = SliderDefaults.colors(
                    thumbColor = Color(0xFF4A1CB7),
                    activeTrackColor = Color(0xFFEF9A9A),
                    inactiveTrackColor = Color(0xFFFFEBEE),
                    inactiveTickColor = Color(0xFFEF9A9A),
                    activeTickColor = DarkGray
                )
            )

            Switch(
                checked = checkedState.value,
                onCheckedChange = { checkedState.value = it }
            )

            TextButton(
                onClick = { checkedStateButton.value = !checkedStateButton.value }
            ) {
                Text("Поставить отсрочку?")
            }

            if (checkedStateButton.value){
                delayedTime()
            }
        }

    }
}

@Composable
fun delayedTime(){
    val time = remember { mutableStateOf("") }
    val date = remember { mutableStateOf("") }
    val c = Calendar.getInstance()
    val year = c.get(Calendar.YEAR)
    val month = c.get(Calendar.MONTH)
    val day = c.get(Calendar.DAY_OF_MONTH)
    val mHour = c[Calendar.HOUR_OF_DAY]
    val mMinute = c[Calendar.MINUTE]

    Text(
        text = "Выбрана дата: ${date.value}",
        color = Color.White
    )
    Text(
        text = "Выбрано время: ${time.value}",
        color = Color.White
    )

    val datePickerDialog = DatePickerDialog(
        LocalContext.current,
        { datePicker: DatePicker, year: Int, month: Int, day: Int ->
            if (month < 10) {
                date.value = "$day.0${month + 1}.$year"
            } else {
                date.value = "$day.${month + 1}.$year"
            }

        }, year, month, day
    )

    val timePickerDialog = TimePickerDialog(
        LocalContext.current,
        { _, hourOfDay, minute ->
            time.value = "$hourOfDay:$minute"
        }, mHour, mMinute, false
    )

    Button(onClick = {
        datePickerDialog.show()
    }) {
        Text(text = "Выбрать дату")

    }
    Button(
        onClick = {
            timePickerDialog.show()
        }) {
        Text(text = "Выбрать время")
    }
}


@Composable
fun deviceCamera(camera: Camera, deviceState: MutableState<SnapshotStateList<Any>>) {
    val checkedState = remember { mutableStateOf(false) }
    Card(
        modifier = Modifier.padding(100.dp, 10.dp),
        shape = RoundedCornerShape(10.dp)
    )
    {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Custom),
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End){
                IconButton(
                    onClick = {
                        deviceState.value.remove(camera)
                    }) {
                    Icon(painter = painterResource(id = R.drawable.baseline_delete),
                        contentDescription = "delete",
                        tint = Color.Red
                    )
                }
            }
            Image(
                painter = painterResource(id = R.drawable.camera),
                contentDescription = "camera"
            )
            Text(
                text = camera.title,
                color = Color.White
            )
            Text(
                text = camera.location,
                color = Color.Gray
            )
            Switch(
                checked = checkedState.value,
                onCheckedChange = { checkedState.value = it }
            )
        }
    }
}

@Composable
fun deviceCharger(charger: Charger, deviceState: MutableState<SnapshotStateList<Any>>) {
    val checkedStateButton = remember {
        mutableStateOf(false)
    }
    val checkedState = remember { mutableStateOf(false) }
    Card(
        modifier = Modifier.padding(100.dp, 10.dp),
        shape = RoundedCornerShape(10.dp)
    )
    {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Custom),
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End){
                IconButton(
                    onClick = {
                        deviceState.value.remove(charger)
                    }) {
                    Icon(painter = painterResource(id = R.drawable.baseline_delete),
                        contentDescription = "delete",
                        tint = Color.Red
                    )
                }
            }
            Image(
                painter = painterResource(id = R.drawable.charger),
                contentDescription = "camera"
            )
            Text(
                text = charger.title,
                color = Color.White
            )
            Text(
                text = charger.location,
                color = Color.Gray
            )
            Switch(
                checked = checkedState.value,
                onCheckedChange = { checkedState.value = it }
            )
            TextButton(
                onClick = { checkedStateButton.value = !checkedStateButton.value }
            ) {
                Text("Выбрать отложенное время?")
            }

            if (checkedStateButton.value){
                delayedTime()
            }
        }
    }
}

@Composable
fun deviceVacuumCleaner(vacuumCleaner: VacuumCleaner, deviceState: MutableState<SnapshotStateList<Any>>) {
    val checkedStateButton = remember {
        mutableStateOf(false)
    }
    val checkedState = remember { mutableStateOf(false) }
    Card(
        modifier = Modifier.padding(100.dp, 10.dp),
        shape = RoundedCornerShape(10.dp)
    )
    {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Custom),
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End){
                IconButton(
                    onClick = {
                        deviceState.value.remove(vacuumCleaner)
                    }) {
                    Icon(painter = painterResource(id = R.drawable.baseline_delete),
                        contentDescription = "delete",
                        tint = Color.Red
                    )
                }
            }
            Image(
                painter = painterResource(id = R.drawable.cleaner),
                contentDescription = "camera"
            )
            Text(
                text = vacuumCleaner.title,
                color = Color.White
            )
            Text(
                text = vacuumCleaner.location,
                color = Color.Gray
            )
            Switch(
                checked = checkedState.value,
                onCheckedChange = { checkedState.value = it }
            )
            TextButton(
                onClick = { checkedStateButton.value = !checkedStateButton.value }
            ) {
                Text("Выбрать отложенное время?")
            }

            if (checkedStateButton.value){
                delayedTime()
            }
        }
    }
}

@Composable
fun DialogAddDevice(
    dialogState: MutableState<Boolean>,
    nameState: MutableState<String>,
    onIconClick: () -> Unit,
    deviceState: MutableState<SnapshotStateList<Any>>
) {
    val nameText = remember {
        mutableStateOf("")
    }
    val nameLocation = remember {
        mutableStateOf("")
    }
    AlertDialog(onDismissRequest = {
        dialogState.value = false
    },
        confirmButton = {
            TextButton(onClick = {
                when (nameState.value) {
                    "Лампочка" -> {
                        deviceState.value.add(Lamp(nameText.value, "1", nameLocation.value))
                    }

                    "Чайник" -> {
                        deviceState.value.add(Kettle(nameText.value, nameLocation.value, "0", ""))
                    }

                    "Колонка" -> {
                        deviceState.value.add(MusicColumn(nameText.value, nameLocation.value, "0"))
                    }
                    "Камера" -> {
                        deviceState.value.add(Camera(nameText.value, nameLocation.value))
                    }
                    "Розетка" -> {
                        deviceState.value.add(Charger(nameText.value, nameLocation.value))
                    }
                    "Пылесос" -> {
                        deviceState.value.add(VacuumCleaner(nameText.value, nameLocation.value))
                    }
                }
                dialogState.value = false
                onIconClick()
            }) {
                Text(text = "Добавить")
            }
        },
        dismissButton = {
            TextButton(onClick = { dialogState.value = false }) {
                Text(text = "Назад")
            }
        },
        title = { Text(text = nameState.value) },
        text = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(text = "Введите название устройства:")
                TextField(value = nameText.value, onValueChange = {
                    nameText.value = it
                })
                Text(text = "Введите название комнаты:")
                TextField(value = nameLocation.value, onValueChange = {
                    nameLocation.value = it
                })

            }

        }
    )
}