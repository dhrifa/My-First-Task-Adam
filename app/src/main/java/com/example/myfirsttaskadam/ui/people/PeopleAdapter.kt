package com.example.myfirsttaskadam.ui.people

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myfirsttaskadam.R
import com.example.myfirsttaskadam.data.model.people.PeopleItemModel
import com.example.myfirsttaskadam.databinding.PeopleItemBinding

class PeopleAdapter(
    val data: ArrayList<PeopleItemModel>,
    val function: (peopleItem: PeopleItemModel) -> Unit
) : RecyclerView.Adapter<PeopleAdapter.ViewHolder>() {

    inner class ViewHolder(val view: PeopleItemBinding) : RecyclerView.ViewHolder(view.root) {
        fun initUi(peopleItem: PeopleItemModel) {
            //data -> peopleitem
            //UI-> view
            view.tvTitle.text = "${peopleItem.firstName} ${peopleItem.lastName}"
            view.tvDesc.text = peopleItem.jobtitle
            with(itemView) {
                Glide.with(context)
                    .load(peopleItem.avatar)
                    .placeholder(R.drawable.animate_loading)
                    .centerCrop()
                    .into(view.ivProfilePic)
            }
            view.ivProfilePic.setOnClickListener {
                function.invoke(peopleItem)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        PeopleItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.initUi(data[position])
    }

    override fun getItemCount() = data.size

}
