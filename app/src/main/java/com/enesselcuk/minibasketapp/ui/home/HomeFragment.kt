package com.enesselcuk.minibasketapp.ui.home


import android.annotation.SuppressLint
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.enesselcuk.minibasketapp.base.BaseFragment
import com.enesselcuk.minibasketapp.databinding.FragmentHomeBinding
import com.enesselcuk.minibasketapp.ui.home.adapter.BasketAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var basketAdapter: BasketAdapter

    override fun definition() {
        basketAdapter = BasketAdapter(BasketAdapter.ClickToBasket {
            val action = HomeFragmentDirections.actionHomeFragmentToBasketFragment()
            findNavController().navigate(action)
        })
        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = basketAdapter
        }
    }

    @SuppressLint("UnsafeRepeatOnLifecycleDetector")
    override fun setObserver() {
        viewLifecycleOwner.lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.uiState.collect {
                        homeUiStatus(it)
                    }
                }
            }
        }
    }

    private fun homeUiStatus(homeUiState: HomeUiState) {
        homeUiState.loading?.let {
            binding.progresBar.isVisible = it
        }
        homeUiState.error?.let {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }
        homeUiState.basket.let {
            basketAdapter.submitList(it)
        }
    }

}