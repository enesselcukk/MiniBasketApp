package com.enesselcuk.minibasketapp.ui.home

import com.enesselcuk.minibasketapp.source.remote.model.BasketResponse

data class HomeUiState(
    val loading: Boolean? = false,
    val error: String? = null,
    val basket: BasketResponse? = null,
)