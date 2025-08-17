package com.aaa.coolui_gemini

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aaa.coolui_gemini.databinding.ItemDeviceBinding

class DeviceAdapter(private val onAddClick: (Device) -> Unit) :
    ListAdapter<Device, DeviceAdapter.DeviceViewHolder>(DeviceDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeviceViewHolder {
        val binding = ItemDeviceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DeviceViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DeviceViewHolder, position: Int) {
        val device = getItem(position)
        holder.bind(device)
    }

    inner class DeviceViewHolder(private val binding: ItemDeviceBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(device: Device) {
            binding.tvDeviceName.text = device.name
            binding.tvDeviceUuid.text = device.uuid
            binding.tvRssi.text = device.rssi
            // アイコンの設定ロジック（例：デバイスタイプに応じて画像を切り替える）
            binding.ivDeviceIcon.setImageResource(getDeviceIcon(device.type))

            binding.btnAddDevice.setOnClickListener {
                onAddClick(device)
            }
        }

        private fun getDeviceIcon(type: DeviceType): Int {
            return when (type) {
                DeviceType.HEART_RATE -> R.drawable.ic_heart_rate
                DeviceType.SENSOR -> R.drawable.ic_sensor
                else -> R.drawable.ic_default_device
            }
        }
    }
}

// DiffUtilの実装
class DeviceDiffCallback : DiffUtil.ItemCallback<Device>() {
    override fun areItemsTheSame(oldItem: Device, newItem: Device): Boolean {
        return oldItem.uuid == newItem.uuid
    }

    override fun areContentsTheSame(oldItem: Device, newItem: Device): Boolean {
        return oldItem == newItem
    }
}

// データモデル
data class Device(
    val name: String,
    val uuid: String,
    val rssi: String,
    val type: DeviceType
)

enum class DeviceType {
    HEART_RATE,
    SENSOR,
    OTHER
}