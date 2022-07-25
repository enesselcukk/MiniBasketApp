package com.enesselcuk.minibasketapp.source.remote.repos.mapper

import com.enesselcuk.minibasketapp.source.local.BasketEntity
import com.enesselcuk.minibasketapp.source.remote.model.BasketResponseItem
import com.enesselcuk.minibasketapp.source.remote.model.Body
import com.enesselcuk.minibasketapp.source.remote.model.BodyResponse


fun basketLocalMap(basketResponseItem: BasketResponseItem): BasketEntity {
    return BasketEntity(
        basketResponseItem.id,
        basketResponseItem.currency,
        basketResponseItem.image,
        basketResponseItem.name,
        basketResponseItem.price,
        basketResponseItem.price?.toDouble()

    )
}

fun cartDataMap(basket: BasketEntity): BodyResponse {

    return BodyResponse(
        basket.id,
        basket.price?.toInt()
    )

}