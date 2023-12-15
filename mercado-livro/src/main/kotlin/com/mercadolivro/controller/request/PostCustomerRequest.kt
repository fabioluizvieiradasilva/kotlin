package com.mercadolivro.controller.request

import com.mercadolivro.validation.EmailAvailable
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty

data class PostCustomerRequest (
    @field: NotEmpty(message = "O nome deve ser informado")
    var name: String,

    @field:Email(message = "O email deve ser um valor válido")
    @EmailAvailable
    var email: String,

    @field:NotEmpty(message = "A senha deve ser informada")
    var password: String
)