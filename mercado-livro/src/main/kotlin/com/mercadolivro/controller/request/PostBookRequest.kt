package com.mercadolivro.controller.request

import com.fasterxml.jackson.annotation.JsonAlias
import jakarta.validation.constraints.Digits
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import java.math.BigDecimal

data class PostBookRequest (
    @field:NotEmpty(message = "O Name deve ser informado")
    var name: String,

    @field:NotNull(message = "O Price deve ser informado")
    var price: BigDecimal,

    @field:NotEmpty(message = "O CustomerId deve ser informado")
    @JsonAlias("customer_id")
    var customerId: Int
)