package com.enesselcuk.minibasketapp.ui.basket


import android.annotation.SuppressLint
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.enesselcuk.minibasketapp.base.BaseFragment
import com.enesselcuk.minibasketapp.databinding.FragmentBasketBinding
import com.enesselcuk.minibasketapp.source.remote.repos.mapper.cartDataMap
import com.enesselcuk.minibasketapp.ui.basket.adapter.CartAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class BasketFragment : BaseFragment<FragmentBasketBinding>(FragmentBasketBinding::inflate) {
    private val cartViewModel: BasketViewModel by activityViewModels()
    private lateinit var cartAdapter: CartAdapter


    @SuppressLint("UnsafeRepeatOnLifecycleDetector")
    override fun definition() {
        cartAdapter = CartAdapter(CartAdapter.ClickToRemove { basketEntity ->
            cartViewModel.deleteToProduct(basketEntity)
            cartViewModel.getSavedCart()
        }, {
            cartViewModel.productPLus(it)
            Toast.makeText(
                requireContext(),
                cartViewModel.amount.value.toString() + " id :" +
                        cartViewModel.product.value?.id.toString(),
                Toast.LENGTH_SHORT
            ).show()
        }, {
            cartViewModel.productReduce(it)
            Toast.makeText(
                requireContext(),
                cartViewModel.amount.value.toString() + " id :" +
                        cartViewModel.product.value?.id.toString(),
                Toast.LENGTH_SHORT
            ).show()
        })

        binding.recyclerCart.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = cartAdapter
        }
        cartViewModel.getSavedCart()
        backStack()

        binding.btnContinue.setOnClickListener {
            val action = BasketFragmentDirections.actionBasketFragmentToHomeFragment()
            findNavController().navigate(action)
        }

        binding.btnPlaceOrder.setOnClickListener {
            cartViewModel.product.observe(viewLifecycleOwner) {
                cartViewModel.cart(it.id!!, it.amount!!)
            }
            viewLifecycleOwner.lifecycleScope.launch {
                lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                    launch {
                        cartViewModel.uiStateCart.collect {
                            it.basket?.map {
                                Toast.makeText(
                                    requireContext(),
                                    it.amount.toString() +
                                            it.id.toString(),
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                            it.error.let {
                                Toast.makeText(requireContext(), it.toString(), Toast.LENGTH_SHORT)
                                    .show()
                            }
                        }
                    }
                }
            }
        }
    }

    @SuppressLint("UnsafeRepeatOnLifecycleDetector")
    override fun setObserver() {
        viewLifecycleOwner.lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    cartViewModel.uiState.collect {
                        cartAdapter.submitList(it.basket)
                        binding.localSize = it.basket?.size
                        cartViewModel.resetProduct()
                        it.basket?.forEach {
                            cartViewModel.productPLus(cartDataMap(it))
                        }
                    }
                }
            }
        }


    }

    override fun onResume() {
        super.onResume()
        cartViewModel.getSavedCart()
    }

    private fun backStack() {
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)
    }
}