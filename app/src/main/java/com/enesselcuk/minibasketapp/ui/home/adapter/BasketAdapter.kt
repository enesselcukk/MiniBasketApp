package com.enesselcuk.minibasketapp.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import com.enesselcuk.minibasketapp.R
import com.enesselcuk.minibasketapp.source.remote.model.BasketResponseItem

class BasketAdapter(private val click: ClickToBasket) :
    ListAdapter<BasketResponseItem, BasketVHolder>(DiffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasketVHolder =
        BasketVHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_adapter, parent, false
            )
        )

    override fun onBindViewHolder(holder: BasketVHolder, position: Int) {
        val basketPosition = getItem(position)
        holder.bind(basketPosition, click)
    }

    class ClickToBasket(val btnClick: (BasketResponseItem) -> Unit) {
        fun clickBtn(basketResponseItem: BasketResponseItem) = btnClick(basketResponseItem)
    }
}