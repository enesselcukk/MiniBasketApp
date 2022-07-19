package com.enesselcuk.minibasketapp.ui.basket.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import com.enesselcuk.minibasketapp.R
import com.enesselcuk.minibasketapp.source.local.BasketEntity

class CartAdapter() :
    ListAdapter<BasketEntity, CartVHolder>(CartDiffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartVHolder =
        CartVHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_basket, parent, false
            )
        )

    override fun onBindViewHolder(holder: CartVHolder, position: Int) {
        val basketPosition = getItem(position)
        holder.bind(basketPosition)
    }

//    class ClickToBasket(val btnClick: (BasketResponseItem) -> Unit) {
//        fun clickBtn(basketResponseItem: BasketResponseItem) = btnClick(basketResponseItem)
//    }
}