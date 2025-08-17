package com.aaa.coolui_gemini

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

// DiffUtilの実装
class BleScanViewModel : ViewModel()  {
    private val _scannedDevices = MutableStateFlow<List<Device>>(emptyList())
    val scannedDevices: StateFlow<List<Device>> = _scannedDevices.asStateFlow()

    init {
        // BLEスキャン処理を開始し、結果を _scannedDevices に流し込む
        // 例: BLEスキャンを開始
        viewModelScope.launch {
            // ダミーデータで初期化
            _scannedDevices.value = listOf(
                Device("Heart Rate Monitor", "RSSI: 0001-0000000", "-65 dbm", DeviceType.HEART_RATE),
                Device("Device 001", "UUID: 0001-0A0C", "-80 dbm", DeviceType.OTHER),
                Device("Sensor Beacon", "UUID: 82A22C", "-88 dbm", DeviceType.SENSOR)
            )
        }
    }

    fun addDevice(device: Device) {
        // デバイス追加ロジック
    }
}