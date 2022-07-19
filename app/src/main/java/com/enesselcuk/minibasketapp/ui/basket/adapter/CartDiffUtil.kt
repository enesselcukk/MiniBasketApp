package com.enesselcuk.minibasketapp.ui.basket.adapter

import androidx.recyclerview.widget.DiffUtil
import com.enesselcuk.minibasketapp.source.local.BasketEntity
import com.enesselcuk.minibasketapp.source.remote.model.BasketResponseItem

object CartDiffUtil : DiffUtil.ItemCallback<BasketEntity>() {
    override fun areItemsTheSame(
        oldItem: BasketEntity,
        newItem: BasketEntity
    ): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: BasketEntity,
        newItem: BasketEntity
    ): Boolean =
        oldItem == newItem
}