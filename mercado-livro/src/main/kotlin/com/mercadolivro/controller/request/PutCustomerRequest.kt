package com.mercadolivro.controller.request

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty

data class PutCustomerRequest (

    @field:NotEmpty(message = "O nome deve ser informado")
    var name: String,

    @field:Email(message = "O email deve ser um valor válido")
    var email: String
)