package com.mercadolivro.enums

enum class Errors(val code: String, val message: String) {
    ML101(code = "ML101", message = "Book [%s] not exist"),
    ML102(code = "ML102", message = "Cannot update book with status [%s]"),
    ML201(code = "ML201", message = "Customer [%s] not exist")
}