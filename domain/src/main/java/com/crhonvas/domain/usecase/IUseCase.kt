package com.crhonvas.domain.usecase

interface IUseCase<IN, OUT:Any?> {
    fun execute(params: OUT): IN
}