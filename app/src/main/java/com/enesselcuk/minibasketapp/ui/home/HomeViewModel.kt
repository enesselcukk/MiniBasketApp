package com.enesselcuk.minibasketapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.enesselcuk.minibasketapp.domain.repository.BasketRepos
import com.enesselcuk.minibasketapp.util.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repos: BasketRepos) : ViewModel() {


    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState(loading = false))
    val uiState: StateFlow<HomeUiState> = _uiState

    private var job: Job? = null

    init {
        getBasket()
    }

    private fun getBasket() {
        job = viewModelScope.launch {
            repos.getProduct()
                .collectLatest {
                    when (it) {
                        is NetworkResult.Error -> {
                            _uiState.update { state ->
                                state.copy(loading = false, error = it.message.toString())
                            }
                        }
                        is NetworkResult.Loading -> {
                            _uiState.update { state ->
                                state.copy(loading = true)
                            }
                        }
                        is NetworkResult.Success -> {
                            _uiState.update { state ->
                                state.copy(loading = false, basket = it.data)
                            }
                        }
                    }
                }
        }
    }
}