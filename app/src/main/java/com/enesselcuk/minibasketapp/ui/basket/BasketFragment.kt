package com.enesselcuk.minibasketapp.ui.basket


import android.annotation.SuppressLint
import android.content.Context
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.enesselcuk.minibasketapp.base.BaseFragment
import com.enesselcuk.minibasketapp.databinding.FragmentBasketBinding
import com.enesselcuk.minibasketapp.ui.basket.adapter.CartAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class BasketFragment : BaseFragment<FragmentBasketBinding>(FragmentBasketBinding::inflate) {
    private val cartViewModel: BasketViewModel by activityViewModels()
    private lateinit var cartAdapter: CartAdapter

    override fun definition() {

        cartAdapter = CartAdapter()
        binding.recyclerCart.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = cartAdapter
        }

    }

    @SuppressLint("UnsafeRepeatOnLifecycleDetector")
    override fun setObserver() {
        viewLifecycleOwner.lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    cartViewModel.uiState.collect {
                        cartAdapter.submitList(it.basket)
                    }
                }
            }
        }
    }
}