package com.aaa.coolui_chatgpt

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: DeviceAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recycler_devices)
        adapter = DeviceAdapter(mutableListOf()) { device ->
            Toast.makeText(this, "${device.name} Added!", Toast.LENGTH_SHORT).show()
        }

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        // 仮データ
        val devices = listOf(
            DeviceItem("Heart Rate Monitor", "RSSI: 0001-000000", -65),
            DeviceItem("Device 001", "UUID: 0001-0A0C", -80),
            DeviceItem("Sensor Beacon", "UUID: 82A22C", -88)
        )

        adapter.updateList(devices)
    }
}
