package com.example.myfirsttaskadam.util

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.myfirsttaskadam.R

object MediaLoading {

fun ImageView.loadingImage(
    context: Context,
    imageUrl: String?,
    placeHolder: Int = R.drawable.animate_loading
){
    Glide.with(context)
        .load(imageUrl)
        .placeholder(placeHolder)
        .centerCrop()
        .into(this)
}
}