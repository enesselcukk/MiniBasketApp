package com.enesselcuk.minibasketapp.domain.mapper

import com.enesselcuk.minibasketapp.source.local.BasketEntity
import com.enesselcuk.minibasketapp.source.remote.model.BasketResponseItem


fun basketLocalMap(basketResponseItem: BasketResponseItem): BasketEntity {
    return BasketEntity(
        basketResponseItem.id,
        basketResponseItem.currency,
        basketResponseItem.image,
        basketResponseItem.name,
        basketResponseItem.price
    )
}