package com.example.myfirsttaskadam.ui.room

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.myfirsttaskadam.data.model.room.RoomModel
import com.example.myfirsttaskadam.data.model.room.RoomModelItemModel
import com.example.myfirsttaskadam.databinding.FragmentRoomBinding
import com.example.myfirsttaskadam.util.NetworkResult
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RoomFragment : Fragment() {

    private var _binding: FragmentRoomBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    val viewModel: RoomViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRoomBinding.inflate(inflater, container, false)
        binding.let { ui ->

            viewModel.roomList.observe(viewLifecycleOwner) {
                when (it) {

                    is NetworkResult.Loading -> {
                        Toast.makeText(context, "Loading...!", Toast.LENGTH_SHORT).show()
                    }
                    is NetworkResult.Success -> {
                        initView(it.data)
                        Toast.makeText(context, "Data", Toast.LENGTH_SHORT).show()
                    }
                    is NetworkResult.Error -> {
                        Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
            viewModel.getRoomList()
        }
        return binding.root
    }

    private fun initView(data: RoomModel?) {
        data?.let {
            binding.rvRoom.adapter = RoomAdapter(data) {}
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}