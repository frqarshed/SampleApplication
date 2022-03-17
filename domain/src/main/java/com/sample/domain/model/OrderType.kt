package com.sample.domain.model

sealed class OrderType {
    object Ascending: OrderType()
    object Descending: OrderType()
}
