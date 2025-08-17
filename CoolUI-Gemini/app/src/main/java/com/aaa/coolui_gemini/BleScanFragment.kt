package com.aaa.coolui_gemini

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.aaa.coolui_gemini.databinding.FragmentBleScanBinding
import kotlinx.coroutines.launch

class BleScanFragment : Fragment() {
    private lateinit var _binding: FragmentBleScanBinding
    private val viewModel: BleScanViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        _binding = FragmentBleScanBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = DeviceAdapter { device ->
            // ADDボタンがクリックされたときの処理
            // viewModel.addDevice(device)
        }

        _binding.rvDevices.layoutManager = LinearLayoutManager(requireContext())
        _binding.rvDevices.adapter = adapter

        // ViewModelのデータを監視
        lifecycleScope.launch {
            viewModel.scannedDevices.collect { devices ->
                adapter.submitList(devices) // DiffUtilを使用
            }
        }
    }
}