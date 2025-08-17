package com.aaa.coolui_chatgpt

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

data class DeviceItem(
    val name: String,
    val info: String,
    val rssi: Int
)

class DeviceAdapter(private val devices: MutableList<DeviceItem>, private val onAddClick: (DeviceItem) -> Unit)
    : RecyclerView.Adapter<DeviceAdapter.DeviceViewHolder>() {

    inner class DeviceViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtName: TextView = view.findViewById(R.id.txt_name)
        val txtInfo: TextView = view.findViewById(R.id.txt_info)
        val txtRssi: TextView = view.findViewById(R.id.txt_rssi)
        val btnAdd: Button = view.findViewById(R.id.btn_add)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeviceViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_device, parent, false)
        return DeviceViewHolder(view)
    }

    override fun getItemCount(): Int = devices.size

    override fun onBindViewHolder(holder: DeviceViewHolder, position: Int) {
        val device = devices[position]
        holder.txtName.text = device.name
        holder.txtInfo.text = device.info
        holder.txtRssi.text = "${device.rssi} dBm"
        holder.btnAdd.setOnClickListener { onAddClick(device) }
    }

    fun updateList(newList: List<DeviceItem>) {
        devices.clear()
        devices.addAll(newList)
        notifyDataSetChanged()
    }
}
