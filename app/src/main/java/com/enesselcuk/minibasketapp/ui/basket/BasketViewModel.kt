package com.enesselcuk.minibasketapp.ui.basket

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.enesselcuk.minibasketapp.domain.repository.LocalBasketRepos
import com.enesselcuk.minibasketapp.source.local.BasketDao
import com.enesselcuk.minibasketapp.ui.home.HomeUiState
import com.enesselcuk.minibasketapp.util.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BasketViewModel @Inject constructor(
    private val repos: LocalBasketRepos,
    private val dao: BasketDao
) : ViewModel() {

    private val _uiState = MutableStateFlow<BasketUiState>(BasketUiState(loading = false))
    val uiState: StateFlow<BasketUiState> = _uiState

    private var job: Job? = null

    init {
        getSavedCart()
    }

    private fun getSavedCart() {
        job = viewModelScope.launch {
            repos.getSavedBag().collectLatest { networkResult ->
                when (networkResult) {
                    is NetworkResult.Error -> {
                        _uiState.update {
                            it.copy(
                                loading = false,
                                error = networkResult.message,
                                basket = networkResult.data ?: emptyList()
                            )
                        }
                    }
                    is NetworkResult.Loading -> {
                        _uiState.update {
                            it.copy(
                                loading = true,
                                error = networkResult.message,
                                basket = networkResult.data ?: emptyList()
                            )
                        }
                    }
                    is NetworkResult.Success -> {
                        _uiState.update {
                            it.copy(
                                loading = false,
                                error = networkResult.message,
                                basket = networkResult.data ?: emptyList()
                            )
                        }
                    }
                }
            }
        }
    }

    fun getAllCart() {
        viewModelScope.launch {
            dao.getAllProduce()
        }
    }
}