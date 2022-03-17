package com.sample.domain.interactor.base

abstract class UseCase<T, in P> {
    abstract suspend fun execute(params: P? = null): T
}