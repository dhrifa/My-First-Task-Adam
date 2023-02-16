package com.example.myfirsttaskadam.ui.room

import android.opengl.Visibility
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myfirsttaskadam.data.model.room.RoomModelItemModel
import com.example.myfirsttaskadam.databinding.RoomItemBinding

class RoomAdapter(
    val data: ArrayList<RoomModelItemModel>,
    val function: () -> Unit
) : RecyclerView.Adapter<RoomAdapter.ViewHolder>() {

    inner class ViewHolder(val roomItem: RoomItemBinding) : RecyclerView.ViewHolder(roomItem.root) {

        fun initUi(roomItemData: RoomModelItemModel) {
            //data ->roomItemdata from initUI
            // ui-> roomItemview from viewholder
            roomItem.tvIdRoom.text = "Room number ${roomItemData.id}"
            roomItem.tvDateCreat.text = roomItemData.createdAt

            if (roomItemData.isOccupied == true) {
                roomItem.ivOccupied.visibility = View.VISIBLE
                roomItem.ivFree.visibility = View.GONE
                roomItem.tvOccupied.text = "Occupied"
            } else {
                roomItem.ivOccupied.visibility = View.GONE
                roomItem.ivFree.visibility = View.VISIBLE
                roomItem.tvOccupied.text =   "Free"
            }
            roomItem.tvMaxOccupied.text = "Max Occupancy: ${roomItemData.maxOccupancy.toString()}"
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        RoomItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.initUi(data[position])
    }

    override fun getItemCount() = data.size

}
