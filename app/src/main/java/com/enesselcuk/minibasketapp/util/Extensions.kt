package com.enesselcuk.minibasketapp.util

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.enesselcuk.minibasketapp.remote.model.BasketResponseItem
import com.enesselcuk.minibasketapp.ui.home.adapter.BasketAdapter


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
