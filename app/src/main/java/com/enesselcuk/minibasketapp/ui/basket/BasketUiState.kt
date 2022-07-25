package com.enesselcuk.minibasketapp.ui.basket

import com.enesselcuk.minibasketapp.source.local.BasketEntity
import com.enesselcuk.minibasketapp.source.remote.model.Body

data class BasketUiState(
    val loading: Boolean? = false,
    val error: String? = null,
    val basket: List<BasketEntity>? = emptyList(),
)

data class BasketUiStateCart(
    val loading: Boolean? = false,
    val error: String? = null,
    val basket: Body? =null
)


