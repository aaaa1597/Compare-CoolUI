package com.aaa.coolui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
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
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        recyclerView = findViewById(R.id.deviceRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val devices = listOf(
            Device("Heart Rate Monitor", "RSSI: 0001-000000", "-65 dBm"),
            Device("Device 001", "UUID: 0001-0A0C", "-80 dBm"),
            Device("Sensor Beacon", "UUID: 82A22C", "-88 dBm")
        )

        adapter = DeviceAdapter(devices)
        recyclerView.adapter = adapter
    }
}

data class Device(val name: String, val info: String, val signal: String)

class DeviceAdapter(private val devices: List<Device>) :
    RecyclerView.Adapter<DeviceAdapter.DeviceViewHolder>() {

    class DeviceViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.deviceName)
        val info: TextView = view.findViewById(R.id.deviceInfo)
        val signal: TextView = view.findViewById(R.id.signalStrength)
        val addButton: Button = view.findViewById(R.id.addButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeviceViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_device, parent, false)
        return DeviceViewHolder(view)
    }

    override fun onBindViewHolder(holder: DeviceViewHolder, position: Int) {
        val device = devices[position]
        holder.name.text = device.name
        holder.info.text = device.info
        holder.signal.text = device.signal
        holder.addButton.setOnClickListener {
            Toast.makeText(holder.itemView.context, "Added ${device.name}", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int = devices.size
}
