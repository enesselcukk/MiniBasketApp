package com.enesselcuk.minibasketapp.ui.home.adapter

import androidx.recyclerview.widget.RecyclerView
import com.enesselcuk.minibasketapp.databinding.ItemAdapterBinding
import com.enesselcuk.minibasketapp.remote.model.BasketResponseItem

class BasketVHolder(private val binding: ItemAdapterBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(basket: BasketResponseItem) {
        binding.setData = basket
    }
}