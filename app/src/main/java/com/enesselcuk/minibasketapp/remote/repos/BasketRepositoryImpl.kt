package com.enesselcuk.minibasketapp.remote.repos


import com.enesselcuk.minibasketapp.remote.service.BasketService
import javax.inject.Inject

class BasketRepositoryImpl @Inject constructor(
    private val api: BasketService,
) {

}