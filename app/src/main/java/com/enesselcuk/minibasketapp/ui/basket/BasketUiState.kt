package com.enesselcuk.minibasketapp.ui.basket

import com.enesselcuk.minibasketapp.source.local.BasketEntity
import com.enesselcuk.minibasketapp.source.remote.model.BasketResponse

data class BasketUiState(
    val loading: Boolean? = false,
    val error: String? = null,
    val basket: List<BasketEntity>? = emptyList(),
)