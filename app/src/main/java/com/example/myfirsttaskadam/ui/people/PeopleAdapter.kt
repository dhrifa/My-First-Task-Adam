package com.example.myfirsttaskadam.ui.people

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myfirsttaskadam.R
import com.example.myfirsttaskadam.data.model.people.PeopleItemModel
import com.example.myfirsttaskadam.databinding.PeopleItemBinding
import com.example.myfirsttaskadam.util.MediaLoading.loadingImage

class PeopleAdapter(
    val data: ArrayList<PeopleItemModel>,
    val function: (peopleItem: PeopleItemModel/*, position: Int*/) -> Unit
) : RecyclerView.Adapter<PeopleAdapter.ViewHolder>() {

    inner class ViewHolder(val view: PeopleItemBinding) : RecyclerView.ViewHolder(view.root) {
        fun initUi(peopleItem: PeopleItemModel, position: Int) {
            //data -> peopleitem
            //UI-> view
            view.tvTitle.text = "${peopleItem.firstName} ${peopleItem.lastName}"
            view.tvDesc.text = peopleItem.jobtitle
            with(itemView) {
                view.ivProfilePic.loadingImage(context,peopleItem.avatar)
            }
            view.ivProfilePic.setOnClickListener {
                function.invoke(peopleItem/*, position*/)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        PeopleItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.initUi(data[position], position)
    }

    override fun getItemCount() = data.size

}
