package com.enesselcuk.minibasketapp.ui.home.adapter

import androidx.recyclerview.widget.DiffUtil
import com.enesselcuk.minibasketapp.source.local.BasketEntity
import com.enesselcuk.minibasketapp.source.remote.model.BasketResponseItem

object BasketDiffUtil : DiffUtil.ItemCallback<BasketResponseItem>() {
    override fun areItemsTheSame(oldItem: BasketResponseItem, newItem: BasketResponseItem): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: BasketResponseItem, newItem: BasketResponseItem): Boolean =
        oldItem == newItem
}