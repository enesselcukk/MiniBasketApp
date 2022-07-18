package com.enesselcuk.minibasketapp.ui.home

import com.enesselcuk.minibasketapp.remote.model.BasketResponse

data class HomeUiState(
    val isLoading: Boolean? = false,
    val error: String? = null,
    val stores: List<BasketResponse> = emptyList(),
)