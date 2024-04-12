package data

import androidx.compose.runtime.snapshots.SnapshotStateList

data class DeviceState(
    val camera: SnapshotStateList<Camera>,
    val charger: SnapshotStateList<Charger>,
    val kettle: SnapshotStateList<Kettle>,
    val lamp: SnapshotStateList<Lamp>,
    val musicColumn: SnapshotStateList<MusicColumn>,
    val vacuumCleaner: SnapshotStateList<VacuumCleaner>
)
