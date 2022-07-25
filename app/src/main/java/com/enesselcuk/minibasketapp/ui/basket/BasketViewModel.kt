package com.enesselcuk.minibasketapp.ui.basket

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.enesselcuk.minibasketapp.domain.repository.BasketRepos
import com.enesselcuk.minibasketapp.domain.repository.LocalBasketRepos
import com.enesselcuk.minibasketapp.source.local.BasketEntity
import com.enesselcuk.minibasketapp.source.remote.model.BasketResponse
import com.enesselcuk.minibasketapp.source.remote.model.Body
import com.enesselcuk.minibasketapp.source.remote.model.BodyResponse
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
    private val basketRepos: BasketRepos
) : ViewModel() {

    private val _uiState = MutableStateFlow<BasketUiState>(BasketUiState(loading = false))
    val uiState: StateFlow<BasketUiState> = _uiState

    private val _uiStateCart =
        MutableStateFlow<BasketUiStateCart>(BasketUiStateCart(loading = false))
    val uiStateCart: StateFlow<BasketUiStateCart> = _uiStateCart

    private var price: Double? = 0.0

    private val _product: MutableLiveData<BodyResponse> = MutableLiveData()
    val product: LiveData<BodyResponse> = _product

    private val _amount: MutableLiveData<Double> = MutableLiveData(price)
    val amount: LiveData<Double> = _amount

    private var job: Job? = null

    fun getSavedCart() {
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

    fun cart(id: Int, amount: Int) {
        viewModelScope.launch {
            basketRepos.cartProduct(id, amount)
                .collectLatest { networkResult ->
                    when (networkResult) {
                        is NetworkResult.Error -> {
                            _uiStateCart.update {
                                it.copy(
                                    loading = false,
                                    error = networkResult.message,
                                    basket = networkResult.data
                                )
                            }
                        }
                        is NetworkResult.Success -> {
                            _uiStateCart.update {
                                it.copy(
                                    loading = false,
                                    error = networkResult.message,
                                    basket = networkResult.data
                                )
                            }
                        }

                        is NetworkResult.Loading -> {
                            _uiStateCart.update {
                                it.copy(
                                    loading = true,
                                    error = networkResult.message,
                                    basket = networkResult.data
                                )
                            }
                        }
                    }
                }
        }
    }

    fun deleteToProduct(entity: BasketEntity) {
        viewModelScope.launch {
            repos.deleteBag(entity)
        }
    }

    fun productPLus(cart: BodyResponse) {
        _amount.value = cart.amount?.toDouble()?.let { _amount.value?.plus(it) }
        _product.value = cart
    }

    fun productReduce(cart: BodyResponse) {
        _amount.value = cart.amount?.toDouble()?.let { _amount.value?.minus(it) }
        _product.value = cart
    }

    fun resetProduct() {
        _amount.value = 0.0
    }
}