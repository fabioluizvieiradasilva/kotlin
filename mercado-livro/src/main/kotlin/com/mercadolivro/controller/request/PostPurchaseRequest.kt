package com.mercadolivro.controller.request

import com.fasterxml.jackson.annotation.JsonAlias
import jakarta.validation.constraints.NotNull
data class PostPurchaseRequest (
    @field:NotNull(message = "O customer_id não pode ser nulo")
    @JsonAlias("customer_id")
    var customerId: Int,

    @field:NotNull(message = "O book_ids não pode ser nulo")
    @JsonAlias("book_id")
    var bookId:Set<Int>
)
