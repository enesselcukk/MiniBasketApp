package com.enesselcuk.minibasketapp.ui.basket.adapter

import androidx.recyclerview.widget.RecyclerView
import com.enesselcuk.minibasketapp.databinding.ItemBasketBinding
import com.enesselcuk.minibasketapp.source.local.BasketEntity

class CartVHolder(private val binding: ItemBasketBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(
        basket: BasketEntity,
    ) {
        binding.cardData = basket

    }
}