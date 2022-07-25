package com.enesselcuk.minibasketapp.util

import android.graphics.Paint
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.google.android.material.textview.MaterialTextView


@BindingAdapter("app:imageView")
fun ImageView.setImageUrl(imageIcon: String?) {
    Glide.with(context)
        .load(imageIcon)
        .fitCenter()
        .into(this)
}

@BindingAdapter("app:progressbar")
fun ProgressBar.setProgress(visible: Boolean) {
    this.progress = if (visible) View.VISIBLE else View.GONE
}

