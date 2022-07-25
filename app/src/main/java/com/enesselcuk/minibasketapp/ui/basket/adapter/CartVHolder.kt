package com.enesselcuk.minibasketapp.ui.basket.adapter

import androidx.recyclerview.widget.RecyclerView
import com.enesselcuk.minibasketapp.databinding.ItemBasketBinding
import com.enesselcuk.minibasketapp.source.local.BasketEntity
import com.enesselcuk.minibasketapp.source.remote.model.BasketResponse
import com.enesselcuk.minibasketapp.source.remote.model.Body
import com.enesselcuk.minibasketapp.source.remote.model.BodyResponse
import com.enesselcuk.minibasketapp.source.remote.repos.mapper.cartDataMap


class CartVHolder(private val binding: ItemBasketBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(
        basket: BasketEntity,
        clickToRemove: CartAdapter.ClickToRemove,
        increaseClick: (BodyResponse) -> Unit,
        decreaseClick: (BodyResponse) -> Unit,

        ) {
        binding.cardData = basket
        binding.clickRemove = clickToRemove

        var result = basket.salePrice
        binding.setDataPrice = result.toString()

        var count = 1
        binding.setData = count.toString()

        if (count == 1) {
            binding.setData = count.toString()
        }

        binding.imagePlus.setOnClickListener {
            count++
            binding.setData = count.toString()

            increaseClick(cartDataMap(basket))

            result = result!! + basket.salePrice!!
            binding.setDataPrice = result.toString()

        }

        binding.imageRemove.setOnClickListener {
            if (count != 1) {
                count--
                binding.setData = count.toString()
                decreaseClick(cartDataMap(basket))

                if (result != basket.salePrice) {
                    result = result!! - basket.salePrice!!
                    binding.setDataPrice = result.toString()
                }

            }
        }
    }

}
