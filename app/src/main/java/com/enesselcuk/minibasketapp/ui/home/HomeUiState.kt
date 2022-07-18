package com.enesselcuk.minibasketapp.ui.home

import com.enesselcuk.minibasketapp.remote.model.BasketResponse
import com.enesselcuk.minibasketapp.remote.model.BasketResponseItem

data class HomeUiState(
    val loading: Boolean? = false,
    val error: String? = null,
    val basket: BasketResponse? = null,
)